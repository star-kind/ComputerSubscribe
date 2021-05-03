package org.apidoc.subscribe.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import org.apidoc.subscribe.bo.Header;
import org.apidoc.subscribe.bo.Info;
import org.apidoc.subscribe.bo.Item;
import org.apidoc.subscribe.bo.Query;
import org.apidoc.subscribe.bo.Request;
import org.apidoc.subscribe.bo.TopItem;
import org.apidoc.subscribe.bo.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 利用postman.json内容,将之生成对象
 * 
 * @author user
 *
 */
public class GetJsonObj {
	static String t = "org.apidoc.subscribe.services.GetJsonObj" + "---\n";

	/**
	 * 获取json文本字符串
	 * 
	 * @param fileURI 文件路径全称
	 * @return
	 */
	public static String getJsonStr(String fileURI) {
		String encoding = "UTF-8";
		File file = new File(fileURI);
		Long fileLength = file.length();
		byte[] fileContent = new byte[fileLength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(fileContent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(fileContent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println(t + "The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取url节点数据集合
	 * 
	 * @param itemList
	 * @return
	 */
	public LinkedList<URL> getUrlNodeData(JSONArray itemList) {
		LinkedList<URL> urlBoList = new LinkedList<URL>();

		for (Object i : itemList) {
			JSONObject jo = (JSONObject) i;

			JSONArray itemArray = JSONArray.fromObject(jo.get("item"));
			for (Object object : itemArray) {
				JSONObject jObject = (JSONObject) object;

				JSONArray requestArray = JSONArray
						.fromObject(jObject.get("request"));
				for (Object requestObj : requestArray) {
					JSONObject jRequestObj = (JSONObject) requestObj;

					JSONArray urlArray = JSONArray
							.fromObject(jRequestObj.get("url"));

					for (Object urlObject : urlArray) {
						URL url = new URL();

						JSONObject jsonUrlObject = (JSONObject) urlObject;
						LinkedList<Query> queries = getQueryListFromURL(
								jsonUrlObject);

						url.setQuery(queries);
//						System.out.println(jsonUrlObject.getString("protocol") + ","
//								+ jsonUrlObject.getString("raw") + ","
//								+ jsonUrlObject.getString("host") + ","
//								+ jsonUrlObject.getString("port") + ","
//								+ jsonUrlObject.getString("path"));
						url.setHost(jsonUrlObject.getString("host"));
						url.setPath(jsonUrlObject.getString("path"));
						url.setPort(jsonUrlObject.getString("port"));
						url.setProtocol(jsonUrlObject.getString("protocol"));
						url.setRaw(jsonUrlObject.getString("raw"));

						urlBoList.add(url);
					}
				}
			}
		}
		return urlBoList;
	}

	/**
	 * 从url数据中获取query节点数据集合
	 * 
	 * @param jsonUrlObject
	 * @return
	 */
	public LinkedList<Query> getQueryListFromURL(JSONObject jsonUrlObject) {
		JSONArray queryObjArr = JSONArray.fromObject(jsonUrlObject.get("query"));
		LinkedList<Query> queries = new LinkedList<Query>();
		for (Object query : queryObjArr) {
			Query q = new Query();

			if (query.toString().length() > 4) {
				JSONObject jsonQuery = (JSONObject) query;

				q.setKey(jsonQuery.getString("key"));
				q.setDescription(jsonQuery.getString("description"));
			} else {
				q.setKey(null);
				q.setDescription(null);
			}
			queries.add(q);
		}
		return queries;
	}

	/**
	 * 获取request节点集合数据
	 * 
	 * @param itemList
	 * @return
	 */
	public LinkedList<Request> getRequestNodeData(JSONArray itemList) {
		LinkedList<Request> reqList = new LinkedList<Request>();

		for (Object items : itemList) {
			JSONObject jsonItem = (JSONObject) items;

			JSONArray itemArray = JSONArray.fromObject(jsonItem.get("item"));
			for (Object itemObj : itemArray) {
				JSONObject jsonItemObj = (JSONObject) itemObj;

				JSONArray requestArray = JSONArray
						.fromObject(jsonItemObj.get("request"));

				for (Object requestObj : requestArray) {
					Request request = new Request();
					JSONObject jsonRequestObj = (JSONObject) requestObj;

					request.setMethod(jsonRequestObj.getString("method"));

					LinkedList<Header> headerNode = getHeaderNodeFromRequest(
							jsonRequestObj);
					request.setHeader(headerNode);

					LinkedList<URL> urlNode = getUrlNodeFromRequest(jsonRequestObj);
					request.setUrl(urlNode);

					reqList.add(request);
				}
			}
		}
		return reqList;
	}

	/**
	 * 从request中获取url节点数据集合
	 * 
	 * @param jsonRequestObj
	 * @return
	 */
	public LinkedList<URL> getUrlNodeFromRequest(JSONObject jsonRequestObj) {
		LinkedList<URL> urlBoList = new LinkedList<URL>();

		JSONArray urlArray = JSONArray.fromObject(jsonRequestObj.get("url"));

		for (Object urlObject : urlArray) {
			URL url = new URL();

			JSONObject jsonUrlObject = (JSONObject) urlObject;
			LinkedList<Query> queries = getQueryListFromURL(jsonUrlObject);

			url.setQuery(queries);
//						System.out.println(jsonUrlObject.getString("protocol") + ","
//								+ jsonUrlObject.getString("raw") + ","
//								+ jsonUrlObject.getString("host") + ","
//								+ jsonUrlObject.getString("port") + ","
//								+ jsonUrlObject.getString("path"));
			url.setHost(jsonUrlObject.getString("host"));
			url.setPath(jsonUrlObject.getString("path"));
			url.setPort(jsonUrlObject.getString("port"));
			url.setProtocol(jsonUrlObject.getString("protocol"));
			url.setRaw(jsonUrlObject.getString("raw"));

			urlBoList.add(url);
		}
		return urlBoList;
	}

	/**
	 * 从request中获取header节点数据集合
	 * 
	 * @param itemList
	 * @return
	 */
	public LinkedList<Header> getHeaderNodeFromRequest(JSONObject jsonRequestObj) {
		LinkedList<Header> headerBoList = new LinkedList<Header>();

		JSONArray headerArray = JSONArray.fromObject(jsonRequestObj.get("header"));

		for (int j = 0; j < headerArray.size(); j++) {
			JSONObject jsonHeadObj = (JSONObject) headerArray.get(j);
			Header header = new Header();

			if (!"".equals(jsonHeadObj.getString("key"))) {
//							System.out.println(jsonHeadObj.getString("key") + "--"
//									+ jsonHeadObj.getString("description"));
				header.setDescription(jsonHeadObj.getString("description"));
				header.setKey(jsonHeadObj.getString("key"));

			} else {
				header.setKey(null);
				header.setDescription(null);
			}
			headerBoList.add(header);
		}

		return headerBoList;
	}

	/**
	 * 
	 * 获取亚层item节点的数据集合 <br>
	 * 
	 * @param originItemList
	 * @return
	 */
	public LinkedList<Item> getSecondItemList(JSONArray originItemList) {
		LinkedList<Item> itemsList = new LinkedList<Item>();

		for (Object itemNode : originItemList) {
			JSONObject jsonNode = (JSONObject) itemNode;
			JSONArray jsonArray = JSONArray.fromObject(jsonNode.get("item"));

			for (Object secondItem : jsonArray) {
				Item item = new Item();
				JSONObject secJsonItem = (JSONObject) secondItem;

				item.setName(secJsonItem.getString("name"));

				LinkedList<Request> requestNode = getRequestNodeFromSecondItem(
						secJsonItem);
				item.setRequest(requestNode);

				itemsList.add(item);
			}
		}
		return itemsList;
	}

	/**
	 * 从亚层item节点中获取request数据集合
	 * 
	 * @param secJsonItem
	 * @return
	 */
	public LinkedList<Request> getRequestNodeFromSecondItem(JSONObject secJsonItem) {
		LinkedList<Request> reqList = new LinkedList<Request>();

		JSONArray requestArray = JSONArray.fromObject(secJsonItem.get("request"));

		for (Object requestObj : requestArray) {
			Request request = new Request();
			JSONObject jsonRequestObj = (JSONObject) requestObj;

			request.setMethod(jsonRequestObj.getString("method"));

			LinkedList<Header> headerNode = getHeaderNodeFromRequest(jsonRequestObj);
			request.setHeader(headerNode);

			LinkedList<URL> urlNode = getUrlNodeFromRequest(jsonRequestObj);
			request.setUrl(urlNode);

			reqList.add(request);
		}
		return reqList;
	}

	/**
	 * 据文件路径,获取最顶层item节点的数据集合
	 * 
	 * @param filePath
	 * @return
	 */
	public JSONArray getOriginItemListByFile(String filePath) {
		String jsonStr = getJsonStr(filePath);
		JSONObject totalData = JSONObject.fromObject(jsonStr);
		String originItem = totalData.getString("item");
		JSONArray originItemNodeList = JSONArray.fromObject(originItem);

		return originItemNodeList;
	}

	/**
	 * TODO 最终敲定之执行方法
	 * 
	 * 获取最顶层item节点的数据集合
	 * 
	 * @param originItemNodeList
	 * @return
	 */
	public LinkedList<TopItem> getTopItemData(JSONArray originItemNodeList) {
		LinkedList<TopItem> topItemsList = new LinkedList<TopItem>();

		for (Object obj : originItemNodeList) {
			TopItem topItem = new TopItem();
			JSONObject jsonItem = (JSONObject) obj;

			LinkedList<Item> secondItemsList = getSecondItemFromTopItem(jsonItem);

			topItem.setDescription(jsonItem.getString("description"));
			topItem.setName(jsonItem.getString("name"));
			topItem.setItems(secondItemsList);

			topItemsList.add(topItem);
		}
		return topItemsList;
	}

	/**
	 * 从顶层item节点中获取亚层item节点数据集合
	 * 
	 * @param jsonItem
	 * @return
	 */
	public LinkedList<Item> getSecondItemFromTopItem(JSONObject jsonItem) {
		LinkedList<Item> itemsList = new LinkedList<Item>();

		JSONArray jsonArray = JSONArray.fromObject(jsonItem.get("item"));

		for (Object secondItem : jsonArray) {
			Item item = new Item();
			JSONObject secJsonItem = (JSONObject) secondItem;

			item.setName(secJsonItem.getString("name"));

			LinkedList<Request> requestNode = getRequestNodeFromSecondItem(
					secJsonItem);
			item.setRequest(requestNode);

			itemsList.add(item);
		}
		return itemsList;
	}

	/**
	 * 获取info节点中的指定元素数据(single node)
	 * 
	 * @param filePath
	 * @return
	 */
	public Info getInfoNodeData(String filePath) {
		Info information = new Info();

		String jsonStr = getJsonStr(filePath);
		JSONObject totalData = JSONObject.fromObject(jsonStr);

		String info = totalData.getString("info");
		JSONArray infoList = JSONArray.fromObject("[" + info + "]");

		JSONObject jObject = (JSONObject) infoList.get(0);
		information.setName(jObject.getString("name"));
		information.setDescription(jObject.getString("description"));

		return information;
	}

//	public static void main(String[] args) {
//		String fileURL = "/home/user/beforehandler/机房预约系统.postman_collection01.json";
//		JSONArray originItemList = doc.getOriginItemListByFile(fileURL);
//		LinkedList<TopItem> topItemData = doc.getTopItemData(originItemList);
//		for (TopItem topItem : topItemData) {
//			System.out.println(topItem.toString());
//		}
//	}
}

package org.apidoc.subscribe.handle;

import java.util.LinkedList;
import java.util.List;

import org.apidoc.subscribe.bo.Info;
import org.apidoc.subscribe.bo.Item;
import org.apidoc.subscribe.bo.Request;
import org.apidoc.subscribe.bo.TopItem;
import org.apidoc.subscribe.bo.URL;
import org.apidoc.subscribe.services.GetJsonObj;
import org.apidoc.subscribe.services.TextFileTool;

import net.sf.json.JSONArray;

/**
 * 调配运行,完成任务
 * 
 * @author user
 *
 */
public class DocumentHandler {
	static String json_file_url = "/home/user/beforehandler/postman_collection2.json";

	/**
	 * 
	 * @param topItemData
	 * @param information
	 * @return
	 */
	public String getHtmlContent(LinkedList<TopItem> topItemData, Info information) {
		StringBuilder bd = new StringBuilder();

		bd.append("<html>");
		bd.append("<head>");
		bd.append("<title>");
		bd.append(information.getName());
		bd.append("</title>");
		bd.append("</head>");

		bd.append("<body>");
		bd.append("<main>");
		bd.append("<h2>");
		bd.append(information.getName());
		bd.append("</h2>");

		bd.append("</div>");
		bd.append(information.getDescription());
		bd.append("</div>");

		// 勉强如此吧,不想在html上耗费过多
		bd.append("<div>");
		for (TopItem topItem : topItemData) {
			bd.append("<br><div>");
			bd.append("<h3>" + topItem.getName() + "</h3>");
			bd.append("<h4>" + topItem.getDescription() + "</h4>");
			bd.append("</div><br>");

			List<Item> itemsList = topItem.getItems();
			bd.append("<table>");
			for (Item item : itemsList) {
				bd.append("<tr>");

				bd.append("<td class=\"item-action-name\">");
				bd.append(item.getName());
				bd.append("</td>");

				List<Request> requestList = item.getRequest();
				for (Request req : requestList) {
					bd.append("<tr>");

					bd.append("<td>");
					bd.append(req.getMethod());
					bd.append("</td>");

					bd.append("<td>");
					bd.append(req.getHeader());
					bd.append("</td>");

					bd.append("<td>");

					List<URL> urlList = req.getUrl();
					for (URL url : urlList) {
						bd.append("<tr>");

						bd.append("<td>");
						bd.append(url.getPath());
						bd.append("</td>");

						bd.append("<td>");
						bd.append(url.getRaw());
						bd.append("</td>");

						bd.append("<td>");
						bd.append(url.getQuery());
						bd.append("</td>");

						bd.append("</tr>");
					}

					bd.append("</tr>");
				}
				bd.append("</td>");

				bd.append("</tr>");
			}
			bd.append("</table>");
		}
		bd.append("</div>");

		bd.append("</main>");
		bd.append("<body/>");
		bd.append("<html/>");

		bd.append("<style>");

		bd.append("body{font-size:28px;}");
		bd.append("table{border-collapse: collapse;}");
		bd.append("table, th, td {border: 1px solid black;}");
		bd.append("td {font-size: 30px;}");
		bd.append(".item-action-name {background: #b7e8d8;}");

		bd.append("</style>");

		return bd.toString();
	}

	public static void main(String[] args) {
		GetJsonObj jsonObj = new GetJsonObj();
		TextFileTool tool = new TextFileTool();
		DocumentHandler handler = new DocumentHandler();

		JSONArray originItemList = jsonObj.getOriginItemListByFile(json_file_url);
		LinkedList<TopItem> topItemData = jsonObj.getTopItemData(originItemList);

		Info infoNodeData = jsonObj.getInfoNodeData(json_file_url);

		String htmlUrl = tool.createFileByUrl();

		String htmlContent = handler.getHtmlContent(topItemData, infoNodeData);

		tool.writeContentToFile(htmlContent, htmlUrl);
	}
}

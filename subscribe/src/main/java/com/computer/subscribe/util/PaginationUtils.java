package com.computer.subscribe.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.TSubscribe;
import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;

/**
 * 处理分页实体对象的工具类
 * 
 * @author user
 *
 */
public class PaginationUtils {
	public static String has_previous_key = "has_previous";
	public static String has_next_key = "has_next";

	private static PaginationUtils paginationUtil;

	private static final Object LOCK = new Object();

	private PaginationUtils() {
		System.err.println(this.getClass() + "__PaginationUtils__私有化构造器,防止被实例化");
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static PaginationUtils getInstance() {
		if (paginationUtil == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (paginationUtil == null) {
					paginationUtil = new PaginationUtils();
				}
			}
		}
		return paginationUtil;
	}

	/**
	 * 分页查询时,对现实页码(1...n),与数据库索引(0...n)之间的对应关系进行调整
	 * 
	 * @param pageNum
	 * @return
	 */
	public int getPageNum(int pageNum) {
		System.err.println(this.getClass() + "__getPageNum__pageNum=" + pageNum);
		if (pageNum < 1) {
			pageNum = 1;
		}
		pageNum -= 1;
		return pageNum;
	}

	/**
	 * 针对用户实体对象来组装
	 * 
	 * @param pageData  分页数据(行形式显示)
	 * @param idCount   全部用户行数
	 * @param pageRows  每页显示行数
	 * @param pageOrder 当前页
	 * @return List<TUser>
	 */
	public Pagination<List<TUser>> assemblyUser(List<TUser> pageData,
			Integer idCount, Integer pageRows, Integer pageOrder) {
		Pagination<List<TUser>> pagin = new Pagination<List<TUser>>();

		// get total pages
		int totalPages = idCount / pageRows;
		// 余数
		int remainder = idCount % pageRows;

		if (remainder != 0) {// 如果行数限制不可以被总行数整除,实际页数+1
			totalPages++;
		}

		HashMap<String, Boolean> boolMap = getPageBoolMap(idCount, pageRows,
				totalPages, pageData.size(), pageOrder);

		Boolean hasPrevious = boolMap.get(has_previous_key);
		Boolean hasNext = boolMap.get(has_next_key);

		pagin.setCurrentPage(++pageOrder);
		pagin.setData(pageData);
		pagin.setHasNext(hasNext);
		pagin.setHasPrevious(hasPrevious);
		pagin.setRows(pageRows);
		pagin.setTotalPages(totalPages);

		return pagin;
	}

	/**
	 * 针对预约实体对象来组装
	 * 
	 * @param pageData  单页分页数据
	 * @param idCount   数据总行数
	 * @param pageRows  每页显示行数
	 * @param pageOrder 页码
	 * @return
	 */
	public Pagination<List<TSubscribe>> assemblySubscribe(List<TSubscribe> pageData,
			Integer idCount, Integer pageRows, Integer pageOrder) {
		Pagination<List<TSubscribe>> pagin = new Pagination<List<TSubscribe>>();
		// get total pages
		int totalPages = idCount / pageRows;

		// 余数
		int remainder = idCount % pageRows;

		if (remainder != 0) {// 如果行数限制不可以被总行数整除,实际页数+1
			totalPages++;
		}

		HashMap<String, Boolean> boolMap = getPageBoolMap(idCount, pageRows,
				totalPages, pageData.size(), pageOrder);

		Boolean hasPrevious = boolMap.get(has_previous_key);
		Boolean hasNext = boolMap.get(has_next_key);

		pagin.setCurrentPage(++pageOrder);
		pagin.setData(pageData);
		pagin.setHasNext(hasNext);
		pagin.setHasPrevious(hasPrevious);
		pagin.setRows(pageRows);
		pagin.setTotalPages(totalPages);

		return pagin;
	}

	/**
	 * 获取布尔哈希表,其中含有元素:是否存在上一页/是否存在下一页
	 * 
	 * @param idCount
	 * @param pageRows
	 * @param totalPages
	 * @param pageDataSize
	 * @param pageOrder
	 * @return
	 */
	public HashMap<String, Boolean> getPageBoolMap(int idCount, int pageRows,
			int totalPages, int pageDataSize, int pageOrder) {
		HashMap<String, Boolean> boolMap = new HashMap<String, Boolean>();

		// 余数
		int remainder = idCount % pageRows;
		System.out.println(this.getClass() + "__getPageBoolMap=>余数=" + remainder);

		Boolean hasPrevious = true;
		Boolean hasNext = true;

		if (remainder != 0) {// 如果行数限制不可以被总行数整除
			if (pageDataSize < pageRows) {// 如果 pageData.size < pageRows, 无下一页
				hasNext = false;
			}
		} else if (pageOrder <= 1) {// 如果 page order <= 1 ,无上一页
			hasPrevious = false;

		} else if (pageOrder < 1) {// 如果传入页码 0, 形同页码 1
			pageOrder = 1;

		} else if (remainder == 0) {// 如果每页行数限制能被总行数整除
			if (idCount <= pageOrder * pageRows) { // 如果当前页码*每页行数限制 >= 总行数, 无下一页
				hasNext = false;
			}
		}

		boolMap.put(has_previous_key, hasPrevious);
		boolMap.put(has_next_key, hasNext);

		for (Map.Entry<String, Boolean> entry : boolMap.entrySet()) {
			String key = entry.getKey();
			Boolean value = entry.getValue();
			System.err.println(this.getClass() + "__getPageBoolMap=>" + key
					+ ",value=" + value);
		}

		return boolMap;
	}

	/**
	 * 分页组装 - 电脑机房
	 * 
	 * @param pageData
	 * @param idCount
	 * @param pageRows
	 * @param pageOrder
	 * @return
	 */
	public Pagination<List<TComputerRoom>> assemblyComputerRoom(
			List<TComputerRoom> pageData, Integer idCount, Integer pageRows,
			Integer pageOrder) {
		Pagination<List<TComputerRoom>> pagin = new Pagination<List<TComputerRoom>>();
		// get total pages
		int totalPages = idCount / pageRows;

		// 余数
		int remainder = idCount % pageRows;

		if (remainder != 0) {// 如果行数限制不可以被总行数整除,实际页数+1
			totalPages++;
		}

		HashMap<String, Boolean> boolMap = getPageBoolMap(idCount, pageRows,
				totalPages, pageData.size(), pageOrder);

		Boolean hasPrevious = boolMap.get(has_previous_key);
		Boolean hasNext = boolMap.get(has_next_key);

		pagin.setCurrentPage(++pageOrder);
		pagin.setData(pageData);
		pagin.setHasNext(hasNext);
		pagin.setHasPrevious(hasPrevious);
		pagin.setRows(pageRows);
		pagin.setTotalPages(totalPages);

		System.err.println(this.getClass() + "__assemblyComputerRoom__pagin="
				+ pagin.toString());
		return pagin;
	}
	
}

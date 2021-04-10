package com.computer.subscribe.util;

import java.util.List;

import com.computer.subscribe.pojo.TUser;
import com.computer.subscribe.pojo.response.Pagination;

/**
 * 处理分页实体对象的工具类
 * 
 * @author user
 *
 */
public class PaginationUtils {
	private static PaginationUtils paginationUtil;

	private static final Object LOCK = new Object();

	private PaginationUtils() {
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
	 * 针对用户实体对象来组装
	 * 
	 * @param pageData  分页用户(行形式显示)
	 * @param idCount   全部用户行数
	 * @param pageRows  每页显示行数
	 * @param pageOrder 当前页
	 * @return List<TUser>
	 */
	public Pagination<List<TUser>> assembly(List<TUser> pageData, Integer idCount,
			Integer pageRows, Integer pageOrder) {
		Pagination<List<TUser>> pagin = new Pagination<List<TUser>>();

		Boolean hasPrevious = true;
		Boolean hasNext = true;

		// get total pages
		int totalPages = idCount / pageRows;
		// 余数
		int remainder = idCount % pageRows;
		System.out.println(this.getClass() + "==>余数== " + remainder);

		if (remainder != 0) {// 如果行数限制不可以被总行数整除
			totalPages++;

			if (pageData.size() < pageRows) {// 如果 pageData.size < pageRows, 无下一页
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

		pagin.setCurrentPage(pageOrder);
		pagin.setData(pageData);
		pagin.setHasNext(hasNext);
		pagin.setHasPrevious(hasPrevious);
		pagin.setRows(pageRows);
		pagin.setTotalPages(totalPages);

		return pagin;
	}

}

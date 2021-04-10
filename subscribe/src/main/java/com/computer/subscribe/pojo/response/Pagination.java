package com.computer.subscribe.pojo.response;

/**
 * 分页实体对象
 * 
 * @author user
 * @param <T>
 *
 */
public class Pagination<T> {
	/**
	 * constructor
	 */
	public Pagination() {
		this.currentPage = 0;
		this.data = null;
		this.hasNext = false;
		this.hasPrevious = false;
		this.rows = 0;
		this.totalPages = 0;
	}

	/**
	 * 是否有上一页
	 */
	private boolean hasPrevious;

	/**
	 * 是否有下一页
	 */
	private boolean hasNext;

	/**
	 * 总页数
	 */
	private int totalPages;

	/**
	 * 当前页码
	 */
	private int currentPage;

	/**
	 * 所要显示的行数
	 */
	private int rows;

	/**
	 * 数据
	 */
	private T data;

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pagination [hasPrevious=" + hasPrevious + ", hasNext=" + hasNext
				+ ", totalPages=" + totalPages + ", currentPage=" + currentPage
				+ ", rows=" + rows + ", data=" + data + "]";
	}

}

package com.computer.subscribe.pojo.response;

/**
 * 为分页而自定义设之
 * 
 * @author user
 *
 */
public class LimitExample {
	/**
	 * 分页查询出发行下标
	 */
	protected int offset;
	/**
	 * 每页行数显示限制
	 */
	protected int limit;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

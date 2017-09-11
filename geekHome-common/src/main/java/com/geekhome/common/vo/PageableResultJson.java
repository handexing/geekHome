package com.geekhome.common.vo;

import java.io.Serializable;

public class PageableResultJson implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 5544244021994468020L;

	public static final int PAGE_SIZE = 7;

	private long totalPageNumber;
	private int pageSize;
	private Object data;

	public Object getData() {
		return data;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalPageNumber(long totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	@Override
	public String toString() {
		return "DateTableJson [totalPageNumber=" + totalPageNumber + ", pageSize=" + pageSize + ", data=" + data + "]";
	}
	

}

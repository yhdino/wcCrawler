package com.zhsz.utils.persistent;

import java.util.List;

import com.zhsz.utils.utils.page.Pagination;

/**
 * 持久化结果集列表接口实现类
 */
public class ResultListImpl<T> implements ResultList<T> {

	private static final long serialVersionUID = -4590321979224243763L;

	public ResultListImpl() {

	}
	/** 返回记录列表 */
	private List<T> results;
	
	/** 返回二级分类选项列表 */
	private List<T> selects;

	/** 返回分页信息 */
	private Pagination page;

	public Pagination getPage() {
		return page;
	}

	public List<T> getResults() {
		return results;
	}
	
	public List<T> getSelects() {
		return selects;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public void setSelects(List<T> selects) {
		this.selects = selects;
	}

}

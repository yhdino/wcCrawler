package com.zhsz.utils.persistent;

import java.io.Serializable;

import java.util.List;

import com.zhsz.utils.utils.page.Pagination;


/**
 * 持久化结果集列表接口
 */
public interface ResultList<T> extends Serializable {
	
	/**
	 * 获取结果集
	 * @return 结果集列表
	 */
	public List<T> getResults() ;
	
	/**
	 * 获取选项列表集
	 * @return 选项集列表
	 */
	public List<T> getSelects() ;

	/**
	 * 设置结果集
	 * @param reuslts 结果集列表
	 */
	public void setResults(List<T> reuslts) ;
	
	/**
	 * 设置选项集
	 * @param reuslts 选项集列表
	 */
	public void setSelects(List<T> selects) ;

	/**
	 * 获取分页信息
	 * @return <code>Pagination</code> 对象
	 */
	public Pagination getPage() ;

	/**
	 * 设置分页信息
	 * @param page <code>Pagination</code> 对象
	 */
	public void setPage(Pagination page) ;
	
}

package com.zhsz.dao.entity.misc.ext;

/**
 * Register实体类的扩展类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public class RegisterExt {
	private String pro_child_name;
	private String pro_name;
	private String project_name;
	private String project_child_name;
	private String group_name;
	private String parent_pro_id;
	private String companyname;
	
	public String getParent_pro_id() {
		return parent_pro_id;
	}
	public void setParent_pro_id(String parent_pro_id) {
		this.parent_pro_id = parent_pro_id;
	}
	// 前台参数
	public String getPro_child_name() {
		return pro_child_name;
	}
	public void setPro_child_name(String pro_child_name) {
		this.pro_child_name = pro_child_name;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_child_name() {
		return project_child_name;
	}
	public void setProject_child_name(String project_child_name) {
		this.project_child_name = project_child_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	
	
}

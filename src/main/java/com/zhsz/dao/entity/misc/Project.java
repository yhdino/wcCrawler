package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.ProjectExt;

import java.sql.Timestamp;

/**
 * project数据库表名称
 * Project实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public class Project extends ProjectExt {
    // 数据库通用属性
    private String id;//
    private String project_name;//项目名
    private String num;//库存
    private String createuserid;//
    private Timestamp createtime;//
    private String updateuserid;//
    private Timestamp updatetime;//
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getProject_name() {
        return project_name;
    }
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
	public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
	public String getCreateuserid() {
        return createuserid;
    }
    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }
	public Timestamp getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
	public String getUpdateuserid() {
        return updateuserid;
    }
    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid;
    }
	public Timestamp getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}

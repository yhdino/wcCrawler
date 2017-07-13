package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.ProjectnewsExt;

import java.sql.Timestamp;

/**
 * projectnews数据库表名称
 * Projectnews实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public class Projectnews extends ProjectnewsExt {
    // 数据库通用属性
    private String id;//
    private String title;//标题
    private String summary;//新闻摘要
    private String content;//新闻内容
    private String image;//新闻图片
    private String parent_project_id;//父项目id
    private String child_project_id;//
    private Timestamp createtime;//创建时间（插入时间）
    private String createuserid;//创建人
    private Timestamp updatetime;//修改时间
    private String updateuserid;//修改人
    private String areaCode;//区域
    private String bz1;//横幅拦置顶
    private String bz2;//备注字段2
    private String bz3;//备注字段3
    private String bz4;//备注字段4
    private String bz5;//备注字段5
    private String bz6;//备注字段6
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
	public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
	public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
	public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
	public String getParent_project_id() {
        return parent_project_id;
    }
    public void setParent_project_id(String parent_project_id) {
        this.parent_project_id = parent_project_id;
    }
	public String getChild_project_id() {
        return child_project_id;
    }
    public void setChild_project_id(String child_project_id) {
        this.child_project_id = child_project_id;
    }
	public Timestamp getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
	public String getCreateuserid() {
        return createuserid;
    }
    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }
	public Timestamp getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
	public String getUpdateuserid() {
        return updateuserid;
    }
    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid;
    }
	public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
	public String getBz1() {
        return bz1;
    }
    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }
	public String getBz2() {
        return bz2;
    }
    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }
	public String getBz3() {
        return bz3;
    }
    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }
	public String getBz4() {
        return bz4;
    }
    public void setBz4(String bz4) {
        this.bz4 = bz4;
    }
	public String getBz5() {
        return bz5;
    }
    public void setBz5(String bz5) {
        this.bz5 = bz5;
    }
	public String getBz6() {
        return bz6;
    }
    public void setBz6(String bz6) {
        this.bz6 = bz6;
    }
}

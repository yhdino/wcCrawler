package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.SqnNewsExt;

import java.sql.Timestamp;

/**
 * sqn_news数据库表名称
 * SqnNews实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-07 14:53:36 
 */  
public class SqnNews extends SqnNewsExt {
    // 数据库通用属性
    private String id;//
    private String title;//园区新闻标题
    private String summary;//园区新闻摘要
    private String content;//园区新闻内容
    private String type;//新闻分类(0:文泽江苏 1:雏鹰伴翔 2:草绿天蓝 3:起航100 4:青联智汇)
    private String childtype;//子类型
    private String starttime;//活动开始时间
    private String endtime;//
    private String num;//报名人数
    private String totalcount;//点击数
    private String img;//园区新闻图片1
    private Timestamp createtime;//创建时间（插入时间）
    private String createuserid;//创建人
    private Timestamp updatetime;//修改时间
    private String updateuserid;//修改人
    private String areaCode;//区域
    private String bz1;//备注字段1
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
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	public String getChildtype() {
        return childtype;
    }
    public void setChildtype(String childtype) {
        this.childtype = childtype;
    }
	public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
	public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
	public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
	public String getTotalcount() {
        return totalcount;
    }
    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }
	public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
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

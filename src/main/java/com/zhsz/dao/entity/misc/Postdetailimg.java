package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.PostdetailimgExt;

import java.sql.Timestamp;

/**
 * postdetailimg数据库表名称
 * Postdetailimg实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-26 17:32:56 
 */  
public class Postdetailimg extends PostdetailimgExt {
    // 数据库通用属性
    private String id;//
    private String oldpath;//原始图片地址
    private String newpath;//新的图片地址
    private String fingerprint;//
    private Timestamp createtime;//创建时间（插入时间）
    private String createuserid;//创建人
    private Timestamp updatetime;//修改时间
    private String updateuserid;//修改人
    private String bz1;//备注字段1
    private String bz2;//备注字段2
    private String bz3;//备注字段3
    private String bz4;//备注字段4
    private String bz5;//备注字段5
    private String bz6;//备注字段6
	private String contentid;
    
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getOldpath() {
        return oldpath;
    }
    public void setOldpath(String oldpath) {
        this.oldpath = oldpath;
    }
	public String getNewpath() {
        return newpath;
    }
    public void setNewpath(String newpath) {
        this.newpath = newpath;
    }
	public String getFingerprint() {
        return fingerprint;
    }
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
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
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
    
    
    
}
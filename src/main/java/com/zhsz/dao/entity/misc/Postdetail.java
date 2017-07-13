package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.PostdetailExt;

import java.sql.Timestamp;

/**
 * postdetail数据库表名称
 * Postdetail实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-16 15:32:57 
 */  
public class Postdetail extends PostdetailExt {
    // 数据库通用属性
    private String id;//
    private String content;//文章内容
    private String sn;//
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
    private String searchkey;//theme表的id
    private String removecontent;
    private String status;
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
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
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public String getRemovecontent() {
		return removecontent;
	}
	public void setRemovecontent(String removecontent) {
		this.removecontent = removecontent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}

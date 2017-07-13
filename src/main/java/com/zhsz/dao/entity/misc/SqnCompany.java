package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.SqnCompanyExt;

import java.sql.Timestamp;

/**
 * sqn_company数据库表名称
 * SqnCompany实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-01 14:35:08 
 */  
public class SqnCompany extends SqnCompanyExt {
    // 数据库通用属性
    private String id;//
    private String companyname;//名字
    private String lxr;//联系人
    private String lxdz;//联系地址
    private String lxdh;//联系电话
    private String cityid;//所在城市id
    private String remark;//公司简介
    private Timestamp createtime;//创建时间
    private String createuserid;//创建人
    private Timestamp updatetime;//修改时间
    private String updateuserid;//修改人
    private String bz1;//备注字段1
    private String bz2;//备注字段2
    private String bz3;//备注字段3
    private String bz4;//备注字段4
    private String bz5;//备注字段5
    private String bz6;//备注字段6
    private String icon;//公司logo
    private String registerid;
    private String letter;
    
    
	
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getRegisterid() {
		return registerid;
	}
	public void setRegisterid(String registerid) {
		this.registerid = registerid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getCompanyname() {
        return companyname;
    }
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
	public String getLxr() {
        return lxr;
    }
    public void setLxr(String lxr) {
        this.lxr = lxr;
    }
	public String getLxdz() {
        return lxdz;
    }
    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }
	public String getLxdh() {
        return lxdh;
    }
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
	public String getCityid() {
        return cityid;
    }
    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
	public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
}

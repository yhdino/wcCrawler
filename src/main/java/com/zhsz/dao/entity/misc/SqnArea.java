package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.SqnAreaExt;

import java.sql.Timestamp;

/**
 * zhyq_area数据库表名称
 * ZhyqArea实体类
 * @author 刘浩
 * @Date Date: 2016-04-19 11:03:43 
 */  
public class SqnArea extends SqnAreaExt {
    // 数据库通用属性
    private String id;//主键ID
    private String areaCode;//园区编码
    private String areaName;//园区名称
    private String displayNum;//序列
    private double longitude;//经度
    private double latitude;//纬度
    private String tel;//电话
    private String img;//LOGO/园区二维码
    private String address;//地址
    private String remark;//园区备注
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
    private String belongoucode;//所属区域code
    private String property;//物业费用
    
    
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDisplayNum() {
        return displayNum;
    }
    public void setDisplayNum(String displayNum) {
        this.displayNum = displayNum;
    }
	public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getBelongoucode() {
		return belongoucode;
	}
	public void setBelongoucode(String belongoucode) {
		this.belongoucode = belongoucode;
	}
    
    
}
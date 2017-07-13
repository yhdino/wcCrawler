package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.ProjectChildExt;

import java.sql.Timestamp;

/**
 * project_child数据库表名称
 * ProjectChild实体类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
public class ProjectChild extends ProjectChildExt {
    // 数据库通用属性
    private String id;//
    private String parent_project_id;//父项目id
    private String project_child_name;//项目名称
    private String num;//
    private String createuserid;//
    private Timestamp createtime;//
    private String updateuserid;//
    private Timestamp updatetime;//
    private String remark;
    private String groupname;//组长姓名
    private String lxdh;//联系电话
    private String code;//二维码
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getParent_project_id() {
        return parent_project_id;
    }
    public void setParent_project_id(String parent_project_id) {
        this.parent_project_id = parent_project_id;
    }
	public String getProject_child_name() {
        return project_child_name;
    }
    public void setProject_child_name(String project_child_name) {
        this.project_child_name = project_child_name;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    
    
    
}

package com.zhsz.dao.entity.misc;

import com.zhsz.dao.entity.misc.ext.PostExt;

import java.sql.Timestamp;

/**
 * post数据库表名称
 * Post实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-08 13:15:46 
 */  
public class Post extends PostExt {
    // 数据库通用属性
    private String id;//
    private String biz;//文章对应的公众号biz
    private String field_id;//微信定义的一个id，每条文章唯一
    private String title;//文章标题
    private String title_encode;//文章编码，防止文章出现emoji
    private String digest;//文章摘要
    private String content_url;//文章地址
    private String source_url;//阅读原文地址
    private String cover;//封面图片
    private String is_multi;//是否多图文
    private String is_top;//是否头条
    private String datetime;//文章时间戳
    private String readNum;//文章阅读量
    private String likeNum;//文章点赞量
    private String type;//
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
    private String content_id;
    private String sn;
	
    
    
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getBiz() {
        return biz;
    }
    public void setBiz(String biz) {
        this.biz = biz;
    }
	public String getField_id() {
        return field_id;
    }
    public void setField_id(String field_id) {
        this.field_id = field_id;
    }
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
	
	public String getTitle_encode() {
		return title_encode;
	}
	public void setTitle_encode(String title_encode) {
		this.title_encode = title_encode;
	}
	public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
	public String getContent_url() {
        return content_url;
    }
    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
	public String getSource_url() {
        return source_url;
    }
    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
	public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
	public String getIs_multi() {
        return is_multi;
    }
    public void setIs_multi(String is_multi) {
        this.is_multi = is_multi;
    }
	public String getIs_top() {
        return is_top;
    }
    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }
	public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
	public String getReadNum() {
        return readNum;
    }
    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }
	public String getLikeNum() {
        return likeNum;
    }
    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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

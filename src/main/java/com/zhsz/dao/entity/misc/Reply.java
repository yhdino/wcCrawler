package com.zhsz.dao.entity.misc;

import java.sql.Timestamp;

import com.zhsz.dao.entity.misc.ext.ReplyExt;

/**
 * reply数据库表名称
 * Reply实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-19 15:43:12 
 */  
public class Reply extends ReplyExt {
    // 数据库通用属性
    private String id;//
    private String url;//
    private String biz;//文章对应的公众号biz
    private String mid;//微信定义的一个id，每条文章唯一
    private String nick_name;//
    private String logo_url;//文章标题
    private String content;//文章编码，防止文章出现emoji
    private String content_id;//文章摘要
    private String create_time;//文章地址
    private String like_num;//阅读原文地址
    private String is_from_friend;//封面图片
    private String reply;//是否多图文
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
	private int positive = 0;
	private int negative = 0;
	private int neutral = 0;
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
	public String getBiz() {
        return biz;
    }
    public void setBiz(String biz) {
        this.biz = biz;
    }
	public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
	public String getNick_name() {
        return nick_name;
    }
    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
	public String getLogo_url() {
        return logo_url;
    }
    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
	public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
	public String getContent_id() {
        return content_id;
    }
    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }
	public String getCreate_time() {
        return create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
	public String getLike_num() {
        return like_num;
    }
    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }
	public String getIs_from_friend() {
        return is_from_friend;
    }
    public void setIs_from_friend(String is_from_friend) {
        this.is_from_friend = is_from_friend;
    }
	public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
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
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getNegative() {
		return negative;
	}
	public void setNegative(int negative) {
		this.negative = negative;
	}
	public int getNeutral() {
		return neutral;
	}
	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}
    
    
    
}

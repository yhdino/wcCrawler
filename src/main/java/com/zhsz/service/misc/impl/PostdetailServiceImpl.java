package com.zhsz.service.misc.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsz.dao.entity.misc.Post;
import com.zhsz.dao.entity.misc.Postdetail;
import com.zhsz.dao.mapper.misc.PostMapper;
import com.zhsz.dao.mapper.misc.PostdetailMapper;
import com.zhsz.service.misc.PostdetailService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.test.GloablParam;
import com.zhsz.utils.utils.JsonUtils;
import com.zhsz.utils.utils.StringUtils;




/**
 * postdetail数据库表名称
 * Postdetail业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-16 15:32:57 
 */  
@Service
public class PostdetailServiceImpl implements PostdetailService {
    // 引用DAO层
    @Autowired
    private PostdetailMapper postdetailMapper;
	@Autowired
	private PostMapper postMapper;
    
    @Override
	public int doInsert(Postdetail t) throws ServiceException {
		try {
			return postdetailMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Postdetail t) throws ServiceException {
		try {
			return postdetailMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Postdetail> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return postdetailMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Postdetail> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return postdetailMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doInsertPostDetail(String content, String url,String json) throws ServiceException {
		Map<String, Object> map = null;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			Timestamp nousedate = new Timestamp(new Date().getTime());
			Postdetail postdetail = new Postdetail();
			postdetail.setContent(content);
			postdetail.setCreatetime(nousedate);
			postdetail.setId(UUID.randomUUID().toString());
			postdetail.setStatus("0");
			//获取url中的sn
			if(StringUtils.isNotEmpty(url)){
				int startindex  = url.indexOf("&sn=");
				int endIndex = url.indexOf("&",url.indexOf("&sn=")+4);
				postdetail.setSn(url.substring(startindex+4, endIndex));
			}
			int n = 0;
			if(StringUtils.isNotEmpty(json)){
				map = JsonUtils.json2map(json);
				String searchkey = (String)map.get("searchkey");
				String days = (String)map.get("days");
				
				Matcher matcher = Pattern.compile("publish_time = (.*?);").matcher(content);
		        String articletime = "";
		        while (matcher.find()) {
		        	articletime = matcher.group();
		        }
				int pub = articletime.indexOf("publish_time");
				int start = articletime.indexOf("\"",pub);
				articletime = articletime.substring(start+1,start+11);
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date artime = sdf.parse(articletime);
				Long daytime = Long.parseLong(days)*24*60*60*1000;//转化days的位long值
				Long endtime = artime.getTime()+daytime;//获取到期时间的long值
				Long nowtime = new Date().getTime();//获取当前时间
				if(nowtime>endtime){
					GloablParam.setPost("0");
				}else{
					condition.put("sn", postdetail.getSn());
					Long count = postdetailMapper.doQueryCountByCondition(condition);
					if(count==0){
						postdetail.setSearchkey(searchkey);
						n=postdetailMapper.doInsert(postdetail);
						//分析content并插入到post中
						try{
							insertPost(content);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}else{
				condition.put("sn", postdetail.getSn());
				Long count = postdetailMapper.doQueryCountByCondition(condition);
				if(count==0){
					n=postdetailMapper.doInsert(postdetail);
				}
			}
			return n;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public void insertPost(String content) throws SQLException {
		
			Timestamp nousedate = new Timestamp(new Date().getTime());
			content = content.replace("\\x26amp;", "&");
			//biz
			String biz=subInfo(content, "var biz");
			//sn
			String sn = subInfo(content, "sn");
			//mid
			String bz1 = subInfo(content, "mid");
			//title
			String title = subInfo(content, "msg_title");
			//digest
			String digest = subInfo(content, "msg_desc");
			//content_url
			String content_url = subInfo(content, "msg_link");
			//cover 
			String cover = subInfo(content, "msg_cdn_url");
			//datetime
			String datetime = subInfo(content, "ct");
			
			Post post = new Post();
			post.setId(UUID.randomUUID().toString());
			post.setBiz(biz);
			post.setBz1(bz1);
			post.setContent_url(content_url);
			post.setCover(cover);
			post.setCreatetime(nousedate);
			post.setDatetime(datetime);
			post.setDigest(digest);
			post.setSn(sn);
			post.setTitle(title);
			post.setIs_multi("1");//默认值
			post.setType("999");//默认值
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("sn", sn);
			Long count = postMapper.doQueryCountByCondition(condition);
			if(count==0){
				postMapper.doInsert(post);
			}
		
    }
	
	
	public String subInfo(String content,String keyword){
		Matcher matcher= Pattern.compile(keyword+" = (.*?);").matcher(content);
        String param = "";
        while (matcher.find()) {
        	param = matcher.group();
        }
		int a = param.indexOf(keyword);
		int start = param.indexOf("\"",a)+1;
		int end = param.indexOf("\"",start);
		param = param.substring(start,end);
		return param;
	}

	@Override
	public List<Postdetail> doQueryByPostImg(Map<String, Object> condition) throws ServiceException {
		try {
			return postdetailMapper.doQueryByPostImg(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Postdetail> doQueryByTheme(Map<String, Object> condition) throws ServiceException {
		try {
			return postdetailMapper.doQueryByTheme(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, PostdetailServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
}

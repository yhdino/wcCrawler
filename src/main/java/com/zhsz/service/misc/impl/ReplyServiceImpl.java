package com.zhsz.service.misc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhsz.dao.entity.misc.Reply;
import com.zhsz.dao.mapper.misc.ReplyMapper;
import com.zhsz.service.misc.ReplyService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.utils.EmojiFilter;
import com.zhsz.utils.utils.JsonUtils;


import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static com.zhsz.controller.test.TestController.replyQueue;

/**
 * reply数据库表名称
 * Reply业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-19 15:43:12 
 */  
@Service
public class ReplyServiceImpl implements ReplyService {
    // 引用DAO层
    @Autowired
    private ReplyMapper replyMapper;
	
    @Override
	public int doInsert(Reply t) throws ServiceException {
		try {
			return replyMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Reply t) throws ServiceException {
		try {
			return replyMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return replyMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Reply> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return replyMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Reply> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return replyMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public String doInsertReply(String json, String url) throws ServiceException{
		int n = 0;
		try {
			
			Timestamp nousedate = new Timestamp(new Date().getTime());

			

			//json
			JSONObject obj = JSONObject.parseObject(json);
//			JSONArray array = (JSONArray) JSON.parseArray((String) obj.get("elected_comment"));
			JSONArray array = (JSONArray) obj.get("elected_comment");
			if(array.size()!=0){
				Reply reply = new Reply();
				//url
				if(StringUtils.isNotEmpty(url)){
					int startindex  = url.indexOf("biz=");
					int endIndex = url.indexOf("&",url.indexOf("biz=")+4);
					reply.setBiz(url.substring(startindex+4, endIndex));
				}

				Map<String, Object> condition= new HashMap<String, Object>();
				for(int i=0;i<array.size();i++){
					Map<String, Object> map_elected_comment = JsonUtils.json2map(array.getJSONObject(i).toString());
					String content_id = (String)map_elected_comment.get("content_id").toString();
					condition.put("content_id", content_id);
					Long count = replyMapper.doQueryCountByCondition(condition);
					if(count>0){
						continue;
					}
					String nick_name = EmojiFilter.resolveToByteFromEmoji((String)map_elected_comment.get("nick_name").toString());
					String logo_url = (String)map_elected_comment.get("logo_url").toString();
					String content = (String)map_elected_comment.get("content").toString();
					String create_time = (String)map_elected_comment.get("create_time").toString();
					String like_num = (String)map_elected_comment.get("like_num").toString();
					String is_from_friend = (String)map_elected_comment.get("is_from_friend").toString();
					String newreply = (String)map_elected_comment.get("reply").toString();
					if(StringUtils.isNotEmpty(url)){
						int startindex  = url.indexOf("appmsgid=");
						int endIndex = url.indexOf("&",url.indexOf("appmsgid=")+9);
						reply.setMid(url.substring(startindex+9, endIndex));
					} 
					reply.setUrl(url);
					reply.setId(UUID.randomUUID().toString());
					reply.setContent(content);
					reply.setContent_id(content_id);
					reply.setCreate_time(create_time);
					reply.setCreatetime(nousedate);
					reply.setIs_from_friend(is_from_friend);
					reply.setLike_num(like_num);
					reply.setLogo_url(logo_url);
					reply.setNick_name(nick_name);
					reply.setReply(newreply);

					replyMapper.doInsert(reply);
					replyQueue.add(new String(JsonUtils.obj2json(reply)));
					n++;
				}
			}
			return "true"+","+n;
		}catch (Exception e) {
			e.printStackTrace();
			return "false"+","+n;
		}
	}

	@Override
	public List<Reply> doQueryByReply(Map<String, Object> condition) throws ServiceException {
		try {
			return replyMapper.doQueryByReply(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, ReplyServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}

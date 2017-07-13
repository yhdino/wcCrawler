package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.SqnNews;
import com.zhsz.dao.mapper.misc.SqnNewsMapper;
import com.zhsz.service.misc.SqnNewsService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.utils.FileUtil;
import com.zhsz.utils.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * sqn_news数据库表名称
 * SqnNews业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-06-07 14:53:36 
 */  
@Service
public class SqnNewsServiceImpl implements SqnNewsService {
    // 引用DAO层
    @Autowired
    private SqnNewsMapper sqnNewsMapper;
	
    @Override
	public int doInsert(SqnNews t) throws ServiceException {
		try {
			return sqnNewsMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(SqnNews t) throws ServiceException {
		try {
			return sqnNewsMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return sqnNewsMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SqnNews> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		try {
			return sqnNewsMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SqnNews> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return sqnNewsMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public void doInsertNews(SqnNews t, String createId, HttpServletRequest request) throws ServiceException {
		try {
			String url="";
			List<SqnNews> list = null;
			if(!StringUtils.isEmpty(t.getId())){  //修改
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("id", t.getId());
				condition.put("minnum", 0);
				condition.put("maxnum", 10);
				list = sqnNewsMapper.doQueryByCondition(condition);
				url = list.get(0).getImg();  //获取原来的图片地址
			}
			String filePath = "";
    		//创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断 request 是否有文件上传,即多部分请求
            if(multipartResolver.isMultipart(request)){
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while(iter.hasNext()){
                    //取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    filePath = FileUtil.saveFile(file,(MultipartHttpServletRequest)request,"companylogo");
                }
            }
			request.setCharacterEncoding("utf-8");
			String uuid = UUID.randomUUID().toString();
			Timestamp nousedate = new Timestamp(new Date().getTime());
			if("" == (filePath)){
				filePath = url;
			}
			if (StringUtils.isEmpty(t.getId())) { // 新增
				t.setId(uuid); // 设置ID
				t.setImg(filePath);
				t.setCreatetime(nousedate); // 设置创建时间
				t.setCreateuserid(createId); // 设置创建人
				sqnNewsMapper.doInsert(t);
			} else { // 修改
				t.setImg(filePath);
				t.setUpdatetime(nousedate); // 设置修改时间
				t.setUpdateuserid(createId); // 设置修改人
				t.setCreateuserid(list.get(0).getCreateuserid());
				t.setCreatetime(list.get(0).getCreatetime());
				sqnNewsMapper.doUpdate(t);
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doDelete(Map<String, Object> condition) throws ServiceException {
		try {
			return sqnNewsMapper.doDelete(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnNewsServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}

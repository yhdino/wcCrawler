package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.SqnArea;
import com.zhsz.dao.mapper.misc.SqnAreaMapper;
import com.zhsz.service.misc.SqnAreaService;
import com.zhsz.utils.data.Globals;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * zhyq_area数据库表名称
 * ZhyqArea业务实现类
 * @author 刘浩
 * @Date Date: 2016-04-19 11:03:43 
 */  
@Service
public class SqnAreaServiceImpl implements SqnAreaService {
    // 引用DAO层
    @Autowired
    private SqnAreaMapper sqnAreaMapper;
	
    @Override
	public void doInsert(SqnArea dnmfArea, String createId, HttpServletRequest request) throws ServiceException {
    	try {
			String url="";
			if(!StringUtils.isEmpty(dnmfArea.getId())){  //修改
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("id", dnmfArea.getId());
				condition.put("minnum", 0);
				condition.put("maxnum", 10);
				List<SqnArea> list = sqnAreaMapper.doQueryByCondition(condition);
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
                    filePath = FileUtil.saveFile(file,(MultipartHttpServletRequest)request,Globals.PIC_DIR_HD);
                }
            }
			request.setCharacterEncoding("utf-8");
			String uuid = UUID.randomUUID().toString();
			Timestamp nousedate = new Timestamp(new Date().getTime());
			if("" == (filePath)){
				filePath = url;
			}
			if (StringUtils.isEmpty(dnmfArea.getId())) { // 新增
				dnmfArea.setId(uuid); // 设置ID
				dnmfArea.setImg(filePath);
				dnmfArea.setCreatetime(nousedate); // 设置创建时间
				dnmfArea.setCreateuserid(createId); // 设置创建人
				dnmfArea.setUpdatetime(nousedate); // 设置修改时间
				dnmfArea.setUpdateuserid(createId); // 设置修改人
				sqnAreaMapper.doInsert(dnmfArea);
			} else { // 修改
				dnmfArea.setImg(filePath);
				dnmfArea.setUpdatetime(nousedate); // 设置修改时间
				dnmfArea.setUpdateuserid(createId); // 设置修改人
				sqnAreaMapper.doUpdate(dnmfArea);
			}
		} catch (Exception e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(SqnArea t) throws ServiceException {
		try {
			return sqnAreaMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByCondition(Map<String, Object> condition) throws ServiceException {
		try {
			return sqnAreaMapper.doQueryCountByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SqnArea> doQueryByCondition(Map<String, Object> condition) throws ServiceException {
		try {
			return sqnAreaMapper.doQueryByCondition(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SqnArea> doQueryByConditionNoPage(Map<String, Object> condition) throws ServiceException {
		try {
			return sqnAreaMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int DeleteById(Map<String, Object> condition) throws SQLException {
		try {
			return sqnAreaMapper.DeleteById(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, SqnAreaServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	
}
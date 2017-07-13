package com.zhsz.service.manage.log.impl;

import com.zhsz.dao.entity.manage.log.LogEntity;
import com.zhsz.dao.mapper.manage.log.ILogMapper;
import com.zhsz.service.manage.log.ILogService;
import com.zhsz.service.manage.role.impl.OperatorServiceImpl;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.persistent.ResultListImpl;
import com.zhsz.utils.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = ILogService.SERVICE_NAME)
public class LogServiceImpl implements ILogService {

	@Autowired
	private ILogMapper logMapper;

	@Override
	@Transactional(readOnly = true)
	public LogEntity getLogById(String id) throws ServiceException {
		LogEntity log = null;
		try {
			log = logMapper.getLogById(id);
		} catch (SQLException e) {
			ZhszLogger.error("根据ID查找日志出错", e, OperatorServiceImpl.class);
			throw new ServiceException(e);
		}
		return log;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultList<LogEntity> getLogs(String operatorId,
			String operatorName, String beginTime, String endTime,
			Pagination page) throws ServiceException {
		Map<String, Object> condition = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		condition.put("operatorId", operatorId);
		condition.put("operatorName", operatorName);
		condition.put("beginTime", beginTime);
		condition.put("endTime", endTime);
		Long totalCount;
		try {
			totalCount = logMapper.logCount(condition);
			page.setTotalCount(totalCount.intValue());
		} catch (SQLException e) {
			ZhszLogger.error("查找日志数量出错", e, OperatorServiceImpl.class);
			throw new ServiceException(e);
		}

		condition.put("minnum", page.getStartNo());
		condition.put("maxnum", page.getPageSize());
		List<LogEntity> list;
		ResultList<LogEntity> res = new ResultListImpl<LogEntity>();
		try {
			list = logMapper.queryLogs(condition);
			for(LogEntity a:list){
				a.setTime1(sdf.format(a.getTime()));
			}
			res.setPage(page);
			res.setResults(list);
		} catch (SQLException e) {
			ZhszLogger.error("查询日志列表出错", e, OperatorServiceImpl.class);
			throw new ServiceException(e);
		}
		return res;
	}
	
	@Override
    @Transactional(readOnly = true)
    public ResultList<LogEntity> getLogsExcel(String operatorId,
            String operatorName, String beginTime, 
            String endTime) throws ServiceException {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("operatorId", operatorId);
        condition.put("operatorName", operatorName);
        condition.put("beginTime", beginTime);
        condition.put("endTime", endTime);
        condition.put("minnum", 0);
        condition.put("maxnum", 10000);
        List<LogEntity> list;
        ResultList<LogEntity> res = new ResultListImpl<LogEntity>();
        try {
            list = logMapper.queryLogs(condition);
            res.setResults(list);
        } catch (SQLException e) {
        	ZhszLogger.error("查询日志列表出错", e, OperatorServiceImpl.class);
            throw new ServiceException(e);
        }
        return res;
    }

	@Override
	@Transactional
	public void addLog(LogEntity log) throws ServiceException {
		try {
			logMapper.insertLog(log);
		} catch (SQLException e) {
			ZhszLogger.error("添加日志出错", e, OperatorServiceImpl.class);
			throw new ServiceException(e);
		}

	}

}

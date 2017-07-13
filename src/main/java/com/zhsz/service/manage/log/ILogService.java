package com.zhsz.service.manage.log;

import com.zhsz.dao.entity.manage.log.LogEntity;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.utils.page.Pagination;

public interface ILogService {

	public static final String SERVICE_NAME = "com/zhsz/service/manage/log/ILogService";

	public void addLog(LogEntity log) throws ServiceException;

	public LogEntity getLogById(String id) throws ServiceException;

	public ResultList<LogEntity> getLogs(String operatorId,
										 String operatorName, String beginTime, String endTime,
										 Pagination page) throws ServiceException;
	
	public ResultList<LogEntity> getLogsExcel(String operatorId,
											  String operatorName, String beginTime,
											  String endTime) throws ServiceException;

}

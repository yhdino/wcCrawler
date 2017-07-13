package com.zhsz.dao.mapper.manage.log;

import com.zhsz.dao.entity.manage.log.LogEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ILogMapper {
	
	public void insertLog(LogEntity log) throws SQLException;

	public void updateLog(LogEntity log) throws SQLException;

	public void deleteLog(String id) throws SQLException;

	public LogEntity getLogById(String id) throws SQLException;

	public Long logCount(Map<String, Object> condition)
			throws SQLException;

	public List<LogEntity> queryLogs(Map<String, Object> condition)
			throws SQLException;

}
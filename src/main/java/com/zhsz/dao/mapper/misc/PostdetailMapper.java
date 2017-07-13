package com.zhsz.dao.mapper.misc;  
  
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zhsz.dao.entity.misc.Postdetail;
  
/**
 * postdetail数据库表名称
 * Postdetail实体类
 * @author 黄冬杰
 * @Date Date: 2017-06-16 15:32:57 
 */  
public interface PostdetailMapper {
    int doInsert(Postdetail t) throws SQLException;
    int doUpdate(Postdetail t) throws SQLException;
    long doQueryCountByCondition(Map<String, Object> condition) throws SQLException;
    List<Postdetail> doQueryByCondition(Map<String, Object> condition) throws SQLException;
    List<Postdetail> doQueryByConditionNoPage(Map<String, Object> condition) throws SQLException;
    
    List<Postdetail> doQueryByPostImg(Map<String, Object> condition) throws SQLException;
    List<Postdetail> doQueryByTheme(Map<String, Object> condition) throws SQLException;
}
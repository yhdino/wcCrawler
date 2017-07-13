package com.zhsz.service.misc.impl;

import com.zhsz.dao.entity.misc.ProjectChild;
import com.zhsz.dao.entity.misc.Register;
import com.zhsz.dao.mapper.misc.ProjectChildMapper;
import com.zhsz.dao.mapper.misc.RegisterMapper;
import com.zhsz.service.misc.RegisterService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.logger.ZhszLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * register数据库表名称
 * Register业务实现类
 * @author 黄冬杰
 * @Date Date: 2017-03-23 09:37:58 
 */  
@Service
public class RegisterServiceImpl implements RegisterService {
    // 引用DAO层
    @Autowired
    private RegisterMapper registerMapper;
	@Autowired
	private ProjectChildMapper projectChildMapper;
    
    
    @Override
	public int doInsert(Register t) throws ServiceException {
		try {
			return registerMapper.doInsert(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doUpdate(Register t) throws ServiceException {
		try {
			return registerMapper.doUpdate(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
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
	public List<Register> doQueryByCondition(Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Register> doQueryByConditionNoPage(
			Map<String, Object> condition) throws ServiceException {
		try {
			return registerMapper.doQueryByConditionNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public long doQueryCountByProid(Map<String, Object> condition) throws ServiceException {
		try {
			return registerMapper.doQueryCountByProid(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)//事务
	public String doInsertRegister(Map<String, Object> map) throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			Date date = new Date();
			Timestamp nousedate = new Timestamp(date.getTime());
			String pro_id = (String)map.get("pro_id");
			String openid = (String)map.get("openid");
			String name = (String) map.get("name");
			String tel = (String) map.get("tel");
			String group = (String) map.get("group");
			String email = (String) map.get("email");
			String parent_project_id = (String)map.get("parent_project_id");
			condition.put("parent_project_id", parent_project_id);
			List<ProjectChild> list = projectChildMapper.doQueryByConditionNoPage(condition);
			int totalnum = 0;
			ProjectChild prochild = new ProjectChild();
			for(ProjectChild projectChild:list){
				totalnum+=Integer.parseInt(projectChild.getRegister_num());
				if(projectChild.getId().equals(pro_id)){
					prochild = projectChild;
				}
			}
			if(Integer.parseInt(list.get(0).getParent_num()) > totalnum
			    && Integer.parseInt(prochild.getNum()) > Integer.parseInt(prochild.getRegister_num())){
				Register re = new Register();
				re.setId(UUID.randomUUID().toString());
				re.setOpenid(openid);
				re.setGroupid(group);
				re.setName(name);
				re.setPro_id(pro_id);
				re.setTelphone(tel);
				re.setEmail(email);
				re.setCreatetime(nousedate);
				registerMapper.doInsert(re);
				return "000";//插入成功
			}else{
				return "001";//已满不能插
			}
		} catch (Exception e) {
			ZhszLogger.error("省青年项目加入失败", e, RegisterServiceImpl.class);
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Register> doQueryByConditionInfo(Map<String, Object> condition) throws ServiceException {
		try {
			return registerMapper.doQueryByConditionInfo(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}

	@Override
	public int doDelete(Register t) throws ServiceException {
		try {
			return registerMapper.doDelete(t);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public long doQueryRegisterCount(Map<String, Object> condition) throws SQLException{
		try {
			return registerMapper.doQueryRegisterCount(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
    public List<Register> doQueryRegister(Map<String, Object> condition) throws SQLException{
		try {
			return registerMapper.doQueryRegister(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
	
	@Override
    public List<Register> doQueryRegisterNoPage(Map<String, Object> condition) throws SQLException{
		try {
			return registerMapper.doQueryRegisterNoPage(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
    
	@Override
    public int deleteById(Map<String, Object> condition) throws SQLException{
		try {
			return registerMapper.deleteById(condition);
		} catch (SQLException e) {
			ZhszLogger.error("", e, RegisterServiceImpl.class);
			throw new ServiceException(e);
		}
	}
}

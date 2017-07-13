package com.zhsz.utils.utils.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

 
public class DBHelper {

	private static Logger logger = LogManager.getLogger(DBHelper.class);
	private DBHelper() {
	}

	private static DataSource datasource = new DataSource();

	static {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://192.168.10.92:3306/sqn?useUnicode=true&characterEncoding=UTF-8");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("root");
		p.setPassword("root");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(true);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(5000);
		p.setMaxActive(200);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(3600);
		p.setMinEvictableIdleTimeMillis(5000);
		p.setMinIdle(10);
		p.setRemoveAbandoned(true);
		p.setRemoveAbandonedTimeout(120);
		p.setLogAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		datasource.setPoolProperties(p);
	}

	public static final Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	public static final void close(Statement statement, Connection conn) {
		closeStatement(statement);
		closeConnection(conn);
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	/**********以下为对DB的操作**********/
	public static final boolean tableExist(String tablename) {
		Connection conn = null;
		PreparedStatement statement = null;
		boolean exist = false;
		try {
			conn = DBHelper.getConnection();
			ResultSet rs = conn.getMetaData().getTables(null, null, tablename, null );
			if (rs.next()) {
				exist = true;
			}else {
				exist = false;
			}
			rs.close();

		} catch (Exception e) {
			logger.error(e);
			exist = false;
		} finally {
			DBHelper.close(statement, conn);
		}
		return exist;
	}
	
	public static final boolean createTable(String sql) {
		Connection conn = null;
		PreparedStatement statement = null;
		boolean flag = true;
		try {
			conn = DBHelper.getConnection();
			statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (Exception e) {
			flag = false;
			logger.error(e);
		} finally {
			DBHelper.close(statement, conn);
		}
		return flag;
	}
	
	public static final void exectesql(List<String> list) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			for (String sql : list) {
				statement = conn.prepareStatement(sql);
				statement.execute();
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
		} finally {
			DBHelper.close(statement, conn);
			DBHelper.shutdownDataSource();
		}
	}
	
	/** 关闭数据源 */
    protected static final void shutdownDataSource() {
    	try {
    		datasource.close();
    	} catch (Exception e) {
			logger.error(e);
		}
    	
    }
	
	public static final void executeUpdate(List<String> list) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		//long id = 0;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			for (String sql : list) {
				statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				statement.executeUpdate();
			}
			conn.commit();
			/*rs=statement.getGeneratedKeys();//这一句代码就是得到插入的记录的id
			   while(rs.next()){
			    id= rs.getLong(1); 
			   }*/
		} catch (Exception e) {
			logger.error(e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
		} finally {
			DBHelper.close(statement, conn);
		}
	}
	 
	public static final long executeUpdate_(String sql) {
		Connection conn = null;
		PreparedStatement statement = null;
		long i = 0;
		try {
			conn = DBHelper.getConnection();
			statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			i = statement.executeUpdate();
			 
		} catch (Exception e) {
			logger.error(e);
		} finally {
			DBHelper.close(statement, conn);
		}
		return i;
	}
	 
	/**
	* 功能描述： 查询 本方法目前只支持 String Integer Timestamp
	*
	* @author 刘浩(liuh@cpsdna.com)
	* <p>创建日期 ：2014-11-21 下午2:22:49</p>
	 * @param <T>
	*
	* @return
	*
	* <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static <T> List<Object> executeQuery (Class<T> cls,String sql) {
		List<Object> list = new ArrayList<Object>();
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Field[] fieldlist_sup = cls.getSuperclass().getDeclaredFields();
			Field[] fieldlist = cls.getDeclaredFields();
			conn = DBHelper.getConnection();
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				rs.beforeFirst();
				while (rs.next()) {
					Object obj = cls.newInstance();
					for (int i = 0; i < fieldlist.length; i++) {
		                try {
		                	Field fld = fieldlist[i];
			                fld.setAccessible(true);
			                if (fld.getType().getName().equals("java.lang.String")) {
			    				fld.set(obj,rs.getString(fld.getName()) == null ? "" : rs.getString(fld.getName()));
			                } else if (fld.getType().getName().equals("java.sql.Timestamp")) {
			                	fld.set(obj,rs.getTimestamp(fld.getName()));
			                } else if (fld.getType().getName().equals("java.lang.Integer") || fld.getType().getName().equals("int")) {
			                	fld.set(obj,rs.getInt(fld.getName()));
			                } else if (fld.getType().getName().equals("java.lang.Double")) {
			                	fld.set(obj,rs.getDouble(fld.getName()));
			                } 
						} catch (Exception e) {
							continue;
						}
		            }
					
					for (int i = 0; i < fieldlist_sup.length; i++) {
		                try {
		                	Field fld = fieldlist_sup[i];
			                fld.setAccessible(true);
			                if (fld.getType().getName().equals("java.lang.String")) {
			    				fld.set(obj,rs.getString(fld.getName()) == null ? "" : rs.getString(fld.getName()));
			                } else if (fld.getType().getName().equals("java.sql.Timestamp")) {
			                	fld.set(obj,rs.getTimestamp(fld.getName()));
			                } else if (fld.getType().getName().equals("java.lang.Integer") || fld.getType().getName().equals("int")) {
			                	fld.set(obj,rs.getInt(fld.getName()));
			                } else if (fld.getType().getName().equals("java.lang.Double")) {
			                	fld.set(obj,rs.getDouble(fld.getName()));
			                } 
						} catch (Exception e) {
							continue;
						}
		            }
					list.add(obj);
				}
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			DBHelper.close(statement, conn);
		}
		
		
		return list;
	}
	
	public static int executeGetCount (String day_querysql) {
		int count = 0;
		//String day_querysql = "select sum(expenditure) count from yy_count_day where userid = 1 and tagid = 'yy_1'";
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DBHelper.getConnection();
			statement = conn.prepareStatement(day_querysql);
			ResultSet rs = statement.executeQuery();
			rs.last();
			if (rs.getRow() > 0) {
				rs.beforeFirst();
				while (rs.next()) {
					count = rs.getInt("count");
				}
			}
			rs.close();
		} catch (Exception e) {
			//logger.error(e);
		} finally {
			DBHelper.close(statement, conn);
		}
		return count;
	}
}

package com.dy.tools;

import java.sql.*;
import java.util.ArrayList;

public class SqlTool {

	// 定义变量
	private static Connection ct = null;
	// 大多数情况下用preparedstatement替代statement
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// 连接数据库的参数
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=mylab";
	private static String username = "sa";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String passwd = "sa";

	private static CallableStatement cs = null;

	public static CallableStatement getCs() {
		return cs;
	}

	// private static Properties pp = null;
	// private static InputStream fis = null;
	// 加载驱动，只需要一次，用静态代码块
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// try
		// {
		// //从dbinfo.properties
		// pp = new Properties();
		// fis=SqlHelper.class.getClassLoader().getResourceAsStream("mysql.properties");
		// //fis = new FileInputStream();
		// pp.load(fis);
		// url = pp.getProperty("url");
		// username = pp.getProperty("username");
		// driver = pp.getProperty("driver");
		// passwd = pp.getProperty("passwd");
		// Class.forName(driver);
		//	            
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// finally
		// {
		// try
		// { fis.close();}
		// catch(IOException e) {e.printStackTrace();}
		// fis = null;//垃圾回收站上收拾
		// }

	}

	// 得到连接
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}

	// *************callPro1存储过程函数1*************
	public static CallableStatement callPro1(String sql, String[] parameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, cs, ct);
		}
		return cs;
	}

	// *******************callpro2存储过程2************************
	public static CallableStatement callPro2(String sql, String[] inparameters,
			Integer[] outparameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}
			// cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
			if (outparameters != null) {
				for (int i = 0; i < outparameters.length; i++) {
					cs.registerOutParameter(inparameters.length + 1 + i,
							outparameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return cs;
	}

	public static ResultSet executeQuery(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return rs;
	}
	//查询的增强版，这里可以及时关闭resultset，返回arraylist
	public static ArrayList executeQuery1(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData resultSetMetaData=rs.getMetaData();
			//得到列数，下面的循环将每一行的数据作为对象放入list中
			int column=resultSetMetaData.getColumnCount();
			ArrayList  arrayList =new ArrayList();
			while(rs.next()){
				//rs是一行，objects存放每列的对象
				Object [] objects=new Object[column];
				//对象个数是列数
				for(int i=1;i<=column;i++){
					objects[i-1]=rs.getObject(i);
				}
				//得到全部列的对象，放入arraylist中
				arrayList.add(objects);

			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);

		}
		
	}

	
	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}
	//执行多条语句
	public static void executeUpdate2(String[] sql, String[][] parameters) {
		try {
			ct = getConnection();
			ct.setAutoCommit(false);

			for (int i = 0; i < sql.length; i++) {

				if (null != parameters[i]) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}

			}

			ct.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}

	}

	// 先写一个update、delete、insert
	// sql格式：update 表名 set 字段名 =？where 字段=？
	// parameter神应该是（”abc“,23）
	public static boolean executeUpdate(String sql, String[] parameters) {
		boolean b=true;
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}

			}
			ps.executeUpdate();
		} catch (Exception e) {
			b=false;
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 可以处理，也可以不处理
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
		return b;
	}

	public static void close(ResultSet rs, Statement ps, Connection ct) {
		// 关闭资源(先开后关)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
		if (null != ct) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ct = null;
		}
	}
	
	
}

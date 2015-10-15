package com.dy.tools;

import java.sql.*;
import java.util.ArrayList;

public class SqlTool {

	// �������
	private static Connection ct = null;
	// ������������preparedstatement���statement
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// �������ݿ�Ĳ���
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
	// ����������ֻ��Ҫһ�Σ��þ�̬�����
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// try
		// {
		// //��dbinfo.properties
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
		// fis = null;//��������վ����ʰ
		// }

	}

	// �õ�����
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}

	// *************callPro1�洢���̺���1*************
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

	// *******************callpro2�洢����2************************
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
	//��ѯ����ǿ�棬������Լ�ʱ�ر�resultset������arraylist
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
			//�õ������������ѭ����ÿһ�е�������Ϊ�������list��
			int column=resultSetMetaData.getColumnCount();
			ArrayList  arrayList =new ArrayList();
			while(rs.next()){
				//rs��һ�У�objects���ÿ�еĶ���
				Object [] objects=new Object[column];
				//�������������
				for(int i=1;i<=column;i++){
					objects[i-1]=rs.getObject(i);
				}
				//�õ�ȫ���еĶ��󣬷���arraylist��
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
	//ִ�ж������
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

	// ��дһ��update��delete��insert
	// sql��ʽ��update ���� set �ֶ��� =��where �ֶ�=��
	// parameter��Ӧ���ǣ���abc��,23��
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
			e.printStackTrace();// �����׶�
			// �׳��쳣
			// ���Դ���Ҳ���Բ�����
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
		return b;
	}

	public static void close(ResultSet rs, Statement ps, Connection ct) {
		// �ر���Դ(�ȿ����)
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

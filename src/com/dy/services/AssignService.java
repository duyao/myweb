package com.dy.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.domain.Assignment;
import com.domain.Student;
import com.domain.Task;
import com.dy.tools.SqlHelper;




public class AssignService {
	
	//添加任务分配,也能修改我的提交
	public Boolean addAssignByTaskid(String taskid,String [] stuid){
		Boolean b=true;
		for (int i = 0; i < stuid.length; i++) {
			String sql="insert into assignment  (taskid,stuid) values(?,?)";
			String []strings ={taskid,stuid[i]};
			Boolean bb=SqlHelper.executeUpdate(sql, strings);
			if(b&&bb){
				continue;
			}else{
				b=false;
				break;
			}
		}
		return b;
		
	}
	
	//根据任务号查看任务分配
	public ArrayList<Student> getAssignByTaskid(String taskid){
		String sql="select * from student where stuid in (select stuid from assignment where taskid=? ) order by stuid";
		ArrayList<Student> arrayList=new ArrayList<Student>();
		String [] strings={taskid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		try {
			while(rs.next()){
				//取出
				Student s=new Student();
				s.setStuid(rs.getInt(1));
				s.setName(rs.getString(3));
				s.setAge(rs.getInt(4));
				s.setHometown(rs.getString(5));
				s.setSex(rs.getString(6));
				s.setEmail(rs.getString(7));
				s.setTel(rs.getString(8));
				s.setAcademy(rs.getString(9));
				s.setMajor(rs.getString(10));
				s.setClassString(rs.getString(11));
				s.setClassid(rs.getString(12));
				s.setRole(rs.getString(13));
				arrayList.add(s);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return arrayList;
		
	}
	
	
	//找出该学生还未提交的任务
	public ArrayList<Integer> getTaskidByStuidUndo(String stuid){
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
		String sql="select taskid from assignment where filename is null and stuid=?";
		String [] strings={stuid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		try {
			while(rs.next()){
				//取出
				
				arrayList.add(rs.getInt(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return arrayList;
	}
	
	//找出该学生的所有任务
	public ArrayList<Assignment> getAllTaskByStuid(String stuid){
		ArrayList<Assignment> arrayList=new ArrayList<Assignment>();
		String sql="select * from assignment where stuid=?";
		String [] strings={stuid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		try {
			while(rs.next()){
				//取出
				Assignment assignment=new Assignment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				arrayList.add(assignment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return arrayList;
	}
	
	//添加提交记录
	public Boolean addAllAssgin(Assignment a){
		String sql="update assignment set subtime=?,path=?,filename=?,mydes=? where taskid=? and stuid=?";
		String [] strings={a.getSubtime(),a.getPath(),a.getFilename(),a.getMydes(),Integer.toString(a.getTaskid()),Integer.toString(a.getStuid())};
		Boolean b=SqlHelper.executeUpdate(sql, strings);
		return b;
		
		

	}
	
	//根据taskid得到assginment
	public Assignment getAssignmentByTaskid(String taskid,String stuid){
		String sql="select * from assignment where taskid=? and stuid=?";
		String []strings ={taskid,stuid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		Assignment assignment=null;
		try {
			while(rs.next()){
				//取出
				assignment=new Assignment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return assignment;
		
		
	}
	
	
	//查找文件存位置
	public String getPath(String taskid,String stuid){
		String sql="select path from assignment where taskid=? and stuid=?";
		String []strings ={taskid,stuid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		String path=null;
		try {
			while(rs.next()){
				//取出
				path=rs.getString(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return path;
		
	}
	
	//根据taskid得到所有任务分配
	public ArrayList<Assignment> getAmByTaskid(String taskid){
		String sql="select * from assignment where taskid =? order by stuid  ";
		String [] strings={taskid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		ArrayList<Assignment> arrayList=new ArrayList<Assignment>();
		try {
			while(rs.next()){
				//取出
				Assignment a=new Assignment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				arrayList.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return arrayList;
	}
	
	//删除提交
	public Boolean delAss(String taksid,String stuid){
		String tmp=null;
		String sql="update assignment set subtime=?,path=?,filename=?,mydes=? where taskid=? and stuid=?";
		String []strings={tmp,tmp,tmp,tmp,taksid,stuid};
		Boolean boolean1=SqlHelper.executeUpdate(sql, strings);
		return boolean1;
	}
	
	
	
	
	
}

package com.dy.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.domain.Project;

import com.dy.tools.SqlHelper;


public class ProjectService {
	//通过sqlhelper处理项目的函数
	
	//添加项目
	public Boolean addProject(Project project){
		//添加project
		String sql="insert into project  values(?,?,?,?,?,?,?,?,?)";
		String [] strings={Integer.toString(project.getProid()),Integer.toString(project.getLeadid()),Integer.toString(project.getTeamid()),
				project.getProname(),project.getDes(),"否",project.getProtype(),project.getStartdate().toString(),project.getEnddate().toString()};
		boolean b=SqlHelper.executeUpdate(sql,strings);
		
		return b;
		
	}


	//显示项目
	public ArrayList<Project> getProByPage(int pageNow,int pageSize){
		ArrayList<Project> arrayList = new ArrayList<Project>();
		String sql="select top "+pageSize+" * from project where proid not in(select top "
		+((pageNow-1)*pageSize)+" proid from project order by proid) order by proid";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		try {
			while(rs.next()){
				//取出
				

				Project p=new Project();
				p.setProid(rs.getInt(1));
				p.setLeadid(rs.getInt(2));
				p.setTeamid(rs.getInt(3));
				p.setProname(rs.getString(4));
				p.setDes(rs.getString(5));
				p.setFinish(rs.getString(6));
				p.setProtype(rs.getString(7));
				p.setStartdate(rs.getDate(8));
				p.setEnddate(rs.getDate(9));
				
				arrayList.add(p);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return arrayList; 
	}
	
	//得到记录个数
	public int getRowCount(){
		int RowCount=1;
		String sql="select count(*) from project";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			RowCount=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return RowCount;
		
	}
	
	//删除项目
	public boolean DelProject(String proid){
		String sql="delete from project where proid=?";
		String []parameters={proid};
		boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	
	//得到项目
	public Project getProByProid(String proid){
		Project p=new Project();
		String sql="select * from project where proid=?";
		String []parameters={proid};
		ResultSet rs=SqlHelper.executeQuery(sql, parameters);
		
		try {
			while(rs.next()){
				//取出
				p.setProid(rs.getInt(1));
				p.setLeadid(rs.getInt(2));
				p.setTeamid(rs.getInt(3));
				p.setProname(rs.getString(4));
				p.setDes(rs.getString(5));
				p.setFinish(rs.getString(6));
				p.setProtype(rs.getString(7));
				p.setStartdate(rs.getDate(8));
				p.setEnddate(rs.getDate(9));
	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return p;
	}
	//添加项目
	public boolean UpdateProject(Project p){
		String sql="update project  set leaderid=?,teamid=?,proname=?,des=?,protype=?, startdate=? ,enddate=? where proid=?";
		String[]parameters={Integer.toString(p.getLeadid()),Integer.toString(p.getTeamid()),p.getProname(),p.getDes(),
				p.getProtype(),p.getStartdate().toString(),p.getEnddate().toString(),Integer.toString(p.getProid())};
		boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	//获得新的添加编号
	public int getNewProid(){
		String sql="select MAX(proid) from project";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		int newProid=0;
		try {
			rs.next();
			newProid=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return (newProid+1);
		
	}
	
	
	
	//得到leader的项目
	public ArrayList<Project> getProByLeader(String leader){
		ArrayList<Project> arrayList = new ArrayList<Project>();
		String sql="select * from project where leaderid=?";
		String [] strings={leader};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		try {
			while(rs.next()){
				//取出
				

				Project p=new Project();
				p.setProid(rs.getInt(1));
				p.setLeadid(rs.getInt(2));
				p.setTeamid(rs.getInt(3));
				p.setProname(rs.getString(4));
				p.setDes(rs.getString(5));
				p.setFinish(rs.getString(6));
				p.setProtype(rs.getString(7));
				p.setStartdate(rs.getDate(8));
				p.setEnddate(rs.getDate(9));
				
				arrayList.add(p);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return arrayList; 
	}
	 
	
	//得到未创建任务的项目
	public ArrayList<Project> getProByLeaderWithUndo(String leader){
		
		
		//先找出已经分派任务的项目号
		String sql="select distinct proid from task where proid in (select proid from project where leaderid=?) order by proid";
		String [] strings={leader};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		int [] doProid=new int[100];
		int cnt=0;
		try {
			while(rs.next()){
				//取出已经分派任务的项目
				doProid[cnt++]=rs.getInt(1);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		//防止后面比较的时候数组发生错误
		doProid[cnt]=-1;
		
		//找出所有的属于该领导的项目
		ArrayList<Project> arrayList = new ArrayList<Project>();
		sql="select * from project where leaderid=? order by proid";
		rs=SqlHelper.executeQuery(sql, strings);
		cnt=0;
		try {
			while(rs.next()){
				//取出
				
				int proid=rs.getInt(1);
				if(doProid[cnt]==proid){
					cnt++;
					continue;
				}else{
					Project p=new Project();
					p.setProid(proid);
					p.setLeadid(rs.getInt(2));
					p.setTeamid(rs.getInt(3));
					p.setProname(rs.getString(4));
					p.setDes(rs.getString(5));
					p.setFinish(rs.getString(6));
					p.setProtype(rs.getString(7));
					p.setStartdate(rs.getDate(8));
					p.setEnddate(rs.getDate(9));
					arrayList.add(p);
				}
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		
		
		return arrayList; 
	}
	
	//
}

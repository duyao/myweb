package com.dy.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.domain.Task;
import com.dy.tools.SqlHelper;


public class TaskService {
	
	public Boolean addTask(String [] taskName,String proid){
		
		
		Boolean b=true;
		for (int i = 0; i < taskName.length; i++) {
			String sql="insert into task  values(?,?,?,?)";
			String taskid=Integer.toString(getNewTaskid());
			String seqString=Integer.toString((i+1));
			String [] strings={taskid,taskName[i],proid,seqString};
			Boolean bb=SqlHelper.executeUpdate(sql, strings);
			if(bb&b){
				continue;
			}else{
				b=false;
				break;
			}
		}
		return b;

	}
	
	public int getNewTaskid(){
		String sql="select MAX(taskid) from task";
		SqlHelper.executeQuery(sql, null);
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		int newTaskid=0;
		try {
			rs.next();
			newTaskid=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return (newTaskid+1);
	}
	
	
	public ArrayList<Task> getTaskByProid(String proid){
		
		String sql="select * from task where proid=? order by seq";
		String [] strings={proid};
		ResultSet resultSet=SqlHelper.executeQuery(sql, strings);
		ArrayList<Task> arrayList=new ArrayList<Task>();
		try {
			while (resultSet.next()) {
				
				Task task=new Task();
				task.setTaskid(resultSet.getInt(1));
				task.setTaskname(resultSet.getString(2));
				task.setProid(resultSet.getInt(3));
				task.setSeq((resultSet.getInt(4)));
				arrayList.add(task);
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(resultSet, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return arrayList;
	}
	
	
	//得到已经创建任务的的proid，但还未分配任务，即assignment表不在，但在task表中,即可以分配的任务
	public ArrayList<Integer> getProidToAssign(){
		String sql="select distinct proid from task where taskid not in(select distinct taskid from assignment) order by proid";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		ArrayList<Integer> proid=new ArrayList<Integer>();
		try {
			while (rs.next()) {
				proid.add(rs.getInt(1));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		return proid;
	}
	
	
	
	
	//根据taskid得到task,返回的是hashmap，一个proid带一些task
	public HashMap<Integer, ArrayList<Task> > getTaskByTaskid(ArrayList<Integer> taskids){
		
		HashMap<Integer, ArrayList<Task> > taskMap=new HashMap<Integer, ArrayList<Task> >();
	
		String tmp="";
		for (int i = 1; i < taskids.size(); i++) {
			tmp+="?,";
			
		}
		String sql="select * from task where taskid in ("+tmp+"?) order by proid,seq";
		
		String [] parameters=new String[taskids.size()];
		int cnt=0;
		for (Integer i : taskids) {
			parameters[cnt++]=Integer.toString(i);
		}
		
		ResultSet rs=SqlHelper.executeQuery(sql, parameters);
		ArrayList<Task> tasks=new ArrayList<Task>();
		int proid = -1;
		try {
			while (rs.next()) {
				Task task=new Task(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				if(proid!=rs.getInt(3)){
					if(proid!=-1){
						taskMap.put(proid, tasks);
						tasks=new ArrayList<Task>();
					}
					proid=rs.getInt(3);
					tasks.add(task);
				}else{
					tasks.add(task);
				}
				
				
			}
			//遍历完要添加！！！
			taskMap.put(proid, tasks);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		return taskMap;
	}
	
	//根据taskid得到proid
	public ArrayList<Integer> getProidByTaskid(ArrayList<Integer> taskids){
		String tmp="";
		for (int i = 1; i < taskids.size(); i++) {
			tmp+="?,";
			
		}
		String sql="select distinct proid from task where taskid in("+tmp+"?) order by proid";
		String [] parameters=new String[taskids.size()];
		int cnt=0;
		for (Integer i : taskids) {
			parameters[cnt++]=Integer.toString(i);
		}
		ResultSet rs=SqlHelper.executeQuery(sql, parameters);
		ArrayList<Integer> proids=new ArrayList<Integer>();
		try {
			while (rs.next()) {
				proids.add(rs.getInt(1));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		return proids;
	}
	//根据taskid得到task
	public Task getTaskByTaskid(String taskid){
		String sql="select * from task where taskid=?";
		String [] strings={taskid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		Task task=null;
		try {
			while (rs.next()) {
				task=new Task(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return task;
	}
	
	
}

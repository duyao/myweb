package com.dy.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.dy.tools.SqlHelper;

import com.domain.Member;
import com.domain.Student;
import com.domain.Team;

public class TeamService {
	//根据团队得到学生
	public HashMap<Integer, ArrayList<Student> > getAllTeam(){
		
		HashMap<Integer, ArrayList<Student> > hashMap=new HashMap<Integer, ArrayList<Student> >();
		
		//得到所有的团队号码和leader
		String sqlteamid="select teamid, proid from team order by teamid";
		ResultSet rs=SqlHelper.executeQuery(sqlteamid, null);
		ArrayList<Integer> teamid=new ArrayList<Integer>();
		ArrayList<Integer> proid=new ArrayList<Integer>();
		int cnt=0;
		
		try {
			
			while(rs.next()){
				//System.out.println("aa"+rs.getInt(1));
				teamid.add(rs.getInt(1));
				proid.add(rs.getInt(2));
				cnt++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		
		//根据得到的团队号码，先取出leader，取出团队成员,
		
		for (int i=0;i<teamid.size();i++) {
			
			int myteamid = teamid.get(i);
			int myproid = proid.get(i);
			ArrayList <Student> arrayList=new ArrayList<Student>();
			//arraylist第一个是leader
			String membersql="select distinct * from student where stuid =? or " +
					"stuid in (select stuid from member where teamid=?)"; 
			
			String [] strings={Integer.toString(myproid),Integer.toString(myteamid)};
			rs=SqlHelper.executeQuery(membersql, strings);
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
			
			hashMap.put(myteamid, arrayList);
			
		}
		return hashMap;
		
		
	}
	
	//得到共有多少团队
	public int getRowCount(){
		int RowCount=1;
		String sql="select count(*) from team";
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
	
	//根据页数得到team
	public HashMap<Integer, ArrayList<Student> > getTeamByPage(int pageNow,int pageSize){
		
		HashMap<Integer, ArrayList<Student> > hashMap=new HashMap<Integer, ArrayList<Student> >();
		
		String sql="select top "+pageSize+" teamid, proid from team where teamid not in" +
				"(select top "+((pageNow-1)*pageSize)+" teamid from team order by teamid) order by teamid";
		
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		
		ArrayList<Integer> teamid=new ArrayList<Integer>();
		ArrayList<Integer> proid=new ArrayList<Integer>();
		int cnt=0;
		
		try {
			
			while(rs.next()){
				//System.out.println("aa"+rs.getInt(1));
				teamid.add(rs.getInt(1));
				proid.add(rs.getInt(2));
				cnt++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		
		
		//根据得到的团队号码，先取出leader，取出团队成员,
		
		for (int i=0;i<teamid.size();i++) {
			
			int myteamid = teamid.get(i);
			int myproid = proid.get(i);
			ArrayList <Student> arrayList=new ArrayList<Student>();
			//arraylist第一个是leader
			String membersql="select distinct * from student where stuid =? or " +
					"stuid in (select stuid from member where teamid=?)"; 
			
			String [] strings={Integer.toString(myproid),Integer.toString(myteamid)};
			rs=SqlHelper.executeQuery(membersql, strings);
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
			
			hashMap.put(myteamid, arrayList);
			
		}
		return hashMap;
		
		
	}
	
	//得到新的团队号码
	public int getNewTeamid(){
		String sql="select MAX(teamid) from team";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		int newTeamid = 0;
		try {
			rs.next();
			newTeamid=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return (newTeamid+1);
		
	}
	
	//添加团队
	public Boolean addTeam(Team team){
		String sql="insert into team values(?,?,?,?)";
		String [] parameters={Integer.toString(team.getTeamid()),Integer.toString(team.getProid()),Integer.toString(team.getLeaderid()),Integer.toString(team.getTotal())};
		Boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	
	//添加member
	public Boolean addMember(ArrayList<Member> list){
		Boolean []booleans=new Boolean[list.size()];
		int cnt=0;
		for(Member m:list){
			String sql="insert into member  values(?,?,?)";
			String [] parameters={Integer.toString(m.getTeamid()),Integer.toString(m.getStuid()),Integer.toString(m.getProid())};
			booleans[cnt++]=SqlHelper.executeUpdate(sql, parameters);
		}
		
		Boolean flag=true;
		for (int i = 0; i < booleans.length; i++) {
			if(!booleans[i]){
				flag=false;
				break;
			}
			
		}
		
		return flag;
		
	}
	
	
	//根据团队号码得到学生
	public HashMap<Integer, ArrayList<Student> > getTeamByTeamid(int [] teamid){
		
		HashMap<Integer, ArrayList<Student> > hashMap=new HashMap<Integer, ArrayList<Student> >();
		
		for (int i=0;i<teamid.length;i++) {
			
			int myteamid = teamid[i];
			ArrayList <Student> arrayList=new ArrayList<Student>();
			//arraylist第一个是leader
			String membersql="select * from student where stuid in (select stuid from member where teamid=?)"; 
			String [] strings={Integer.toString(myteamid)};
			ResultSet rs=SqlHelper.executeQuery(membersql, strings);
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
			
			hashMap.put(myteamid, arrayList);
			
		}
		return hashMap;
		
		
	}
	
	public ArrayList<Student> getTeamByProid(String proid){
		String sql="select * from student where stuid in (select stuid from member where proid=?)";
		String [] strings={proid};
		ResultSet rs=SqlHelper.executeQuery(sql, strings);
		ArrayList<Student> arrayList=new ArrayList<Student>();
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
	
}

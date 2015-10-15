package com.dy.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import com.domain.Student;
import com.domain.Team;
import com.dy.tools.SqlHelper;




//����student��ĸ��ֲ���
public class StudentService {
	//��֤�û�������
	public boolean userCheck(Student student){
		boolean b=false;
		String sql="select * from student where stuid=? and pwd=?";
		String [] parameters={Integer.toString(student.getStuid()),student.getPwd()};
		
		ResultSet rs=SqlHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
//		ArrayList arrayList=SqlHelper.executeQuery1(sql, parameters);
//		if(arrayList.size()==1){
//			b=true;
//		}
		return b;
	}
	public int getRowCount(){
		int RowCount=1;
		String sql="select count(*) from student";
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
	//����ҳ�ŵõ�ѧ��
	public ArrayList getStuByPage(int pageNow,int pageSize){
		ArrayList<Student> arrayList = new ArrayList<Student>();
		String sql="select top "+pageSize+" * from student where stuid not in(select top "
		+((pageNow-1)*pageSize)+" stuid from student order by stuid) order by stuid";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		try {
			while(rs.next()){
				//ȡ��
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
	//ɾ��ѧ��
	public boolean DelStudent(String stuid){
		String sql="delete from student where stuid=?";
		String []parameters={stuid};
		
		boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	//�޸�ѧ��
	public boolean UpdateStudent(Student s){
		String sql="update student set pwd=?,name=?,age=?,hometown=?,sex=?,mail=?,tel=?,academy=?,major=?,class=?,classid=? where stuId=?";
		String[]parameters={s.getPwd(),s.getName(),Integer.toString(s.getAge()),s.getHometown(),s.getSex(),s.getEmail(),	s.getTel(),	s.getAcademy(),	
				s.getMajor(),	s.getClassString(),	s.getClassid(),Integer.toString(s.getStuid())};
		boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	
	//�õ�ȫ����Ϣ
	public Student getStuByStuid(String stuid){
		Student s=new Student();
		String sql="select * from student where stuid=?";
		String []parameters={stuid};
		ResultSet rs=SqlHelper.executeQuery(sql, parameters);
		try {
			while(rs.next()){
				//ȡ��
				s.setStuid(rs.getInt(1));
				s.setPwd(rs.getString(2));
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
	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return s;
	}
	
	//�õ��µ�stuid��������stuid+1
	public int getNewStuid(){
		String sql="select MAX(stuid) from student";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		int newstuid=1;
		try {
			rs.next();
			newstuid= rs.getInt(1)+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return newstuid;
	}
	//���ѧ��
	public boolean addStu(Student s){
		String sql="insert into student values( ?,?,?,?,?,?,?,?,?,?,?,? )";
		String[]parameters={s.getPwd(),s.getName(),Integer.toString(s.getAge()),s.getHometown(),s.getSex(),s.getEmail(),
							s.getTel(),	s.getAcademy(),	s.getMajor(),	s.getClassString(),	s.getClassid(),"3"};
		boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
		
	}
	
	//����ѧ�ŵõ���¼�ߵ������ͼ���
	public String getNameRoleByid(String stuid){
		String sql="select name ,role from student where stuid=? ";
		String [] parameters={stuid};
		ArrayList aList=SqlHelper.executeQuery1(sql, parameters);
		Object[] objects=(Object[]) aList.get(0);
		
		String nameString=(String) objects[0];
		String rloeString=(String) objects[1];
		return nameString+","+rloeString;
		
	}
	
	//�õ�����ѧ��
	public ArrayList getAllStu(){
		ArrayList<Student> arrayList = new ArrayList<Student>();
		String sql="select * from student order by stuid";
		ResultSet rs=SqlHelper.executeQuery(sql, null);
		try {
			while(rs.next()){
				//ȡ��
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
	
	//����ѧ�ŵõ�ѧ��
	public ArrayList<Student> getStuMemberByStuid(String [] stuidString){
		ArrayList<Student> arrayList = new ArrayList<Student>();
		String cntString="";
		for(int i=1;i<stuidString.length;i++){
			cntString+="?,";
		}
		String sql="select * from student where stuid in("+cntString+"?) order by stuid";
		System.out.println(sql);
		ResultSet rs=SqlHelper.executeQuery(sql, stuidString);
		try {
			while(rs.next()){
				//ȡ��
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
	
	
	//��role����
	public Boolean updateRole(String stuid){
		String sql="update student  set role=2 where stuid=?";
		String [] parameters={stuid};
		Boolean b=SqlHelper.executeUpdate(sql, parameters);
		return b;
	}
	
	
	
	
}

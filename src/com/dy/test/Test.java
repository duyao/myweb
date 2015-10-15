package com.dy.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.domain.Student;

import com.dy.services.StudentService;
import com.dy.services.TeamService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TeamService tService=new TeamService();
//		HashMap<Integer, ArrayList<Student> > hasmap=tService.getAllTeam();
//	    Iterator it = hasmap.entrySet().iterator();  
//	    while(it.hasNext()){  
//	        Entry  entry=(Entry)it.next();  
//	        Object key=entry.getKey();
//	        int teamid=Integer.parseInt(key.toString());
//	        System.out.println(teamid);
//	        Object value=entry.getValue();  
//	        ArrayList<Student> arrayList=(ArrayList<Student>)value;
//	        for (Student student : arrayList) {
//				System.out.println(student.getStuid());
//			}
//	    }  
	    
		
//		StudentService ss=new StudentService();
//		ArrayList<Student> arrayList=ss.getStuByPage(1, 13);
//		for(Student s:arrayList){
//			System.out.println(s.getStuid());
//			
//		}
		
		String teamid="0";
		System.out.println("aa"+teamid+"aa");
		if(teamid.equals("0")){
			System.out.println("bb");
			teamid="";
		}
		
		StudentService ssService=new StudentService();
		String [] stuidString={"1","12","13","14","2"};
		ArrayList<Student> arrayList=ssService.getStuMemberByStuid(stuidString);
		for(Student student:arrayList){
			System.out.println(student.getStuid());
		}
	}

}

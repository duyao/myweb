package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Project;
import com.domain.Student;
import com.dy.services.StudentService;

public class ShowStu extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * �õ��ܹ�ҳ������ǰҳ����ÿҳ��¼������
		 */
		
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		StudentService ss=new StudentService();
		
		String type=request.getParameter("type");
		if(type==null){
		
			//ÿҳ��ʾ���ټ�¼��Լ����
			int pageSize=20;
			
			//���ж��ټ�¼
			int rowCount=ss.getRowCount();
			//��������ҳ
			int pageCount=(rowCount-1)/pageSize+1;
			
			//��ǰҳ��
			String pageNowString=request.getParameter("pageNow");
			int pageNow=1;
			if(pageNowString!=null){
				pageNow=Integer.parseInt(pageNowString);
			}
			
			ArrayList<Student> arrayList=ss.getStuByPage(pageNow, pageSize);
			
			//int
			request.setAttribute("rowCount",rowCount);
			//request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNow",pageNow);
			
			
			//System.out.println(pageNow);
			
			//ArrayList<Student>
			request.setAttribute("arrayList",arrayList);
			
			request.getRequestDispatcher("/WEB-INF/showStu.jsp").forward(request, response);
		
		}else if("updateLeader".equals(type) || "addLeader".equals(type)){
			//ÿҳ��ʾ���ټ�¼��Լ����
			int pageSize=25;
			
			//���ж��ټ�¼
			int rowCount=ss.getRowCount();
			//��������ҳ
			int pageCount=(rowCount-1)/pageSize+1;
			
			//��ǰҳ��
			String pageNowString=request.getParameter("pageNow");
			int pageNow=1;
			if(pageNowString!=null){
				pageNow=Integer.parseInt(pageNowString);
			}
			
			ArrayList<Student> arrayList=ss.getStuByPage(pageNow, pageSize);
			
			//int
			request.setAttribute("rowCount",rowCount);
			//request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNow",pageNow);
			
			
			request.setAttribute("allStu", arrayList);
			//��Ӻ��޸�leader����ͬһҳ�棬��������תҳ�治ͬ����Щ���ܲ�ͬ
			if("updateLeader".equals(type)){
				request.setAttribute("type", "updateLeader");
				request.getRequestDispatcher("/WEB-INF/updateLeader.jsp").forward(request, response);
			}else if("addLeader".equals(type) ){
				
				//�ȴӻ����а�pȡ����ͨ��ǰһ���������
				HttpSession session=request.getSession();
				Project project=(Project)session.getAttribute("project");
				String proname=request.getParameter("proname");
				String des=request.getParameter("des");
				System.out.println("pr"+proname+"des"+des);
				
				if(des!=null){
					System.out.println("inshowdes"+des);
					project.setDes(des);
				}
				if(proname!=null){
					project.setProname(proname);
				}
				session.setAttribute("project", project);
				
				
				request.setAttribute("type", "addLeader");
				request.getRequestDispatcher("/WEB-INF/updateLeader.jsp").forward(request, response);
				
				
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

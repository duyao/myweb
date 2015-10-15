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
		 * 得到总共页数，当前页数，每页记录个数，
		 */
		
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		StudentService ss=new StudentService();
		
		String type=request.getParameter("type");
		if(type==null){
		
			//每页显示多少记录，约定好
			int pageSize=20;
			
			//共有多少记录
			int rowCount=ss.getRowCount();
			//共有所少页
			int pageCount=(rowCount-1)/pageSize+1;
			
			//当前页数
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
			//每页显示多少记录，约定好
			int pageSize=25;
			
			//共有多少记录
			int rowCount=ss.getRowCount();
			//共有所少页
			int pageCount=(rowCount-1)/pageSize+1;
			
			//当前页数
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
			//添加和修改leader都是同一页面，但最终跳转页面不同，有些功能不同
			if("updateLeader".equals(type)){
				request.setAttribute("type", "updateLeader");
				request.getRequestDispatcher("/WEB-INF/updateLeader.jsp").forward(request, response);
			}else if("addLeader".equals(type) ){
				
				//先从缓存中把p取出，通过前一面更新数据
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

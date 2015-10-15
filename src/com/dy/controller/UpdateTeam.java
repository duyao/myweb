package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Project;
import com.domain.Student;
import com.dy.services.StudentService;
import com.dy.services.TeamService;

public class UpdateTeam extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		System.out.println(type);
		TeamService teamService=new TeamService();
		
		
		if("updateTeamid".equals(type)){
			//每页显示多少记录，约定好
			int pageSize=5;
			
			//共有多少记录
			int rowCount=teamService.getRowCount();
			//共有所少页
			int pageCount=(rowCount-1)/pageSize+1;
			
			//当前页数
			String pageNowString=request.getParameter("pageNow");
			int pageNow=1;
			if(pageNowString!=null){
				pageNow=Integer.parseInt(pageNowString);
			}
			
			HashMap<Integer, ArrayList<Student> > hashMap=teamService.getTeamByPage(pageNow, pageSize);
			
			//int
			request.setAttribute("rowCount",rowCount);
			//request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNow",pageNow);
			request.setAttribute("team", hashMap);
			request.getRequestDispatcher("/WEB-INF/updateTeamid.jsp").forward(request, response);

			
		}else if("addTeamid".equals(type)){
			ArrayList<Student> arrayList=new StudentService().getAllStu();
			int newTeamid=teamService.getNewTeamid();
			
			//先从缓存中把p取出，通过前一面更新数据
			HttpSession session=request.getSession();
			Project project=(Project)session.getAttribute("project");
			
			String proname=request.getParameter("proname");
			String des=request.getParameter("des");
			System.out.println("pr"+proname+"des"+des);
			//更新数据
			project.setTeamid(newTeamid);
			if(des!=null){
				project.setDes(des);
			}
			if(proname!=null){
				project.setProname(proname);
			}
			
			session.setAttribute("project", project);
			
			request.setAttribute("allStu", arrayList);
			request.setAttribute("newTeamid", newTeamid);
			request.getRequestDispatcher("/WEB-INF/addTeamid.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

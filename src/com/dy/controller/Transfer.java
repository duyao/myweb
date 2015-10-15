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

public class Transfer extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type");
		
		//得到session的p，修改
		HttpSession session=request.getSession();
		Project p=(Project)session.getAttribute("project");
		//System.out.println(p.getDes());
		if("updateLeader".equals(type)){
			String leader=request.getParameter("radio");
			//惊现空格，不然后面没法转
			leader=leader.trim();
			//System.out.println("trans"+leader+"type"+type);
			p.setLeadid(Integer.parseInt(leader));
			session.setAttribute("project", p);
			request.getRequestDispatcher("/WEB-INF/updatePro.jsp").forward(request, response);
			
			
		}else if("updateTeamid".equals(type)){
			String teamid=request.getParameter("radio");
			//惊现空格，不然后面没法转			
			//System.out.println("trans"+leader+"type"+type);
			teamid=teamid.trim();
			p.setTeamid(Integer.parseInt(teamid));
			session.setAttribute("project", p);
			request.getRequestDispatcher("/WEB-INF/updatePro.jsp").forward(request, response);
			
		}else if("addLeader".equals(type)){
			String leader=request.getParameter("radio");
			leader=leader.trim();
			
			p.setLeadid(Integer.parseInt(leader));
			session.setAttribute("project", p);
			request.getRequestDispatcher("/WEB-INF/addPro.jsp").forward(request, response);
			
		}else if("addTeamid".equals(type)){
			String [] member=request.getParameterValues("checkbox");
			for(int i=0;i<member.length;i++){
				member[i]=member[i].trim();
			}
			ArrayList<Student> arrayList=new StudentService().getStuMemberByStuid(member);
			session.setAttribute("member", arrayList);
			request.getRequestDispatcher("/WEB-INF/addPro.jsp").forward(request, response);
			
			
		}
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

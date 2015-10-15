package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Project;
import com.dy.services.ProjectService;
import com.dy.services.TaskService;
import com.dy.services.TeamService;

public class UpdateTask extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type");
		
		TeamService teamService=new TeamService();
		ProjectService projectService=new ProjectService();
		TaskService taskService=new TaskService();
		if("seq".equals(type)){
			
			String proid=request.getParameter("proid");
			Project project=projectService.getProByProid(proid);
			request.setAttribute("project", project);
			//System.out.println(proid+"ff");
			request.getRequestDispatcher("/WEB-INF/addTask2.jsp").forward(request, response);
			
		}else if("getseq".equals(type)){
			String proid=request.getParameter("proid");
			String seq=request.getParameter("seq");
			Project project=projectService.getProByProid(proid);
			
			request.setAttribute("seq", seq);
			request.setAttribute("project", project);
			
			request.getRequestDispatcher("/WEB-INF/addTask3.jsp").forward(request, response);
			
			
		}else if("addtask".equals(type)){
			
			int seq=Integer.parseInt(request.getParameter("seq"));
			String [] taskName=new String[seq];
			for(int i=0;i<seq;i++){
				String taskseq="seq"+(i+1);
				taskName[i]=request.getParameter(taskseq);
				System.out.println("taskname"+taskName[i]);
			}
			
			String proid=(String)request.getParameter("proid");
			
			Boolean b=taskService.addTask(taskName, proid);
			
			request.setAttribute("type", "add");
			if(b){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
			
			
			
			
			
			
			
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

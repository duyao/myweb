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


import com.domain.Assignment;
import com.domain.Project;
import com.domain.Student;
import com.domain.Task;
import com.dy.services.AssignService;
import com.dy.services.ProjectService;
import com.dy.services.StudentService;
import com.dy.services.TaskService;
import com.dy.services.TeamService;

public class UpdateAssign extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type");
		
		ProjectService projectService=new ProjectService();
		TeamService teamService=new TeamService();
		AssignService assignService=new AssignService();
		TaskService taskService=new TaskService();
		StudentService studentService=new StudentService();
		
		if("getdotask".equals(type)){
			
			HttpSession session=request.getSession();
			String leadersString=(String)session.getAttribute("stuid");
			leadersString=leadersString.trim();
			int leader=Integer.parseInt(leadersString);
			
			//未分配的任务
			ArrayList<Integer> allproid=taskService.getProidToAssign();
			//找到这些项目的任务,一个项目对应多个任务
			HashMap<Integer, ArrayList<Task> > task=new HashMap<Integer, ArrayList<Task> >();
			//找到这些项目的具体信息
			ArrayList<Project> projects=new ArrayList<Project>();
			//找到团队成员，一个项目，对应很多人
			HashMap<Integer, ArrayList<Student> > students=new HashMap<Integer, ArrayList<Student>>();
			
			//找到属于该领导的项目
			for (Integer intproid : allproid) {
				String proid=Integer.toString(intproid);
				Project p=projectService.getProByProid(proid);
				if(p.getLeadid()==leader){
					projects.add(p);
					ArrayList<Student> s=teamService.getTeamByProid(proid);
					students.put(intproid, s);
					ArrayList<Task> t=taskService.getTaskByProid(proid);
					task.put(intproid, t);
				}
			}
			
			request.setAttribute("arrayList", projects);
			request.setAttribute("task", task);
			request.setAttribute("students", students);
			
			
			request.getRequestDispatcher("/WEB-INF/addAssign1.jsp").forward(request, response);
			
			
			
			
			
		}else if("getproid".equals(type)){
			
			String proid=request.getParameter("proid");
			Project project=projectService.getProByProid(proid);
			ArrayList<Student> students=teamService.getTeamByProid(proid);
			ArrayList<Task> tasks=taskService.getTaskByProid(proid);
			request.setAttribute("project", project);
			request.setAttribute("students", students);
			request.setAttribute("tasks", tasks);
			
			
			request.getRequestDispatcher("/WEB-INF/addAssign2.jsp").forward(request, response);
				
			
			
		}else if("addassign".equals(type)){
			String proid=request.getParameter("proid");
			ArrayList<Task> tasks=taskService.getTaskByProid(proid);
			Boolean b=true;
			for (Task task : tasks) {
				String taskid=Integer.toString(task.getTaskid());
				String s="checkbox"+taskid;
				String [] strings=request.getParameterValues(s);
				Boolean bb=assignService.addAssignByTaskid(taskid, strings);
				if(b&&bb){
					continue;
				}else{
					b=false;
					break;
				}
			}
			
			request.setAttribute("type", "add");
			if(b){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
		}else if("del".equals(type)){
			String taskid=request.getParameter("taskid");
			HttpSession session=request.getSession();
			String leadersString=(String)session.getAttribute("stuid");
			Boolean b=assignService.delAss(taskid, leadersString);
			request.setAttribute("type", "del");
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

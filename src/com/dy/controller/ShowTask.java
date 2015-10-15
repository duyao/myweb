package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Assignment;
import com.domain.Project;
import com.domain.Task;
import com.dy.services.AssignService;
import com.dy.services.ProjectService;
import com.dy.services.TaskService;
import com.dy.services.TeamService;

public class ShowTask extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		AssignService assignService=new AssignService();
		ProjectService projectService=new ProjectService();
		TaskService taskService=new TaskService();
		
		String type=request.getParameter("type");
		
		if("addassgin".equals(type)){
			
			HttpSession session=request.getSession();
			String stuid=(String)session.getAttribute("stuid");
			//得到项目号码
			ArrayList<Integer> taskid=assignService.getTaskidByStuidUndo(stuid);
			//得到每个项目的内容，一个proid对应多个task
			HashMap<Integer, ArrayList<Task> > taskMap=taskService.getTaskByTaskid(taskid);
//			Iterator it = taskMap.entrySet().iterator();  
//		    while(it.hasNext()){  
//		        Entry  entry=(Entry)it.next();  
//		        Object key=entry.getKey();
//		        int proid=Integer.parseInt(key.toString());
//		        Object value=entry.getValue();  
//		        ArrayList<Task> arrayList=(ArrayList<Task>)value;
//		        for (Task tt : arrayList) {
//					System.out.println(tt.getTaskid()+"proid"+proid+tt.getSeq()+":"+tt.getTaskname());
//				}
//		    }  
			//得到所在任务的项目
			ArrayList<Integer> proids=taskService.getProidByTaskid(taskid);
			ArrayList<Project> projects=new ArrayList<Project>();
			for (Integer integer : proids) {
				Project p=projectService.getProByProid(Integer.toString(integer));
				projects.add(p);
			}
			
			request.setAttribute("projects", projects);
			request.setAttribute("taskMap", taskMap);
			request.getRequestDispatcher("/WEB-INF/showAssgin.jsp").forward(request, response);
			
			
			
		}else if("submit".equals(type)){
			
			String taskid=request.getParameter("taskid");
			String proid=request.getParameter("proid");
			
			Project project=projectService.getProByProid(proid);
			Task task=taskService.getTaskByTaskid(taskid);
			
			request.setAttribute("project", project);
			request.setAttribute("task", task);
			
			
			
			request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
			
		}else if("showAssgin".equals(type)){
			
			HttpSession session=request.getSession();
			String stuid=(String)session.getAttribute("stuid");
			//得到所有该人员的任务
			ArrayList<Assignment> assignments=assignService.getAllTaskByStuid(stuid);
			//得到所有的taskid,并把任务化成hashmap，一taskid对应一个assginment
			HashMap<Integer, Assignment> assginMap=new HashMap<Integer, Assignment>();
			ArrayList<Integer> taskids=new ArrayList<Integer>();
			for (Assignment assignment : assignments) {
				taskids.add(assignment.getTaskid());
				assginMap.put(assignment.getTaskid(), assignment);
			}
			
			//得到每个项目的内容，一个proid对应多个task
			HashMap<Integer, ArrayList<Task> > taskMap=taskService.getTaskByTaskid(taskids);

			//得到所在任务的项目
			ArrayList<Integer> proids=taskService.getProidByTaskid(taskids);
			ArrayList<Project> projects=new ArrayList<Project>();
			for (Integer integer : proids) {
				Project p=projectService.getProByProid(Integer.toString(integer));
				projects.add(p);
			}
			
			request.setAttribute("projects", projects);
			request.setAttribute("taskMap", taskMap);
			request.setAttribute("assginMap", assginMap);
			
			
			
			request.getRequestDispatcher("/WEB-INF/showMyTask.jsp").forward(request, response);
			
			
		}else if("showFile".equals(type)){
			
			HttpSession session=request.getSession();
			String stuid=(String)session.getAttribute("stuid");
			
			String taskid=(String)request.getParameter("taskid");
			String proid=(String)request.getParameter("proid");
			Assignment assignment=assignService.getAssignmentByTaskid(taskid, stuid);
			Project project=projectService.getProByProid(proid);
			Task task=taskService.getTaskByTaskid(taskid);
			request.setAttribute("project", project);
			request.setAttribute("assignment", assignment);
			request.setAttribute("task", task);
			request.getRequestDispatcher("/WEB-INF/showMyTask2.jsp").forward(request, response);
			
		}

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

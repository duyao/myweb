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

public class ShowPro extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		/*
		 * �õ��ܹ�ҳ������ǰҳ����ÿҳ��¼������
		 */
		
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TeamService teamService=new TeamService();
		AssignService assignService=new AssignService();
		TaskService taskService=new TaskService();
		ProjectService projectService=new ProjectService();

		String type=request.getParameter("type");
		if("1".equals(type)){
			
			
			//ÿҳ��ʾ���ټ�¼��Լ����
			int pageSize=15;
			
			//���ж��ټ�¼
			int rowCount=projectService.getRowCount();
			//��������ҳ
			int pageCount=(rowCount-1)/pageSize+1;
			
			//��ǰҳ��
			String pageNowString=request.getParameter("pageNow");
			int pageNow=1;
			if(pageNowString!=null){
				pageNow=Integer.parseInt(pageNowString);
			}
			
			ArrayList<Project> arrayList=projectService.getProByPage(pageNow, pageSize);
			
			//int
			request.setAttribute("rowCount",rowCount);
			//request.setAttribute("pageCount",pageCount);
			request.setAttribute("pageNow",pageNow);
			
			
			//System.out.println(pageNow);
			
			//ArrayList<Project>
			request.setAttribute("arrayList",arrayList);
			
			request.getRequestDispatcher("/WEB-INF/showPro1.jsp").forward(request, response);
			
		}else if("2".equals(type)){
			
			
			//�鿴���е���Ŀ
			//�õ���¼��
			HttpSession session=request.getSession();
			String stuid=(String)session.getAttribute("stuid");
			//�õ����е���Ŀ
			ArrayList<Project> projects=projectService.getProByLeader(stuid);
			//�ҵ���Щ��Ŀ������,һ����Ŀ��Ӧ�������
			HashMap<Integer, ArrayList<Task> > tasks=new HashMap<Integer, ArrayList<Task> >();
			//�ҵ��Ŷӳ�Ա��һ����Ŀ����Ӧ�ܶ���
			HashMap<Integer, ArrayList<Student> > students=new HashMap<Integer, ArrayList<Student>>();
			//�ҵ���������˭��һ��taskid���stuid
			HashMap<Integer, ArrayList<Assignment> > assignments=new HashMap<Integer, ArrayList<Assignment> >();
			for (Project project : projects) {
				String proid=Integer.toString(project.getProid());
				 ArrayList<Student> s=new TeamService().getTeamByProid(proid);
				 students.put(project.getProid(), s);
				 ArrayList<Task> t=new TaskService().getTaskByProid(proid);
				 tasks.put(project.getProid(), t);
				 for (Task task : t) {
					ArrayList<Assignment> a=new AssignService().getAmByTaskid(Integer.toString(task.getTaskid()));
					assignments.put(task.getTaskid(), a);
				}
			}
			//Student leader=new StudentService().getStuByStuid(Integer.toString(project.getLeadid()));
			//request.setAttribute("leader",leader);
			request.setAttribute("projects", projects);
			request.setAttribute("tasks", tasks);
			request.setAttribute("students", students);
			request.setAttribute("assignments", assignments);

			request.getRequestDispatcher("/WEB-INF/showPro2.jsp").forward(request, response);
			
			
			
		}else if("addtask".equals(type)){
			
			
			
			//���ж��ټ�¼
			HttpSession session=request.getSession();
			String stuid=(String)session.getAttribute("stuid");

			ArrayList<Project> arrayList=projectService.getProByLeaderWithUndo(stuid);
			//ArrayList<Project>
			request.setAttribute("arrayList",arrayList);
			
			int [] teamid=new int[arrayList.size()];
			int cnt=0;
			for (Project project : arrayList) {
				teamid[cnt++]=project.getTeamid();
			}
			HashMap<Integer, ArrayList<Student> > hashMap=new TeamService().getTeamByTeamid(teamid);
			request.setAttribute("hashMap", hashMap);
			
			request.getRequestDispatcher("/WEB-INF/addTask1.jsp").forward(request, response);
			
		}else if("showALl".equals(type)){
			String proid=request.getParameter("proid");
			Project project=projectService.getProByProid(proid);
			Student leader=new StudentService().getStuByStuid(Integer.toString(project.getLeadid()));
			ArrayList<Student>team =teamService.getTeamByProid(proid);
			ArrayList<Task> tasks=taskService.getTaskByProid(proid);
			//
			HashMap<Integer, ArrayList<Assignment> > hashMap=new HashMap<Integer, ArrayList<Assignment> >();
			for (Task t: tasks ) {
				ArrayList<Assignment> am=assignService.getAmByTaskid(Integer.toString(t.getTaskid()));
				hashMap.put(t.getTaskid(), am);
			}
			
			request.setAttribute("project", project);
			request.setAttribute("leader",leader);
			request.setAttribute("team",team);
			request.setAttribute("tasks",tasks);
			request.setAttribute("hashMap",hashMap);
			
			request.getRequestDispatcher("/WEB-INF/showPro1_2.jsp").forward(request, response);
		}
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

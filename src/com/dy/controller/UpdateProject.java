package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Member;
import com.domain.Project;
import com.domain.Student;
import com.domain.Team;
import com.dy.services.ProjectService;
import com.dy.services.StudentService;
import com.dy.services.TeamService;

public class UpdateProject extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
			
		
		String type=request.getParameter("type");
		ProjectService projectService=new ProjectService();
		StudentService studentService=new StudentService();
		TeamService teamService=new TeamService();
		
		if("del".equals(type)){
			String proid=(String) request.getParameter("proid");
			request.setAttribute("type", "del");
			if(projectService.DelProject(proid)){
				request.getRequestDispatcher("/WEB-INF/ok.jsp?").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
		}else if ("updateView".equals(type)){
			String proid=(String) request.getParameter("proid");
			//修改时把p存在session中，便于后面修改
			Project p=projectService.getProByProid(proid);
			HttpSession session=request.getSession();
			session.setAttribute("project", p);
			request.getRequestDispatcher("/WEB-INF/updatePro.jsp").forward(request, response);
			
		}
		else if("update".equals(type)){
			
			Project project=new Project();
			project.setProid(Integer.parseInt(request.getParameter("proid")));
			project.setProname(request.getParameter("proname"));
			project.setTeamid(Integer.parseInt(request.getParameter("teamid")));
			project.setLeadid(Integer.parseInt(request.getParameter("leadid")));
			project.setDes(request.getParameter("des"));
			
			request.setAttribute("type", "update");
			if(projectService.UpdateProject(project)){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);

			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
			
		}else if("addView".endsWith(type)){
			
			String newproid=Integer.toString(projectService.getNewProid()) ;
			request.setAttribute("newproid", newproid);
			request.getRequestDispatcher("/WEB-INF/addPro1.jsp").forward(request, response);
			
		}else if("addnamedes".equals(type)){
			String newproid=request.getParameter("proid");
			String proname=request.getParameter("proname");
			String des=request.getParameter("des");
			
			request.setAttribute("proid",newproid );
			request.setAttribute("proname",proname);
			request.setAttribute("des",des );
			
			ArrayList<Student> arrayList=studentService.getAllStu();
			request.setAttribute("arrayList", arrayList);
			
			request.getRequestDispatcher("/WEB-INF/addPro2.jsp").forward(request, response);
			
			
		}else if("addleader".equals(type)){
			
			String newproid=request.getParameter("proid");
			String proname=request.getParameter("proname");
			String des=request.getParameter("des");
			String leader=request.getParameter("radio");
			leader=leader.trim();
			Student student=studentService.getStuByStuid(leader);
			
			
			request.setAttribute("proid",newproid );
			request.setAttribute("proname",proname);
			request.setAttribute("des",des );
			request.setAttribute("leader", student);
			
			ArrayList<Student> arrayList=studentService.getAllStu();
			request.setAttribute("arrayList", arrayList);
			
			String teamid=Integer.toString(teamService.getNewTeamid());
			request.setAttribute("teamid", teamid);
			
			request.getRequestDispatcher("/WEB-INF/addPro3.jsp").forward(request, response);
			
		}else if("add".endsWith(type)){
			
			
			int newproid=Integer.parseInt(request.getParameter("proid"));
			String proname=request.getParameter("proname");
			String des=request.getParameter("des");
			int leaderid=Integer.parseInt(request.getParameter("leader"));
			int teamid=Integer.parseInt(request.getParameter("teamid"));
			
			String [] member=request.getParameterValues("checkbox");
			for(int i=0;i<member.length;i++){
				member[i]=member[i].trim();
			}
			
			Project project=new Project(newproid,leaderid,teamid,proname,des);
			
			//创建team
			Team team=new Team(teamid,newproid,leaderid,member.length);
			//创建member
			ArrayList<Member> members=new ArrayList<Member>();
			for (int j = 0; j < member.length; j++) {
				int stuid = Integer.parseInt(member[j]);
				Member m=new Member(project.getTeamid(), stuid, project.getProid());
				members.add(m);
			}
			
			
			//添加项目
			Boolean b1=projectService.addProject(project);
			
			//添加team
			Boolean b2=teamService.addTeam(team);
			//添加member
			Boolean b3=teamService.addMember(members);
			//将leader的role提升为2
			Boolean b4=new StudentService().updateRole(Integer.toString(project.getLeadid()));
			
			request.setAttribute("type", "add");
			if( b1 && b2 && b3 && b4){
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

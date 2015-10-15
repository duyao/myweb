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
			//ÿҳ��ʾ���ټ�¼��Լ����
			int pageSize=5;
			
			//���ж��ټ�¼
			int rowCount=teamService.getRowCount();
			//��������ҳ
			int pageCount=(rowCount-1)/pageSize+1;
			
			//��ǰҳ��
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
			
			//�ȴӻ����а�pȡ����ͨ��ǰһ���������
			HttpSession session=request.getSession();
			Project project=(Project)session.getAttribute("project");
			
			String proname=request.getParameter("proname");
			String des=request.getParameter("des");
			System.out.println("pr"+proname+"des"+des);
			//��������
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

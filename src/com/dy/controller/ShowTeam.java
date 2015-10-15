package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Student;
import com.dy.services.TeamService;



public class ShowTeam extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TeamService teamService=new TeamService();
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
		request.setAttribute("team",hashMap);
		
		
		String type=request.getParameter("type");
		
		if("updateTeamid".equals(type)){
			
			request.getRequestDispatcher("/WEB-INF/updateTeamid.jsp").forward(request, response);
			
		}

		
		
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

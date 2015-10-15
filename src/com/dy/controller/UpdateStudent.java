package com.dy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Student;
import com.dy.services.StudentService;
import com.dy.tools.SqlHelper;

public class UpdateStudent extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		
		String stuid=(String) request.getParameter("stuid");
		
		String type=request.getParameter("type");
		
		StudentService ss=new StudentService();
		
		if("del".equals(type)){
			request.setAttribute("type", "del");
			if(ss.DelStudent(stuid)){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
		}else if ("updateView".equals(type)){
			Student s=ss.getStuByStuid(stuid);
			request.setAttribute("student", s);
			request.getRequestDispatcher("/WEB-INF/updateStu.jsp").forward(request, response);
			
		}else if("update".equals(type)){
			
			Student student=new Student();
			student.setStuid(Integer.parseInt(request.getParameter("stuid")));
			student.setPwd(request.getParameter("pwd"));
			student.setName(request.getParameter("name"));
			student.setAge(Integer.parseInt(request.getParameter("age")));
			student.setHometown(request.getParameter("hometown"));
			student.setSex(request.getParameter("sex"));
			student.setEmail(request.getParameter("email"));
			student.setTel(request.getParameter("tel"));
			student.setAcademy(request.getParameter("academy"));
			student.setMajor(request.getParameter("major"));
			student.setClassString(request.getParameter("classString"));
			student.setClassid(request.getParameter("classid"));
			
			request.setAttribute("type", "update");
			if(ss.UpdateStudent(student)){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);

			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
			
		}else if("addView".endsWith(type)){
			String newstuid=Integer.toString(ss.getNewStuid()) ;
			request.setAttribute("newstuid", newstuid);
			request.getRequestDispatcher("/WEB-INF/addStu.jsp").forward(request, response);
		}else if("add".endsWith(type)){
			
			Student student=new Student();
			student.setStuid(Integer.parseInt(request.getParameter("stuid")));
			student.setPwd(request.getParameter("pwd"));
			student.setName(request.getParameter("name"));
			student.setAge(Integer.parseInt(request.getParameter("age")));
			student.setHometown(request.getParameter("hometown"));
			student.setSex(request.getParameter("sex"));
			student.setEmail(request.getParameter("email"));
			student.setTel(request.getParameter("tel"));
			student.setAcademy(request.getParameter("academy"));
			student.setMajor(request.getParameter("major"));
			student.setClassString(request.getParameter("classString"));
			student.setClassid(request.getParameter("classid"));
			student.setRole("3");
			request.setAttribute("type", "add");
			
			if(ss.addStu(student)){
				request.getRequestDispatcher("/WEB-INF/ok.jsp").forward(request, response);

			}else{
				request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
			}
			
		}else if("updateMy".equals(type)){
			stuid=(String)session.getAttribute("stuid");
			String role=(String)session.getAttribute("role");
			Student s=ss.getStuByStuid(stuid);
			request.setAttribute("student", s);
			request.getRequestDispatcher("/WEB-INF/updateMy"+role+".jsp").forward(request, response);
		}
		
		


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

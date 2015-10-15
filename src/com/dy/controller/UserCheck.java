package com.dy.controller;
import com.domain.Student;
import com.dy.services.StudentService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String nameString=request.getParameter("stuid");
		String pwd=request.getParameter("pwd");
		
		
		Student student=new Student();
		student.setStuid(Integer.parseInt(nameString));
		student.setPwd(pwd);
		StudentService ss=new StudentService();
		//得到cookie
		Cookie [] cookies=request.getCookies();
		//获取本次登录时间
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String timeString=simpleDateFormat.format(new Date());
		//设置上次登录时间
		String lasttime=null;
		if(ss.userCheck(student)){
			//没有缓存
			//out.print("aa"+cookies.length);
			if(cookies!=null){

				for(Cookie cookie: cookies){
					//如果存在上次登录时间则记录下来
					if("lasttime".equals(cookie.getName())){
						lasttime=cookie.getValue();
						cookie.setValue(timeString);
					}
				}
			}
			//如果没有找到上次登录时间，建立cookie
			if(lasttime==null){
				Cookie timeCookie=new Cookie("lasttime", timeString);
				response.addCookie(timeCookie);
				
			}
			//登陆成功，建立session
			HttpSession session=request.getSession();
			String tmp=ss.getNameRoleByid(nameString);
			String [] strings=tmp.split(",");
			String name=strings[0];
			String role=strings[1];
			//去掉空格
			role=role.trim();
			
			session.setAttribute("nameString", name);
			session.setAttribute("lasttime", timeString);
			session.setAttribute("stuid", nameString);
			session.setAttribute("role", role);
			//System.out.println("/WEB-INF/main"+role+".jsp");
			
			request.getRequestDispatcher("/WEB-INF/main"+role+".jsp").forward(request, response);
		
		}else{
			request.setAttribute("err", "用户名或者密码错误");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

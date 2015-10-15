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
		//�õ�cookie
		Cookie [] cookies=request.getCookies();
		//��ȡ���ε�¼ʱ��
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String timeString=simpleDateFormat.format(new Date());
		//�����ϴε�¼ʱ��
		String lasttime=null;
		if(ss.userCheck(student)){
			//û�л���
			//out.print("aa"+cookies.length);
			if(cookies!=null){

				for(Cookie cookie: cookies){
					//��������ϴε�¼ʱ�����¼����
					if("lasttime".equals(cookie.getName())){
						lasttime=cookie.getValue();
						cookie.setValue(timeString);
					}
				}
			}
			//���û���ҵ��ϴε�¼ʱ�䣬����cookie
			if(lasttime==null){
				Cookie timeCookie=new Cookie("lasttime", timeString);
				response.addCookie(timeCookie);
				
			}
			//��½�ɹ�������session
			HttpSession session=request.getSession();
			String tmp=ss.getNameRoleByid(nameString);
			String [] strings=tmp.split(",");
			String name=strings[0];
			String role=strings[1];
			//ȥ���ո�
			role=role.trim();
			
			session.setAttribute("nameString", name);
			session.setAttribute("lasttime", timeString);
			session.setAttribute("stuid", nameString);
			session.setAttribute("role", role);
			//System.out.println("/WEB-INF/main"+role+".jsp");
			
			request.getRequestDispatcher("/WEB-INF/main"+role+".jsp").forward(request, response);
		
		}else{
			request.setAttribute("err", "�û��������������");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}

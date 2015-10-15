<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>



	</head>

	<body>
		<%
  	response.setContentType("text/html;charset=utf-8");
	String info=(String) request.getAttribute("type");
	if("del".equals(info)){
		info="删除";
	}else if("update".equals(info)){
		info="修改";
	}else if("add".equals(info)){
		info="添加";
	}
	String role=(String)session.getAttribute("role");
	
	//将project的session取出，这样下次修改不影响
	session=request.getSession();
	session.removeAttribute("project");
	session.removeAttribute("member");
	
  %>
		<h2>
			恭喜！<%=info %>成功！
		</h2>
		<br />

		<a href="http://localhost:8080/myweb/Forward">返回主界面</a>
	</body>
</html>

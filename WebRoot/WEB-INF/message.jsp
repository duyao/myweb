<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>



	</head>

	<body>
		<%
  String string=(String)request.getAttribute("message");
  String role=(String)session.getAttribute("role");
  %>
		<h3><%=string %></h3>
		<h2>
			<a href="http://localhost:8080/myweb/Forward">返回主界面</a>
		</h2>

	</body>
</html>

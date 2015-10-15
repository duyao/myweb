<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>实验室科研项目任务管理系统_用户登录</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow: hidden;
}

.STYLE1 {
	color: #000000;
	font-size: 15px;
}
-->
</style>
	</head>

	<body>
		<%
	response.setContentType("text/html;charset=utf-8");
%>
		<form action="/myweb/UserCheck" method="post">
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table width="962" border="0" align="center" cellpadding="0"
							cellspacing="0">

							<%
    	String err=(String)request.getAttribute("err");
    	if(err==null){
    		err="";
    	}
    	%>
							<tr>
								<td height="25" align="center">
									<font color="ffffff"><%=err %></font><span class="STYLE1"></span>
								</td>
							</tr>

							<tr>
								<td height="235" background="images/login_03.jpg">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="53">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<tr>

											<td width="394" height="53" background="images/login_05.jpg">
												&nbsp;
											</td>
											<td width="206" background="images/login_06.jpg">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>

														<td width="16%" height="25">
															<div align="right">
																<span class="STYLE1">用户</span>
															</div>
														</td>
														<td width="57%" height="25">
															<div align="center">

																<input type="text" name="stuid"
																	style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff"
																	id="stuid">
															</div>
														</td>
														<td width="27%" height="25">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="25">
															<div align="right">
																<span class="STYLE1">密码</span>
															</div>
														</td>
														<td height="25">
															<div align="center">
																<input type="password" name="pwd"
																	style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff"
																	id="pwd">
															</div>
														</td>
														<td height="25">
															<div align="left">
																<input type="submit" width="49" height="18" border="0"
																	value="登录">
																</
																<input>
															</div>
														</td>
													</tr>

												</table>
											</td>

											<td width="362" background="images/login_07.jpg">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="213" background="images/login_08.jpg">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

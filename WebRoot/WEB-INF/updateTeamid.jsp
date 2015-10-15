<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import=" java.util.HashMap"%>
<%@page import=" java.util.Iterator"%>
<%@page import=" java.util.Map.Entry"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
.STYLE11 {
	font-size: 12px;
	color: #FFFFFF;
}
</style>
		<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>

	</head>

	<body>
		<%
  
  
    HashMap<Integer, ArrayList<Student> > hashMap=(HashMap<Integer, ArrayList<Student> >)request.getAttribute("team");
	//每页显示多少记录
	int pageSize = 5;
	//共有多少记录
	int rowCount = Integer.parseInt(request.getAttribute("rowCount").toString());
	//int rowCount=Integer.parseInt((String)request.getAttribute("rowCount"));
	//共有所少页
	int pageCount = (rowCount - 1) / pageSize + 1;
	//当前页数
	int pageNow = Integer.parseInt(request.getAttribute("pageNow").toString());
  
 
	%>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="8" background="images/tab_12.jpg">
					&nbsp;
				</td>
				<td>
					当前位置：修改项目团队&nbsp;&nbsp;&nbsp;&nbsp;（粉色为项目负责人）
					<hr />
					<form
						action="http://localhost:8080/myweb/Transfer?type=updateTeamid"
						method="post">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="b5d6e6" onmouseover="changeto()"
							onmouseout="changeback()">




							<tr>

								<td width="3%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">请选择</span>
									</div>
								</td>





								<td width="5%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">项目团队编号</span>
									</div>
								</td>

								<td width="5%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">学号</span>
									</div>
								</td>

								<td width="5%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">姓名</span>
									</div>
								</td>

								<td width="5%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">性别</span>
									</div>
								</td>

								<td width="10%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">学院</span>
									</div>
								</td>
								<td width="10%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">专业</span>
									</div>
								</td>
								<td width="5%" height="22" background="images/bg.jpg"
									bgcolor="#FFFFFF">
									<div align="center">
										<span class="STYLE1">年级</span>
									</div>
								</td>



							</tr>
							<%

					 Iterator it = hashMap.entrySet().iterator();  
				    while(it.hasNext()){  
				        Entry  entry=(Entry)it.next();  
				        Object key=entry.getKey();
				        int teamid=Integer.parseInt(key.toString());
				        Object value=entry.getValue();  
				        ArrayList<Student> arrayList=(ArrayList<Student>) value;
				%>
							<tr>


								<td height="20" bgcolor="#FFFFFF"
									rowspan="<%=arrayList.size() %>">
									<div align="center">
										<input type="radio" name="radio" value="<%=teamid%> " />

									</div>
								</td>


								<td height="20" bgcolor="#FFFFFF"
									rowspan="<%=arrayList.size() %>">
									<div align="center">
										<span class="STYLE1"><%=teamid %></span>
									</div>
								</td>

								<%
      
      for (int i=0;i<arrayList.size();i++) {
    	  Student s=arrayList.get(i);
    	  String bgcolor="#FFFFFF";
    	  if(i==0){
    		  bgcolor="#FF3FFF";
    	  }
    	  
      %>

								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getStuid()%></span>
									</div>
								</td>
								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getName()%></span>
									</div>
								</td>


								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getSex()%></span>
									</div>
								</td>

								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getAcademy()%></span>
									</div>
								</td>
								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getMajor()%></span>
									</div>
								</td>
								<td height="20" bgcolor="<%=bgcolor%>">
									<div align="center">
										<span class="STYLE1"><%=s.getClassString()%></span>
									</div>
								</td>



							</tr>
							<%
						}
				        }
					%>

							<tr>
								<td>
									<div align="right">
										<input type="submit" value="提交" />
									</div>
								</td>
							</tr>

						</table>
					</form>

				</td>
				<td width="8" background="images/tab_15.jpg">
					&nbsp;
				</td>
			</tr>


		</table>


		<tr>
			<td height="35" background="images/tab_19.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12" height="35">
							<img src="images/tab_18.jpg" width="12" height="35" />
						</td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="STYLE4">
										&nbsp;&nbsp;共有
										<%=rowCount%>
										条记录，当前第
										<%=pageNow%>/<%=pageCount%>
										页
									</td>
									<td>
										<table border="0" align="right" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="40">
													<a
														href="http://localhost:8080/myweb/ShowTeam?type=updateLeader&pageNow=1"><img
															src="images/first.jpg" width="37" height="15" /> </a>
												</td>
												<%
												//对上一页和下一页做判断
												int back = 1;
												int next = pageCount;
												if (pageNow != 1) {
													back = pageNow - 1;
												}
												if (pageNow != pageCount) {
													next = pageNow + 1;
												}
											%>
												<td width="45">
													<a
														href="http://localhost:8080/myweb/ShowTeam?type=updateTeamid&pageNow=<%=back%>"><img
															src="images/back.jpg" width="43" height="15" /> </a>
												</td>
												<td width="45">
													<a
														href="http://localhost:8080/myweb/ShowTeam?type=updateTeamid&pageNow=<%=next%>"><img
															src="images/next.jpg" width="43" height="15" /> </a>
												</td>
												<td width="40">
													<a
														href="http://localhost:8080/myweb/ShowTeam?type=updateTeamid&pageNow=<%=pageCount%>"><img
															src="images/last.jpg" width="37" height="15" /> </a>
												</td>
												<td width="100">
													<div align="center">
														<span class="STYLE1">转到第 <input name="pageNow"
																type="text" size="4"
																style="height: 12px; width: 20px; border: 1px solid #999999;" />
															页 </span>
													</div>
												</td>
												<td width="40">
													<img src="images/go.jpg" width="37" height="15" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="16">
							<img src="images/tab_20.jpg" width="16" height="35" />
						</td>
					</tr>
				</table>
			</td>
		</tr>


	</body>
</html>

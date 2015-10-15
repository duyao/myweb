<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="sun.text.normalizer.IntTrie"%>
<%@page import="com.domain.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charget=utf-8" />
		<title>SYSTEM NAMAGER</title>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE11 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE12 {
	font-size: 9px
}

.STYLE13 {
	color: #033d61;
	font-size: 12px;
}

.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
</style>
		<script>
var he=document.body.clientHeight-105
document.write("<div id=tt style=height:"+he+";overflow:hidden>")
</script>

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


		<script type="text/javascript">
function display(clock)
{
var now=new Date();   //创建Date对象
var year=now.getFullYear(); //获取年份
var month=now.getMonth(); //获取月份
var date=now.getDate();  //获取日期
var day=now.getDay();  //获取星期
var hour=now.getHours(); //获取小时
var minu=now.getMinutes(); //获取分钟
var sec=now.getSeconds(); //获取秒钟
month=month+1;
var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
var week=arr_week[day];  //获取中文的星期
var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间
clock.innerHTML="当前时间："+time; //显示系统时间
}
window.onload=function()
{
 window.getInterval("display(clock)", 1000);
}
</script>


		<style>
.navPoint {
	COLOR: white;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 9pt
}
</style>


	</head>

	<body>

		<%
			session = request.getSession();
			response.setContentType("text/html;charget=utf-8");
			//HttpSession session2=request.getSession();
			String nameString = (String) session.getAttribute("nameString");
			//out.print("<h3>欢迎"+nameString+"登录  </h3>");

			String lasttime = (String) session.getAttribute("lasttime");
			if (lasttime.equals("null")) {
				//out.print("<h3>您本次是第一次登录</h3>");
				lasttime = "您本次是第一次登录";

			} else {
				//out.print("<h3>您上次的登录时间为："+lasttime+"</h3>");
				lasttime = "您上次的登录时间为：" + lasttime;

			}
		%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="70" background="images/main_05.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="24">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" height="24" background="images/main_03.jpg">
											&nbsp;
										</td>
										<td width="505" background="images/main_04.jpg">
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td width="21">
											<img src="images/main_07.jpg" width="21" height="24">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="38">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" height="38" background="images/main_09.jpg">
											&nbsp;
										</td>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="77%" height="25" valign="bottom">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="50" height="19">
																	<div align="center">
																		<a href="http://localhost:8080/myweb/Forward"><img
																				src="images/main_12.jpg" width="49" height="19">
																		</a>
																	</div>
																</td>

																<td width="50">
																	<div align="center">
																		<a href="/myweb/index.jsp"><img
																				src="images/main_20.jpg" width="48" height="19">
																		</a>
																	</div>
																</td>
																<td width="26">
																	<div align="center">
																		<img src="images/main_21.jpg" width="26" height="19">
																	</div>
																</td>
																<td width="100">
																	<div align="center">
																		<a
																			href="http://localhost:8080/myweb/UpdateStudent?type=updateMy"><img
																				src="images/main_22.jpg" width="98" height="19">
																		</a>
																	</div>
																</td>
																<td>
																	&nbsp;
																</td>
															</tr>
														</table>
													</td>
													<td width="220" valign="bottom" nowrap="nowrap">
														<div align="right">
															<span class="STYLE11"><span class="STYLE12">■</span>
																<%=lasttime%></span>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="21">
											<img src="images/main_11.jpg" width="21" height="38">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="8" style="line-height: 8px;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" background="images/main_29.jpg"
											style="line-height: 8px;">
											&nbsp;
										</td>
										<td width="505" background="images/main_30.jpg"
											style="line-height: 8px;">
											&nbsp;
										</td>
										<td style="line-height: 8px;">
											&nbsp;
										</td>
										<td width="21" style="line-height: 8px;">
											<img src="images/main_31.jpg" width="21" height="8">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="28" background="images/main_36.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="177" height="28" background="images/main_32.jpg">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="20%" height="22">
											&nbsp;
										</td>
										<td width="59%" valign="bottom">
											<div align="center" class="STYLE11">
												当前用户：<%=nameString%></div>
										</td>
										<td width="21%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="65" height="28">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="23">
														<table width="258" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td height="20">
																	<span>欢迎登录实验室科研项目管理系统</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>


										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="21">
								<img src="images/main_37.jpg" width="21" height="28">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>















		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" style="table-layout: fixed;">
			<tr>


				<td width="171" id=frmTitle noWrap name="fmTitle" align="center"
					valign="top">
					<table width="171" height="100%" border="0" cellpadding="0"
						cellspacing="0" style="table-layout: fixed;">
						<tr>
							<td bgcolor="#1873aa" style="width: 6px;">
								&nbsp;
							</td>
							<td width="165">
								<table width="165" height="100%" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="28" background="images/main_40.jpg">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="19%">
														&nbsp;
													</td>
													<td width="81%" height="20">
														<span class="STYLE11">管理菜单</span>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<table width="151" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="23" background="images/main_47.jpg"
																	id="imgmenu1" class="menu_title"
																	onMouseOver="this.className='menu_title2';"
																	onClick="showsubmenu(1)"
																	onMouseOut="this.className='menu_title';"
																	style="cursor: hand">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td width="18%">
																				&nbsp;
																			</td>
																			<td width="82%" class="STYLE1">
																				查 看
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td background="images/main_51.jpg" id="submenu1">
																	<div class="sec_menu">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table width="90%" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<tr>
																							<td width="16%" height="25">
																								<div align="center">
																									<img src="images/left.jpg" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td width="84%" height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20" style="cursor: hand"
																											onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																											onmouseout="this.style.borderStyle='none'">
																											<span class="STYLE3"><a
																												href="http://localhost:8080/myweb/ShowStu?pageNow=1">查看人员</a>
																											</span>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="images/left.jpg" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20" style="cursor: hand"
																											onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																											onmouseout="this.style.borderStyle='none'">
																											<span class="STYLE3"> <a
																												href="http://localhost:8080/myweb/ShowPro?type=1&pageNow=1">查看项目</a>
																											</span>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>

																						<!--  
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                          <span class="STYLE3"></span></td>
                        </tr>
                    </table></td>
                  </tr>
                  -->

																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="5">
																					<img src="images/main_52.jpg" width="151"
																						height="5" />
																				</td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="23" background="images/main_47.jpg"
																	id="imgmenu2" class="menu_title"
																	onmouseover="this.className='menu_title2';"
																	onclick="showsubmenu(2)"
																	onmouseout="this.className='menu_title';"
																	style="cursor: hand">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td width="18%">
																				&nbsp;
																			</td>
																			<td width="82%" class="STYLE1">
																				添加
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td background="images/main_51.jpg" id="submenu2">
																	<div class="sec_menu">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table width="90%" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<tr>
																							<td width="16%" height="25">
																								<div align="center">
																									<img src="images/left.jpg" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td width="84%" height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20" style="cursor: hand"
																											onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																											onmouseout="this.style.borderStyle='none'">
																											<span class="STYLE3"><a
																												href="http://localhost:8080/myweb/UpdateStudent?type=addView">添加人员</a>
																											</span>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="images/left.jpg" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20" style="cursor: hand"
																											onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																											onmouseout="this.style.borderStyle='none'">
																											<span class="STYLE3"><a
																												href="http://localhost:8080/myweb/UpdateProject?type=addView">添加项目</a> </span>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<!--  
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3">数据更新</span></td>
                              </tr>
                          </table></td>
                        </tr>
                        -->

																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="5">
																					<img src="images/main_52.jpg" width="151"
																						height="5" />
																				</td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>


												<!-- 
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" background="images/main_47.jpg" id="imgmenu3" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(3)" onmouseout="this.className='menu_title';" style="cursor:hand"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="82%" class="STYLE1">安全管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="images/main_51.jpg" id="submenu3" style="DISPLAY: none"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'"><span class="STYLE3">企业安全</span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'"><span class="STYLE3">信息安全管理</span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'"><span class="STYLE3">安全审计</span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.jpg" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'"><span class="STYLE3">网站安全</span></td>
                              </tr>
                          </table></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="5"><img src="images/main_52.jpg" width="151" height="5" /></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table></td>
      </tr>
      -->



											</table>
										</td>
									</tr>


									<tr>
										<td height="18" background="images/main_58.jpg">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="18" valign="bottom">
														<div align="center" class="STYLE3">
															版本：2015V1.0
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<script>




function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="images/main_47.jpg";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="images/main_48.jpg";
}
}

</script>




							</td>
						</tr>
					</table>
				</td>


				<td width="6" style="width: 6px;" valign="middle" bgcolor="1873aa"
					onclick=switchSysBar()>
					<SPAN class=navPoint id=switchPoint title=关闭/打开左栏><img
							src="images/main_55.jpg" name="img1" width=6 height=40 id=img1>
					</SPAN>
				</td>

				<!--开始界面不显示table，就把src去掉-->
				<td width="100%" align="center" valign="top">

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" background="images/tab_05.jpg">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="12" height="30">
											<img src="images/tab_03.jpg" width="12" height="30" />
										</td>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="46%" valign="middle">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="5%">
																	<div align="center">
																		<img src="images/tb.jpg" width="16" height="16" />
																	</div>
																</td>
																<td width="15%" class="STYLE1">
																	当前页面： [ 查看项目 ]
																</td>
																<td width="35%" class="STYLE1">
																	<span class="STYLE3">欢迎&nbsp;&nbsp;&lt;&nbsp;&nbsp;&nbsp;<%=nameString%>&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;访问~O(∩_∩)O~</span>
																</td>
																<td width="45%" class="STYLE1">
																	<div id="clock" align="right"></div>
																</td>
															</tr>
														</table>
													</td>

													<!-- 自己添加内容 
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <span>欢迎 <%=nameString%> 访问~O(∩_∩)O</span>
            </tr>
            </table></td>
            -->




												</tr>
											</table>
										</td>
										<td width="16">
											<img src="images/tab_07.jpg" width="16" height="30" />
										</td>
									</tr>
								</table>
							</td>
						</tr>







						<!-- 表单内容 -->

						<%

Project project=(Project)request.getAttribute("project");
Student leader=(Student )request.getAttribute("leader");
ArrayList<Student>team=(ArrayList<Student>)request.getAttribute("team" );
ArrayList<Task> tasks=(ArrayList<Task> )request.getAttribute("tasks" );
HashMap<Integer, ArrayList<Assignment> > hashMap=(HashMap<Integer, ArrayList<Assignment> > )request.getAttribute("hashMap" );

%>
						<tr>
							<td>

								<h3>
									项目简介
								</h3>
								<table border="1" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											项目号
										</td>
										<td><%=project.getProid() %></td>
									</tr>
									<tr>
										<td>
											项目名称
										</td>
										<td><%=project.getProname() %></td>
									</tr>
									<tr>
										<td>
											项目描述
										</td>
										<td><%=project.getDes() %></td>
									</tr>
								</table>
								<hr />

								<h3>
									人员介绍
								</h3>
								<table border="1" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="11" background="images/tab_05.jpg">
											项目负责人
										</td>
									</tr>
									<tr>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											年龄
										</td>
										<td>
											家乡
										</td>
										<td>
											性别
										</td>
										<td>
											邮箱
										</td>
										<td>
											电话
										</td>
										<td>
											学院
										</td>
										<td>
											专业
										</td>
										<td>
											年级
										</td>
										<td>
											班级号
										</td>
									</tr>
									<tr>
										<td><%=leader.getStuid() %></td>
										<td><%=leader.getName() %></td>
										<td><%=leader.getAge() %></td>
										<td><%=leader.getHometown() %></td>
										<td><%=leader.getSex() %></td>
										<td><%=leader.getEmail() %></td>
										<td><%=leader.getTel() %></td>
										<td><%=leader.getAcademy() %></td>
										<td><%=leader.getMajor() %></td>
										<td><%=leader.getClassString() %></td>
										<td><%=leader.getClassid() %></td>
									</tr>
								</table>

								<table border="1" cellspacing="0" cellpadding="0">
									<tr>
										<td background="images/tab_05.jpg">
											团队号
										</td>
										<td colspan="11" background="images/tab_05.jpg">
											团队成员
										</td>
									</tr>

									<tr>
										<td rowspan="<%=(team.size() +1)%>"><%=project.getTeamid() %></td>
										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											年龄
										</td>
										<td>
											家乡
										</td>
										<td>
											性别
										</td>
										<td>
											邮箱
										</td>
										<td>
											电话
										</td>
										<td>
											学院
										</td>
										<td>
											专业
										</td>
										<td>
											年级
										</td>
										<td>
											班级号
										</td>
									</tr>
									<%
for(Student s:team){
	
%>
									<td><%=s.getStuid() %></td>
									<td><%=s.getName() %></td>
									<td><%=s.getAge() %></td>
									<td><%=s.getHometown() %></td>
									<td><%=s.getSex() %></td>
									<td><%=s.getEmail() %></td>
									<td><%=s.getTel() %></td>
									<td><%=s.getAcademy() %></td>
									<td><%=s.getMajor() %></td>
									<td><%=s.getClassString() %></td>
									<td><%=s.getClassid() %></td>
									</tr>

									<%
	}
%>

								</table>
								<hr />

								<h3>
									任务简介
								</h3>
								<%
if(tasks.size()==0){
	%>
								<h4>
									暂未创建任务
								</h4>
								<%
}else{ 
	%>


								<table border="1" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											任务序号
										</td>
										<td>
											任务内容
										</td>
										<td>
											分配成员
										</td>
										<td>
											提交文件(点击下载)
										</td>
										<td>
											提交时间
										</td>
										<td>
											提交简述
										</td>
									</tr>
									<tr>
										<%
for(Task t:tasks){
	ArrayList<Assignment> ass=hashMap.get(t.getTaskid());
	%>
										<td rowspan="<%=ass.size() %>"><%=t.getSeq() %></td>
										<td rowspan="<%=ass.size() %>"><%=t.getTaskname() %></td>
										<%
	
	if(ass.size()==0){
		%>
										<td colspan="4">
											暂未分配任务
										</td>
									</tr>
									<%
	}else{ 
		%>


									<%
		for(Assignment a:ass){
			
			if(a.getFilename()==null){
				
				%>
									<td><%=a.getStuid() %></td>
									<td colspan="3">
										暂未提交任务
										</tr>
										<%
			}else{
				%>
										<td><%=a.getStuid() %></td>
										<td>
											<a
												href="http://localhost:8080/myweb/Download?type=downOther&stuid=<%=a.getStuid() %>&taskid=<%=t.getTaskid()%>&filename=<%=a.getFilename() %>"><%=a.getFilename() %></a>
										</td>
										<td><%=a.getSubtime() %></td>
										<td><%=a.getMydes() %></td>
										</tr>

										<%
			}
			
			
		}//for ass
	}//else
		%>

										<%
}//for:task
%>
									
								</table>






								<%
}//else
%>

								<hr />













								<!--  
  
  <tr>
    <td height="20" bgcolor="#FFFFFF"><div align="center">
      <input type="checkbox" name="checkbox3" value="checkbox" />
    </div></td>
    <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
      <div align="center">02</div>
    </div></td>
    <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">13813916585</span></div></td>
    <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">2007-11-16 15:00:20 </span></div></td>
    <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">tiezhu0902@163.com</span></div></td>
    <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">南京科技股份有限公司…</span></div></td>
    <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE4"><img src="images/edt.jpg" width="16" height="16" />编辑&nbsp; &nbsp;<img src="images/del.jpg" width="16" height="16" />删除</span></div></td>
  </tr>
  
  -->
					</table>
				</td>
				<td width="8" background="images/tab_15.jpg">
					&nbsp;
				</td>
			</tr>
		</table>
		</td>
		</tr>














		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="6" background="images/main_59.jpg"
					style="line-height: 6px;">
					<img src="images/main_59.jpg" width="6" height="6">
				</td>
				<td background="images/main_61.jpg" style="line-height: 6px;">
					&nbsp;
				</td>
				<td width="6" background="images/main_61.jpg"
					style="line-height: 6px;">
					<img src="images/main_62.jpg" width="6" height="6">
				</td>
			</tr>
		</table>





		<!-- 对应520，否则显示有问题 <table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
		</table>
		</table>




	</body>
</html>

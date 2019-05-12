<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!--m-top start-->
	<div class="m-top">
		<div class="t-head">
			<div class="h-logo">
				<a href="<%=path %>/index2.jsp"><img src="<%=path %>/images/logo1.svg" alt="echo云音乐" title="echo云音乐"/></a>
			</div>
			<div class="h-nav">
				<ul> <li> <c:if test="${userFront==null}">
								<a href="login.jsp">登录</a>
								<span class="separator">|</span>
								<a href="reg.jsp">注册</a>
								<span class="separator">|</span>
							</c:if>
							</li>
                <c:if test="${userFront!=null}">
					<li style="margin-left:80px;"><a href="index2.jsp">首 页</a>  </li>
					<li><a href="myInfo.jsp">个人信息</a></li>
					<li> <a href="myPwd.jsp">修改密码</a></li>
					<li><a href="page_listMusics.action">我的音乐</a>  </li>
					<li><a href="page_listMysheets.action">我的歌单</a></li>
					<li> <a id="loginindex2">退出</a></li>

				</c:if>
				</ul>
			</div>
<c:if test="${userFront!=null}">
			<div class="h-search" style="position:relative;">
				<form name="info" id="in" action="page_listMusics.action" method="post" style="width:100%;height:100%">
					<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" placeholder="搜索歌手，歌曲" name="all_name" id="all_name" value="${paramsMusic.all_name}" class="inputstyle"/>&nbsp;

					<%--<input type="hidden"  onclick="serch();" value="">--%>
				</form>

				<div class="s_xiala" style="overflow:auto;position:absolute;top:41px;height:160px;width:300px;background:rgba(255,255,255,.9);z-index:999;display:none;">
					<ul class="s_ul">
						<!-- <li style="width:100%;height:30px;margin:0;"><a href="javascript:void(0)">bigbang-if you.mp3---《M》</a></li>
						<li style="width:100%;height:30px;margin:0;"><a href="javascript:void(0)">bigbang-if you.mp3---《M》</a></li> -->
					</ul>
				</div>
			</div>
</c:if>

		</div>
	</div>
	<!--end m-top-->
	<script src="<%=path %>/js/jquery.js"></script>
<script >
    $("#loginindex2").bind('click',function(){
        $.post('loginindex2',null,
            function(responseObj) {
                if(responseObj.success) {
                    alert('退出成功！');
                    window.location.href="index2.jsp";
                }else  if(responseObj.err.msg){
                    alert('退出异常：'+responseObj.err.msg);
                }else{
                    alert('退出异常：服务器异常！');
                }
            },'json');
    });

</script>
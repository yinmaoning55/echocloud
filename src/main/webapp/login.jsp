<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache"><link rel="stylesheet" href="layui-v2.4.5/layui/css/layui.css">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css2/style.css">
<link rel="stylesheet" type="text/css" href="css2/reg.css">
<link rel="stylesheet" type="text/css" href="css2/info.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">

<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js2/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">


</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 .regTable td{
 	height:40px;
 }
 .regTable input{
 	height:30px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="list">

		<button class="layui-btn">登陆页面</button>

		 <div class="list_info">
		 	 <form name="info" id="info" class="layui-form" >
		 	 <input type="hidden" name="user_card" value=""/>
		 	 <input type="hidden" name="user_powers" value=""/>
			 <table class="layui-table" style="">
				<tr>
					<td class="theme" colspan="2">登录echo云音乐网站</td>
				</tr>
				<tr>
					<td align="right" width="20%"><i class="layui-icon layui-icon-username" style="font-size: 30px; color: #14160e;"></i></td>
					<td align="left" width="80%">

						<input type="text" name="user_name" id="user_name" required lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
					</td>

				</tr>
				<tr>
					<td align="right" width="20%"><i class="layui-icon layui-icon-password" style="font-size: 30px; color: #14160e;"></i></td>
					<td align="left" width="80%">

						<input type="password" name="user_pass" id="user_pass" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
					</td>
				</tr>

				<tr>
					<td align="right" width="20%"><i class="layui-icon layui-icon-circle" style="font-size: 30px; color: #14160e;"></i></td>
					<td align="left" width="80%">

						<input type="text" name="random" id="random" style="width:120px;" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
						&nbsp;<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%"></td>
					<td align="left" width="80%">
						<input type="button" id="add" class="layui-btn layui-btn-radius" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset"  class="layui-btn layui-btn-radius" value="清空"/>
					</td>
				</tr>
		 	 </table>
		 	 </form>
		 </div>

	</div>

</div>
<script language="javascript" type="text/javascript">
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
$(document).ready(function(){
	var add = $("#add");
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	var random = $("#random");

	var name=/^[a-z][a-z0-9_]{3,15}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
    var email=/^[\w]+[@]\w+[.][\w]+$/;
    var IdCard=/^\d{15}(\d{2}[A-Za-z0-9])?$/;
    var Phone=/^\d{11}$/;

    add.bind("click",function(){


		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名或密码不能为空");
			return;
		}
		if(random.val()==''){
			alert("验证码不能为空");
			return;
		}
		var aQuery = $("#info").serialize();
		$.post('LoginInSystem.action',aQuery,
			function(responseObj) {
		    console.log(aQuery)
					if(responseObj.success) {
						 alert('登录成功！');
						 window.location.href="index2.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}
		 },'json');
	});
});
</script>

</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css2/style.css">
<link rel="stylesheet" type="text/css" href="css2/store.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" href="layui-v2.4.5/layui/css/layui.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js2/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var user_id="${userFront.user_id}";
	if(user_id==null || user_id==''){
		alert("请先登录！");
		window.location.href="page_index.action";
	}
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  修改个人信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveUserFront.action" method="post">    
				 <input type="hidden" name="user_id" value="${userFront.user_id}"/>
				 <table class="layui-table" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px">用户名：</td>
			          <td width="35%">${userFront.user_name}</td>
			          <td width="15%" align="right" style="padding-right:5px"><font color="48BB86">*</font> 姓名：</td>
			          <td width="35%">
			            <input type="text" id="real_name" name="real_name" class="layui-input" value="${userFront.real_name}"/>
			          </td> 
			        </tr> 
			         <tr>
			          <td align="right" style="padding-right:5px"><font color="48BB86">*</font> 性别：</td>
			          <td>
			             <input type="radio" name="user_sex" id="sex1" value="1"/>男&nbsp;&nbsp;
			             <input type="radio" name="user_sex" id="sex2" value="2"/>女
			          </td>
			          <td width="15%" align="right" style="padding-right:5px"><font color="48BB86">*</font> 昵称：</td>
			          <td width="35%">
			            <input type="text" id="nick_name" name="nick_name" class="layui-input" value="${userFront.nick_name}"/>
			          </td> 
			       </tr> 
			       <tr>
			          <td align="right" style="padding-right:5px">邮箱：</td>
			          <td>
			             <input type="text" id="user_mail" name="user_mail" class="layui-input" value="${userFront.user_mail}"/>
			          </td>
			          <td align="right" style="padding-right:5px">电话：</td>
			          <td>
			             <input type="text" id="user_phone" name="user_phone" class="layui-input" value="${userFront.user_phone}"/>
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">

						  <input type="button" id="saveBtn"  class="layui-btn layui-btn-radius" value="修 改"/>
			            <span style="color:red">${tip }</span>
			          </td>
			        </tr>
				 </table>
				 </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		 var user_sex = "${userFront.user_sex}";
		 $("#sex"+user_sex).attr('checked','checked');
		 
		 var num=/^\d+$/;
		 var email=/^[\w]+[@]\w+[.][\w]+$/;
	     var IdCard=/^\d{15}(\d{2}[A-Za-z0-9])?$/;
	     var Phone=/^\d{11}$/;
	     
	     var real_name = $("#real_name");
	     var nick_name = $("#nick_name");
	 	 var user_mail = $("#user_mail");
	 	 var user_phone = $("#user_phone");
		 $("#saveBtn").bind('click',function(){
			if(real_name.val()==''){
				alert("姓名不能为空");
				return;
			}
			if(nick_name.val()==''){
				alert("昵称不能为空");
				return;
			}
			if(user_mail.val()!='' && !email.exec(user_mail.val())){
				alert("邮箱格式不正确");
				return;
			}
			if(!Phone.exec(user_phone.val())){
				alert("联系电话必须为11位数字");
				return;
			}
			var aQuery = $("#info").serialize();  
			$.post('page_saveUserFront.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('修改成功！');
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
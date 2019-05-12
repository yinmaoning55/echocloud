<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${mysheet!=null && mysheet.mysheet_id!=''?'编辑':'发布'}我的歌单信息</title>
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
<script charset="utf-8" src="admin/editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="admin/editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript"> 
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
			<div class="title">个人中心  &gt;&gt;  ${mysheet!=null && mysheet.mysheet_id!=''?'编辑':'发布'}我的歌单信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveMysheet.action" method="post">    
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id }"/>
				 <input type="hidden" name="mysheet_id" id="mysheet_id" value="${mysheet.mysheet_id}"/>
				 <table class="layui-table" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 歌单名称：</td>
			          <td width="*">
			          	<input type="text" class="layui-input" name="mysheet_name" id="mysheet_name" value="${mysheet.mysheet_name}"/>
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <c:if test="${mysheet!=null && mysheet.mysheet_id!=''}">
			          	<input type="button" id="editBtn" Class="layui-btn layui-btn-radius" value="编 辑"/>
			          	</c:if>
			            <c:if test="${mysheet==null || mysheet.mysheet_id==''}">
			          	<input type="button" id="addBtn" Class="layui-btn layui-btn-radius" value="新 增" />
			          	</c:if>
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
		$("#addBtn").bind('click',function(){
			if($("#mysheet_name").val()==''){
				alert('歌单名称不能为空');
				return;
			}
			$("#mysheet_id").val(0);
			var aQuery = $("#info").serialize();  
			$.post('page_addMysheet.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('新增成功！');
							 window.location.href="page_listMysheets.action";
						}else  if(responseObj.err.msg){
							 alert('新增失败：'+responseObj.err.msg);
						}else{
							 alert('新增失败：服务器异常！');
						}	
			},'json');
		 });
		
		 $("#editBtn").bind('click',function(){
			if($("#mysheet_name").val()==''){
				alert('歌单名称不能为空');
				return;
			}
			var aQuery = $("#info").serialize();  
			$.post('page_saveMysheet.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMysheets.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
</script>
</body>
</html>
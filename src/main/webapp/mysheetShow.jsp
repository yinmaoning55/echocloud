<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的歌单</title>
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
			<div class="title">个人中心  &gt;&gt;  我的歌单</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_listMysheets.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"class="layui-input"/>
				 <table class="layui-table" style="margin-bottom:5px;">
				 	<tr>
						<td colspan="7" align="right">
							歌单名称：
      						<input type="text" id="mysheet_name" name="mysheet_name" style="width:150px;height: 22px;top: 100px;"
      							value="${paramsMysheet.mysheet_name}" class="inputstyle"/>&nbsp;
      					    <input type="submit" value="搜索"   class="layui-btn layui-btn-radius"/>&nbsp;
							<input type="button" id="delBtn" value="删除" class="layui-btn layui-btn-radius layui-btn-danger"/>&nbsp;
							<input type="button" value="新增" onclick="window.location.href='page_addMysheetShow.action';" class="layui-btn layui-btn-radius"/>
						</td>
					</tr>
					<tr class="head_text">
						<td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
						<td align="center">歌单名称</td>
						<td align="center">操作</td>
					</tr>
					<c:if test="${mysheets!=null &&  fn:length(mysheets)>0}">
   					<c:forEach items="${mysheets}" var="mysheet" varStatus="status">
					<tr>
						 <td width="" align="center"><input type="checkbox" name="chkid" value="${mysheet.mysheet_id}"  Style="vertical-align:text-bottom;"/></td>
						 <td width="" align="center" title="${mysheet.mysheet_name}">
						 	${mysheet.mysheet_name}
						 </td>
					     <td width="" align="center">
					     	<a href="page_editMysheet.action?mysheet_id=${mysheet.mysheet_id}">编辑</a>
					     </td>
					</tr>
					</c:forEach>
				    </c:if>
				    <c:if test="${mysheets==null ||  fn:length(mysheets)==0}">
				    <tr>
				      <td height="60" colspan="7" align="center"><b>&lt;不存在我的歌单信息&gt;</b></td>
				    </tr>
				    </c:if>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.action="page_listMysheets.action";
	   document.info.submit();
	}
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  document.getElementById("pageNo").value=pagenum;
	  document.info.action="page_listMysheets.action";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMysheets.action";
		document.info.submit();
	}
	function CheckAll(obj) 
	{
		var checks=document.getElementsByName("chkid");
	    for (var i=0;i<checks.length;i++)
		{
		    var e = checks[i];
		    e.checked = obj.checked;
		}
	    
	}
	$(document).ready(function(){
		$("#delBtn").bind('click',function(){
			var checks=document.getElementsByName("chkid");
		    var ids="";
			for (var i=0;i<checks.length;i++)
		    {
		        var e = checks[i];
				if(e.checked==true)
				{
				  if(ids=="")
				  {
				    ids=ids+e.value;
				  }
				  else
				  {
				    ids=ids+","+e.value;
				  }
				}
		    }
		    if(ids=="")
		    {
		       alert("请至少选择一个要删除的歌单！");
		       return false;
		    }
		    if(confirm('确认删除吗!?'))
		    {
		    	var aQuery = {
						'ids':ids
				};  
				$.post('page_delMysheets.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.href="page_listMysheets.action";
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>
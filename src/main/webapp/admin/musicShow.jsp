<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歌曲信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../layui-v2.4.5/layui/css/layui.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
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


function serch()
{
   document.info.action="Admin_listMusics.action";
   document.info.submit();
}
function del()
{
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
       alert("请至少选择一个要删除的歌曲！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delMusics.action?ids="+ids;
       document.info.submit();
    }
    
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
  document.info.action="Admin_listMusics.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listMusics.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">歌曲管理&gt;&gt;歌曲查询</span>
</div>
<form name="info" id="info" action="Admin_listMusics.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%"  class="layui-table" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">歌曲列表</td>
    <td width="" align="right">
            歌曲：
      <input type="text" id="music_name" name="music_name" 
      		value="${paramsMusic.music_name}" class="inputstyle"/>&nbsp;
            歌手：
      <input type="text" id="music_author" name="music_author" 
      		value="${paramsMusic.music_author}" class="inputstyle"/>&nbsp;
            专辑：
      <input type="text" id="music_album" name="music_album" 
      		value="${paramsMusic.music_album}" class="inputstyle"/>&nbsp;
      <input type="button" value="搜索" onclick="serch();"  class="layui-btn layui-btn-radius"/>&nbsp;
      <input type="button" value="删除" onclick="del();"  class="layui-btn layui-btn-radius"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">歌曲</td>
     <td width="" align="center">歌手</td>
     <td width="" align="center">专辑</td>
     <td width="" align="center">红心推荐</td>

     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${musics!=null &&  fn:length(musics)>0}">
   <c:forEach items="${musics}" var="music" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${music.music_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsMusic.start}</td>
     <td width="" align="center">${music.music_name}</td>
     <td width="" align="center">${music.music_author}</td>
     <td width="" align="center">${music.music_album}</td>
     <td width="" align="center">${music.music_collect}</td>

     <td width="" align="center">
       <a href="Admin_queryMusic.action?music_id=${music.music_id}">查看</a>&nbsp;
       <a href="Admin_editMusic.action?music_id=${music.music_id}">编辑</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${musics==null ||  fn:length(musics)==0}">
   <tr>
     <td height="60" colspan="13" align="center"><b>&lt;不存在歌曲信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>
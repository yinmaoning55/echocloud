<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${music!=null && music.music_id!=''?'编辑':'添加'}歌曲名称信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../layui-v2.4.5/layui/css/layui.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num1 = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#music_name").val()==''){
			alert("歌曲名称不能为空！");
			return;
		}
		if($("#music_author").val()==''){
			alert("歌手姓名不能为空！");
			return;
		}
		$("#music_id").val(0);
		$("#info").attr('action','Admin_addMusic.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#music_name").val()==''){
			alert("歌曲名称不能为空！");
			return;
		}
		if($("#music_author").val()==''){
			alert("歌手姓名不能为空！");
			return;
		}
		$("#info").attr('action','Admin_saveMusic.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">歌曲名称管理&gt;&gt;${music!=null && music.music_id!=''?'编辑':'添加'}歌曲名称</span>
</div>
<form id="info" name="info" action="Admin_addMusic.action" method="post">   
<input type="hidden" id="music_id" name="music_id" value="${music.music_id}" /> 
<input type="hidden" id="pic_name" name="pic_name" value="${music.pic_name}" /> 
<input type="hidden" id="music_name2" name="music_name2" value="${music.music_name2}" /> 
<input type="hidden" id="lrc_name" name="lrc_name" value="${music.lrc_name}" /> 
<table width="800" align="center"  class="layui-table" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${music!=null && music.music_id!=''?'编辑':'添加'}歌曲名称</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
     	<tr>
		  <td width="150"align="right" style="padding-right:5px">歌曲附件：</td>
		  <td width="*" align="left">
		    <span id="userImg2">${music.music_name2}</span>
		    <span id="op2" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
        <tr>
		  <td align="right" style="padding-right:5px">上传歌曲：</td>
	      <td align="left"> 
	          <iframe name="uploadPage2" src="uploadImg2.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
	    <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 歌曲名称：</td>
          <td >
          	<input type="text" name="music_name" id="music_name" value="${music.music_name}" Style="width:345px;"/>
          </td>
		</tr> 
	     <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 专辑图片：</td>
          <td>
          	<img id="userImg" src="images/datas/${music.pic_name}" width="120" height="100" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
          </td>
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">上传图片：</td>
	      <td align="left"> 
	          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
		  <td width="150"align="right" style="padding-right:5px"><font color="red">*</font> 歌词附件：</td>
		  <td width="*" align="left">
		    <span id="userImg3">${music.lrc_name}</span>
		    <span id="op3" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
         <tr>
		  <td align="right" style="padding-right:5px">上传歌词：</td>
	      <td align="left"> 
	          <iframe name="uploadPage3" src="uploadImg3.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
		<tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 演唱歌手：</td>
          <td>
            <input type="text" style="width:345px;" name="music_author" id="music_author" value="${music.music_author}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 专辑名称：</td>
          <td>
            <input type="text" style="width:345px;" name="music_album" id="music_album" value="${music.music_album}"/> 
          </td>  
        </tr>
        <tr> 
          <td align="right" style="padding-right:5px">音乐描述：</td>
          <td>
            <textarea style="width:500px;height:100px" name="music_desc"  id="noticeContent">${music.music_desc}</textarea>
          </td>
        </tr>   
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <c:if test="${music!=null && music.music_id!=''}">
          	<input type="button" id="editBtn" class="layui-btn layui-btn-radius" value="编 辑"/>
          	</c:if>
          	<c:if test="${music==null || music.music_id==''}">
          	<input type="button" id="addBtn" class="layui-btn layui-btn-radius" value="添 加" />
          	</c:if>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
<script>        
 
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看歌曲信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../layui-v2.4.5/layui/css/layui.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">歌曲管理&gt;&gt;查看歌曲</span>
</div>
<form id="info" name="info" action="Admin_addMusic.action" method="post">   
<input type="hidden" id="music_id" name="music_id" value="${music.music_id}" /> 
<table width="800"  class="layui-table" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">查看歌曲</TD>
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
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 歌曲名称：</td>
          <td width="*">
          	${music.music_name}
          </td>
		</tr> 
		<tr> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 歌曲文件：</td>
          <td align="left" style="padding:5px;line-height:22px">
          <a href='DownLoad.jsp?fileName=${music.music_name2}&fileType=2'>${music.music_name2}</a>
          </td>
        </tr>   
	     <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 专辑图片：</td>
          <td>
          	<img id="userImg" src="images/datas/${music.pic_name}" width="120" height="100" style="border:0px;"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 歌词文件：</td>
          <td>
          	<a href='DownLoad.jsp?fileName=${music.lrc_name}&fileType=2'>${music.lrc_name}</a>
          </td>
		</tr> 
		<tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 专辑名称：</td>
          <td>
          	${music.music_album}
          </td>
		</tr> 
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 歌手姓名：</td>
          <td>
          	${music.music_author}
          </td>
		</tr> 
		<tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 红心推荐：</td>
          <td>
          	${music.music_collect}
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
           <input type="button" onclick="window.history.back();" class="layui-btn layui-btn-radius" value="返 回"/>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>
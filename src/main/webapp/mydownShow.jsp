<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
<style type="text/css">
	#hbzj{
		position:absolute;
		zIndex:999;
		width:100px;
		height:100px;
		top:70px;
		left:200px;
		border:5px solid #a7a7f4;
		background-color:white; 
		font-size:11px!important;
		display:none;
	}	
</style>
<head>
<script language="javascript" type="text/javascript"> 
if(${userFront==null||userFront==''})
{
  window.location.href="login.jsp";
} 
function serch()
{
   document.info.action="page_listMydowns.action";
   document.info.submit();
}

function ChangePage(pagenum)
{	
	document.getElementById("pageNo").value=pagenum;
	document.info.action="page_listMydowns.action";
	document.info.submit();
}	
</script>
<script type="text/javascript" src="js2/jquery-1.4.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10, IE=11, IE=12"/>
<meta name="application-name" content="歌曲音乐盒"/>
<title>歌曲音乐盒</title>
<!--[if IE]>
	<script type="text/javascript">
		document.execCommand("BackgroundImageCache", false, true);
	</script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="css/qfWhygaf.css"/>
<!--[if IE 6]>
     <link type="text/css" rel="stylesheet" href="css/player_ie6.css"/>
<![endif]-->
</head>
<body class="mbox wrapper skin-default" data-skin="default">
<div class="default-main" id="mainContent" alog-alias="mbox-main-content" monkey="mbox-main-content">
  <div class="main-wrapper">
  
    <!-- 左边导航开始 -->
    <div class="mb-layout-bd column1" id="leftCol">
      <div class="leftbar-bottom-bg">
        <div class="leftbar-outer">
           <div class="leftbar">
            <div class="list list-most " id="list_late"> <a  class="icon column1-icon list-most-icon" hidefocus="true"></a> <a class="text" href="page_listMusics.action">所有歌曲</a> <a  class="listening-icon" hidefocus="true"></a> </div>
            <div class="list list-history" id="list_often"> <a  class="icon column1-icon list-history-icon" hidefocus="true"></a> <a class="text" href="page_listMyplays.action">播放记录</a> <a  class="column1-icon listening-icon" hidefocus="true"></a> </div>
            <div class="list list-download selected" id="list_download"> <a  class="icon column1-icon list-download-icon" hidefocus="true"></a> <a class="text"  href="page_listMydowns.action">下载历史</a> <a  class="column1-icon listening-icon" hidefocus="true"></a> </div>
            <div class="list list-fm" id="list_fm"> <a  class="icon column1-icon list-fm-icon" hidefocus="true"></a> <a class="text">我的歌单</a> <a  class="column1-icon listening-icon" hidefocus="true"></a> </div>
            <c:forEach items="${mysheets}" var="mysheet" varStatus="status">
            <div class="list list-collect">　　　　&gt;<a class="text" href="page_listMycollects.action?mysheet_id=${mysheet.mysheet_id}">${mysheet.mysheet_name}</a> <a  class="column1-icon listening-icon" hidefocus="true"></a> </div>
          	</c:forEach>
          </div>
        </div>
      </div>
    </div>
	<!-- 左边导航结束 -->
	
	<!-- 右边字幕开始 -->
    <div class="mb-layout-bd column3" id="lrcCol" style=" right: 0px;">
	  <div class="album-wrapper">
		<div class="album">
			<a target="_blank">
			<img width="135" height="135" src="images/default_album.jpg">
			</a>
		<div class="album-name">
			<a target="_blank"></a>
		</div>
		</div>
		<div class="split"></div>
		</div>
      <div class="lrc-wrapper" id="lrcWrap">
	      <div class="no-lrc">
				<div></div>
				<span class="no-lrc-hint">该歌曲暂时没有歌词</span>
		  </div>
	     <ul id="lrc_list">
         </ul>
      </div>
      <div class="link-wrapper">
        <div class="link-source" id="linkSource"> 来源:<a></a> </div>
        <div class="split2"></div>
        <div class="clipboard-wrapper"> 
		    <a id="downloadLrc" class="clip-btn"  target="_blank">下载歌词</a>
		    <a id="errorLrc" class="clip-btn" style="display:none">歌曲信息报错</a>
		</div>
      </div>
      <div class="ui-resizable" id="lrcResize">
        <div class="resizable-icon" id="widResize"></div>
      </div>
    </div>
	<!-- 右边字幕结束 -->
	
	<!-- 中央列表开始 -->
    <div class="mb-layout-bd column2" onselectstart="return false;"  unselectable="on" style="-moz-user-select:none;right:252px">
        <div class="tab-main ui-tabs ui-widget ui-widget-content ui-corner-all" id="tab">
		     <div class="tab-content cfix" >
			      <div id="page-song" class="tab-page reelList_274789 ui-reelList ui-widget ui-tabs-panel ui-widget-content ui-corner-bottom fullHeartShow" style="top: 0px;">
				       <!-- 头部开始 -->
					   <div class="ui-reelList-header ui-state-default">
						  <div class="ui-reelList-header-column c0">
						       <span class="sort-item" data-sortkey="songName">歌曲</span>
						  </div>
						  <div class="ui-reelList-header-column c1"><span class="sort-item" data-sortkey="artistName">歌手</span></div>
						  <div class="ui-reelList-header-column c2"><span class="sort-item" data-sortkey="albumName">专辑</span></div>
						  <div class="ui-reelList-header-column c3"><span class="sort-item" data-sortkey="heartCol"></span></div>
						</div>
						 <!-- 头部结束 -->
						 <!-- 中间开始 -->
						<div class="ui-reelList-viewport">
						  <div class="ui-reelList-canvas" style="width: 100%; height: 155px;">
						     
						  </div>
						  <div class="ui-reelList-centerLoading">载入中...</div>
						</div>
						<!-- 中间结束 -->
						<!-- 底部开始 -->
						<div class="ui-reelList-footer" style="">
						      <div style="display: block;" id="myFooter" class="p-footer footerTool-no-checked">
								<%--<div class="select-all-combo">
								</div>
								<div style="display: block;" class="playlist-button store-button"></div>
								<div style="display: block;" class="playlist-button add-button"></div>
								<div style="display: block;" class="playlist-button delete-button"></div>
								<div style="display: none;" class="playlist-button deletefromhere-button"></div>
								<div style="display: block;" class="playlist-button download-button"></div>
								<div class="playlist-length">共有<span>6</span>首歌</div>--%>
								<div class="playlist-length" style="width:480px">
									<!-- 搜索开始 -->
									<div>
									  <div style="width:220px;float:left;">
										   <form name="info" id="info" action="page_listMydowns.action" method="post" style="width:100%;height:100%">
										   <input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
									   	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									       <input type="text" name="all_name" id="all_name" value="${paramsMydown.all_name}" class="inputstyle"/>&nbsp;
									       <input type="button" value=" 搜 索 " onclick="serch();" class="btnstyle"/>
									       </form>
									  </div>
									  <div style="width:260px;float:left;text-align:right">
									  	 <div id="page" style="margin-right:10px">
											共 ${paperUtil.totalCount} 条，第 ${paperUtil.pageCount==0?0:paperUtil.pageNo}/${paperUtil.pageCount} 页
											<c:if test="${paperUtil.pageNo > 1 }">
												&nbsp;　<a href="javascript:ChangePage('1');" class="alinkstyle">首页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${paperUtil.pageNo-1}');" class="alinkstyle">前页</a>
											</c:if>
											<c:if test="${paperUtil.pageNo <= 1 }">
												&nbsp;　首页
											    &nbsp;前页
											</c:if>
											<c:if test="${paperUtil.pageNo < paperUtil.pageCount}">
										        <a href="javascript:ChangePage('${paperUtil.pageNo+1}');" class="alinkstyle">后页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${paperUtil.pageCount}');" class="alinkstyle">末页</a>
										    </c:if> 
										    <c:if test="${paperUtil.pageNo >= paperUtil.pageCount}">
												&nbsp;后页
											    &nbsp;末页
											</c:if>
										 </div>
									  </div>
									</div>
								</div>
							  </div>
						</div>
						<!-- 底部结束 -->
				  </div>
			 </div>
		</div>
    </div>
	<!-- 中央列表结束 -->
	
  </div>
</div>

<!-- 顶部工具栏 开始-->
<div class="mb-layout-hd cmb-comm" alog-alias="mbox-header" monkey="mbox-header">
  <div class="top-banner">
    <div class="logo-wrapper">
      <div class="logo"></div>
    </div>
	 <div id="searchBar">
      <a style="margin-left:50px"> 音乐是美好的	心灵是纯净的。</a>
    </div>
	
    <div id="userBar" class="user-bar">
      <ul class="user-info user-unlogged">
        <li> <a href="page_listMusics.action" >首 页</a> </li>
        <li class="separator">|</li>
        <li> <a href="myInfo.jsp" >个人信息</a> </li>
        <li class="separator">|</li>
        <li> <a href="myPwd.jsp" >修改密码</a> </li>
        <li class="separator">|</li>
        <li> <a href="page_listMysheets.action" >我的歌单</a> </li>
        <li class="separator">|</li>
        <li> <a id="loginOutBtn" href="javascript:void(0)">退出</a> </li>
        <li class="separator">|</li>
      </ul>
    </div>
  </div>
</div>
<!-- 顶部工具栏 结束-->
<div class="mb-layout-ft minwidth" onselectstart="return false;" alog-alias="mbox-play-ctrl" monkey="mbox-play-ctrl">
<!-- jplayer 播放器开始-->
 <div class="panel" id="jp_container_1">  
     <div class="jp-playlist" style="height: 500px; overflow-y: auto; display:none">
		 <ul>
			 <!-- The method Playlist.displayPlaylist() uses this unordered list -->
			 <li></li>
		 </ul>
	 </div>
    <div class="panel-inner">
      <div class="left-panel" id="leftPanel">
        <ul class="play-btn">
          <li class="prev"> <a class="wg-button jp-previous" tabindex="1" hidefocus="true" title="上一首[←]"><span class="wg-button-inner"></span></a> </li>
           <li class="play stop jp-play"  tabindex="1" title="播放"> <a class="wg-button" hidefocus="true" ><span class="wg-button-inner"></span></a> </li>
		   <li class="play jp-pause" tabindex="1" title="暂停" style="display:none;"> <a class="wg-button" hidefocus="true" ><span class="wg-button-inner"></span></a> </li>
          <li class="next"> <a class="wg-button jp-next" tabindex="1" hidefocus="true" title="下一首[→]"><span class="wg-button-inner"></span></a> </li>
        </ul>
      </div>
      <div class="right-panel">
        <ul class="playmod" id="playMode">
          <li class="random-mode"> <a  hidefocus="true" title="随机播放" class="wg-button"><span class="wg-button-inner"></span></a> </li>
          <li class="single-mode"> <a hidefocus="true" title="单曲播放" class="wg-button"><span class="wg-button-inner"></span></a> </li>
          <li class="list-mode"> <a hidefocus="true" title="循环播放" class="wg-button cur"><span class="wg-button-inner"></span></a> </li>
        </ul>
		 <!--声音进度条开始 -->
        <div class="volume" id="volumeWrap"> 
			<a href="javascript:;" class="mute wg-button jp-mute" tabindex="1" title="开启静音"><span class="wg-button-inner"></span></a> 
		    <a href="javascript:;" class="mute muted wg-button jp-unmute" style="display:none;" tabindex="1" title="关闭静音"><span class="wg-button-inner"></span></a>
			<div class="vol-slider-wrapper">
			  <div id="volSlider" class="vol-slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all jp-volume-bar">
				<div class="ui-slider-range ui-widget-header ui-slider-range-min jp-volume-bar-value" >
					 <span class="ui-slider-range-inner"></span>
				</div>
				<div id="soundBar" style="width: 100%;"></div>
			  </div>
			</div>
        </div>
		 <!--声音进度条结束 -->
      </div>
      <div class="main-panel">
        <div class="title-wrapper" id="playTitle">
          <div class="title">
			<a class="songname" href="javascript:;"></a>
			<span class="split">-</span>
			<a class="artist" >
			   <a class="log" href="javascript:;"></a>
			</a>
		  </div>
          <ul class="icons" style="display:none">
            <li class="favor"><a class="" hidefocus="true"></a></li>
            <li class="share"><a class="" hidefocus="true" title="分享">分享</a></li>
            <li class="download"><a class="" title="下载" hidefocus="true"></a></li>
          </ul>
        </div>
        <div class="pane">
          <div class="time" id="timeWrap"> <span class="jp-current-time">00:00</span> <span class="split">/</span> <span class="jp-duration">00:00</span> </div>
          <div class="progress-wrapper" >
		    <!--进度条开始 --> 
            <div id="progressSlider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"  style="float:left;width:100%" >
			   <div class="ui-slider-progressbar jp-seek-bar" style="width:0%">
			        <div class="ui-slider-left"></div>
					<div class="ui-slider-right"></div>
			        <div class="ui-slider-range ui-widget-header ui-slider-range-min jp-play-bar" style="width:0%">
					   <span class="ui-slider-range-inner"></span>
				   </div> 
				   <div id="progreeBar" style="width: 100%;"></div>
			   </div>
            </div>
			<!--进度条结束 -->
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- jplayer 播放器结束-->
</div>
<div id="jquery_jplayer_1" class="jp-jplayer"></div>
<script src="js/sea.js"></script>
<script src="js/seajs-preload.js"></script>
<script>
var musicDatas = [];
var pageFlag = "mydown";
var addMyplay;
(function(){
        var modConfig = {
		    "AppVer": "1.1.5", //应用版本
		    "base": "./js/", //Sea.js 的基础路径
			"alias": {
			  "jquery": "jquery/jquery-1.10.2-min" ,// 别名配置  可以让文件的真实路径与调用标识分开，有利于统一维护。
			  "jplayer":'player/jquery.jplayer_1-min',
			  "jplayerList":'player/jplayer.playlist',
			  "map":'common/jmap',
			  "jquery-ui":'jquery/jquery-ui-min',
			  "lrc":'lrc/lrc-min'
			},    
		    "debug": false    // 是否启用调试模式
		};
 
        var mapVer = function(str){
            str = str + "?v=" + modConfig.AppVer; 
            return str; 
        }; 
 
        seajs.config({ 
            base: modConfig.base, 
            alias: modConfig.alias, 
            preload: [  // 预加载项
                "jquery"
            ], 
            debug: modConfig.debug || 0, 
            map:[ [/.*\.js$/, mapVer] ],  // 映射配置
			charset: 'UTF-8'  //// 文件编码
        });
        //初始化音乐
        <c:forEach items="${mydowns}" var="music" varStatus="status">
		var musicData = {
		 	id:"${music.music_id}",
       		title:"${music.music_name}",
       		title2:"${music.music_name2}",
       		artist:"${music.music_author}",
       		album:"${music.music_album}",
       		collect:"${music.music_collect}",
       		mp3:"admin/images/datas/${music.music_name2}",
       		lrc:"admin/images/datas/${music.lrc_name}",
       		poster:"admin/images/datas/${music.pic_name}"
		};
		musicDatas["${status.index}"] = musicData;
		</c:forEach>
		if(musicDatas.length==0){
			alert("暂无歌曲");
		}
        // 加载入口模块 
        seajs.use("main"); 
        
        //工具栏
        $("img[id^='praise_']").on("click",function(){
        	var music_id = $(this).attr("id").split("_")[1];
        	var music_collect = Number($("#praise_count_"+music_id).html());
        	$.post('page_addMypraise.action',{'music_id':music_id},
       			function(responseObj) {
       					if(responseObj.success) {	
       						 alert('红心点赞成功！');
       						$("#praise_count_"+music_id).html(music_collect+1)
       					}else  if(responseObj.err.msg){
       						 alert('红心点赞异常：'+responseObj.err.msg);
       					}else{
       						 alert('红心点赞异常：服务器异常！');
       					}	
       		 },'json');
        });
        $("img[id^='down_']").live("click",function(){
        	var music_id = $(this).attr("id").split("_")[1];
        	var music_name2 = $(this).attr("id").split("_")[2];
        	$.post('page_addMydown.action',{'music_id':music_id,'user_id':'${userFront.user_id}'},
       			function(responseObj) {
       					if(responseObj.success) {	
       						window.location.href="DownLoad.jsp?fileName="+music_name2+"&fileType=2";
       					}else  if(responseObj.err.msg){
       						 //alert('红心点赞异常：'+responseObj.err.msg);
       					}else{
       						 //alert('红心点赞异常：服务器异常！');
       					}	
       		 },'json');
        });
        addMyplay = function(index){
        	var music_id = musicDatas[index].id ;
        	$.post('page_addMyplay.action',{'music_id':music_id,'user_id':'${userFront.user_id}'},
       			function(responseObj) {
       					if(responseObj.success) {	
       					}else  if(responseObj.err.msg){
       						 //alert('红心点赞异常：'+responseObj.err.msg);
       					}else{
       						 //alert('红心点赞异常：服务器异常！');
       					}	
       		 },'json');
        }
        var music_id_now = "";
        $("img[id^='collect_']").live("click",function(){
        	var music_id = $(this).attr("id").split("_")[1];
        	if($("#hbzj").css("display")=="none"){
            	$("#hbzj").css("top",(parseInt($(this).offset().top)+30)+"px").css("left",$(this).offset().left).show();
        	}else if(music_id==music_id_now){
        		$("#hbzj").hide();
        	}
        	music_id_now = music_id;
        });
        $("a[id^='addMySheet_']").live("click",function(){
        	var mysheet_id = $(this).attr("id").split("_")[1];
        	$.post('page_addMycollect.action',{'music_id':music_id_now,'mysheet_id':mysheet_id,'user_id':'${userFront.user_id}'},
       			function(responseObj) {
       					if(responseObj.success) {	
       						 alert('收藏成功！');
       					}else  if(responseObj.err.msg){
       						 alert('收藏异常：'+responseObj.err.msg);
       					}else{
       						 alert('收藏异常：服务器异常！');
       					}	
       					$("#hbzj").hide();
       		 },'json');
        });
    })(); 
    
	$("#loginOutBtn").bind('click',function(){
		$.post('LoginOutSystem.action',null,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('退出成功！');
						 window.location.href="login.jsp";
					}else  if(responseObj.err.msg){
						 alert('退出异常：'+responseObj.err.msg);
					}else{
						 alert('退出异常：服务器异常！');
					}	
		 },'json');
	});
</script>
<div id="hbzj">
<div style="width:100;height:100px;overflow:scroll;">
<table id="hbzj_con" width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <c:forEach items="${mysheets}" var="mysheet" varStatus="status">
   <tr class="list0"> 
     <td width="" align="center">
     	<a href="javascript:void(0)" id="addMySheet_${mysheet.mysheet_id}" style="font-size:11px">${mysheet.mysheet_name}</a>
     </td>
   </tr> 
  </c:forEach>
</table>
</div>
</div>
</body>
</html>
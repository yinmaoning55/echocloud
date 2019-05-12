package com.example.echocloud.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.echocloud.domain.*;
import com.example.echocloud.service.IndexManager;
import com.example.echocloud.service.LoginIndexManager;
import com.example.echocloud.util.PaperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.soft.common.util.JSONData;

@Controller
public class IndexAction{

	@Autowired
	LoginIndexManager loginIndexManager;

	@Autowired
	IndexManager indexManager;
	public IndexManager getIndexManager() {
		return indexManager;
	}
	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}

	/**
	 * @Title: listMusics
	 * @Description: 查询全部歌曲
	 * @return String
	 */
	@RequestMapping(value="page_listMusics.action")
	public String listMusics(Music paramsMusic, PaperUtil paperUtil,
							 ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		try {
			if (paramsMusic==null) {
				paramsMusic = new Music();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsMusic);
			//总的条数
			int[] sum={0};
			//查询全部歌曲列表
			List<Music> musics = indexManager.listMusics(paramsMusic,sum); 
			model.addAttribute("musics", musics);
			model.addAttribute("paramsMusic", paramsMusic);
			paperUtil.setTotalCount(sum[0]);
			
			//查询用户歌单
			initUserMenu(model, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "music";
	}
	@RequestMapping(value="index2.action")
	public String showindex2(){


		return "index2";
	}
	
	/**
	 * @Title: addMypraise
	 * @Description: 新增歌曲点赞
	 * @return String
	 */
	@RequestMapping(value="page_addMypraise.action")
	@ResponseBody
	public JSONData addMypraise(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存歌曲点赞
			Users userFront = (Users)httpSession.getAttribute("userFront");
			int flag = indexManager.addMypraise(paramsMusic,userFront.getUser_id());
			if (flag==1) {
				jsonData.setErrorReason("您已经红心点过赞了！");
				return jsonData;
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFront.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFront(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息

			indexManager.updateUser(paramsUser);
			//更新session
			Users admin = new Users();
			admin.setUser_id(paramsUser.getUser_id());
			admin = indexManager.queryUser(admin);
			httpSession.setAttribute("userFront", admin);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFrontPass.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFrontPass(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			Users users=(Users)httpSession.getAttribute("userFront");
			String name=users.getUser_name();
			users.setUser_name(name);
			users.setUser_type(1);
			users.setUser_pass(paramsUser.getUser_opass());
			Users admin = loginIndexManager.getUser(users);
			System.out.println("params:"+users);
			System.out.println("admin:"+admin);

			if (admin!=null) {

			}else {
				jsonData.setErrorReason("旧密码错误");
				return jsonData;
			}
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			Users userFront = (Users)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				userFront.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("userFront", userFront);
				jsonData.setErrorReason("修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyMyplays
	 * @Description: 查询播放记录信息
	 * @return String
	 */
	@RequestMapping(value="page_listMyplays.action")
	public String listMyplays(Myplay paramsMyplay, PaperUtil paperUtil,
							  ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		try {
			if (paramsMyplay==null) {
				paramsMyplay = new Myplay();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsMyplay);
			int[] sum={0};
			//用户身份限制
			Users userFront = (Users)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				paramsMyplay.setUser_id(userFront.getUser_id());
			}
			List<Myplay> myplays = indexManager.listMyplays(paramsMyplay,sum); 
			model.addAttribute("myplays", myplays);
			model.addAttribute("paramsMyplay", paramsMyplay);
			paperUtil.setTotalCount(sum[0]);

			//查询用户歌单
			initUserMenu(model, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myplayShow";
	}
	
	/**
	 * @Title: addMyplayShow
	 * @Description: 新增播放记录界面
	 * @return String
	 */
	@RequestMapping(value="page_addMyplayShow.action")
	public String addMyplayShow(){
		return "myplayEdit";
	}
	
	/**
	 * @Title: addMyplay
	 * @Description: 新增播放记录
	 * @return String
	 */
	@RequestMapping(value="page_addMyplay.action")
	@ResponseBody
	public JSONData addMyplay(Myplay paramsMyplay,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存播放记录
			indexManager.addMyplay(paramsMyplay);

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	
	/**
	 * @Title: delMyplays
	 * @Description: 删除播放记录
	 * @return String
	 */
	@RequestMapping(value="page_delMyplays.action")
	@ResponseBody
	public JSONData delMyplays(Myplay paramsMyplay){
		JSONData jsonData = new JSONData();
		try {
			 //删除播放记录
			indexManager.delMyplays(paramsMyplay);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyMydowns
	 * @Description: 查询下载记录信息
	 * @return String
	 */
	@RequestMapping(value="page_listMydowns.action")
	public String listMydowns(Mydown paramsMydown, PaperUtil paperUtil,
							  ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		try {
			if (paramsMydown==null) {
				paramsMydown = new Mydown();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsMydown);
			int[] sum={0};
			//用户身份限制
			Users userFront = (Users)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				paramsMydown.setUser_id(userFront.getUser_id());
			}
			List<Mydown> mydowns = indexManager.listMydowns(paramsMydown,sum); 
			model.addAttribute("mydowns", mydowns);
			model.addAttribute("paramsMydown", paramsMydown);
			paperUtil.setTotalCount(sum[0]);

			//查询用户歌单
			initUserMenu(model, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "mydownShow";
	}
	
	/**
	 * @Title: addMydownShow
	 * @Description: 新增下载记录界面
	 * @return String
	 */
	@RequestMapping(value="page_addMydownShow.action")
	public String addMydownShow(){
		return "mydownEdit";
	}
	
	/**
	 * @Title: addMydown
	 * @Description: 新增下载记录
	 * @return String
	 */
	@RequestMapping(value="page_addMydown.action")
	@ResponseBody
	public JSONData addMydown(Mydown paramsMydown,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存下载记录
			indexManager.addMydown(paramsMydown);

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	
	/**
	 * @Title: delMydowns
	 * @Description: 删除下载记录
	 * @return String
	 */
	@RequestMapping(value="page_delMydowns.action")
	@ResponseBody
	public JSONData delMydowns(Mydown paramsMydown){
		JSONData jsonData = new JSONData();
		try {
			 //删除下载记录
			indexManager.delMydowns(paramsMydown);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMycollects
	 * @Description: 查询收藏记录信息
	 * @return String
	 */
	@RequestMapping(value="page_listMycollects.action")
	public String listMycollects(Mycollect paramsMycollect, PaperUtil paperUtil,
								 ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		try {
			if (paramsMycollect==null) {
				paramsMycollect = new Mycollect();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsMycollect);
			int[] sum={0};
			//用户身份限制
			Users userFront = (Users)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				paramsMycollect.setUser_id(userFront.getUser_id());
			}
			List<Mycollect> mycollects = indexManager.listMycollects(paramsMycollect,sum); 
			model.addAttribute("mycollects", mycollects);
			model.addAttribute("paramsMycollect", paramsMycollect);
			paperUtil.setTotalCount(sum[0]);

			//查询用户歌单
			initUserMenu(model, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "mycollectShow";
	}
	
	/**
	 * @Title: addMycollectShow
	 * @Description: 新增收藏记录界面
	 * @return String
	 */
	@RequestMapping(value="page_addMycollectShow.action")
	public String addMycollectShow(){
		return "mycollectEdit";
	}
	
	/**
	 * @Title: addMycollect
	 * @Description: 新增收藏记录
	 * @return String
	 */
	@RequestMapping(value="page_addMycollect.action")
	@ResponseBody
	public JSONData addMycollect(Mycollect paramsMycollect,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存收藏记录
			indexManager.addMycollect(paramsMycollect);

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	
	/**
	 * @Title: delMycollects
	 * @Description: 删除收藏记录
	 * @return String
	 */
	@RequestMapping(value="page_delMycollects.action")
	@ResponseBody
	public JSONData delMycollects(Mycollect paramsMycollect){
		JSONData jsonData = new JSONData();
		try {
			 //删除收藏记录
			indexManager.delMycollects(paramsMycollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMysheets
	 * @Description: 查询音乐歌单信息
	 * @return String
	 */
	@RequestMapping(value="page_listMysheets.action")
	public String listMysheets(Mysheet paramsMysheet,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsMysheet==null) {
				paramsMysheet = new Mysheet();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsMysheet);
			int[] sum={0};
			//用户身份限制
			Users userFront = (Users)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				paramsMysheet.setUser_id(userFront.getUser_id());
			}
			List<Mysheet> mysheets = indexManager.listMysheets(paramsMysheet,sum); 
			model.addAttribute("mysheets", mysheets);
			model.addAttribute("paramsMysheet", paramsMysheet);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "mysheetShow";
	}
	
	/**
	 * @Title: addMysheetShow
	 * @Description: 新增音乐歌单界面
	 * @return String
	 */
	@RequestMapping(value="page_addMysheetShow.action")
	public String addMysheetShow(){
		return "mysheetEdit";
	}
	
	/**
	 * @Title: addMysheet
	 * @Description: 新增音乐歌单
	 * @return String
	 */
	@RequestMapping(value="page_addMysheet.action")
	@ResponseBody
	public JSONData addMysheet(Mysheet paramsMysheet,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存音乐歌单
			indexManager.addMysheet(paramsMysheet);

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: editMysheet
	 * @Description: 编辑音乐歌单信息
	 * @return String
	 */
	@RequestMapping(value="page_editMysheet.action")
	public String editMysheet(Mysheet paramsMysheet,
			ModelMap model){
		try {
			//查询音乐歌单
			Mysheet mysheet = indexManager.queryMysheet(paramsMysheet);
			model.addAttribute("mysheet", mysheet);
			
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "mysheetEdit";
	}
	
	/**
	 * @Title: saveMysheet
	 * @Description: 保存音乐歌单信息
	 * @return String
	 */
	@RequestMapping(value="page_saveMysheet.action")
	@ResponseBody
	public JSONData saveMysheet(Mysheet paramsMysheet){
		JSONData jsonData = new JSONData();
		try {
			//保存音乐歌单信息
			indexManager.updateMysheet(paramsMysheet);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: delMysheets
	 * @Description: 删除音乐歌单
	 * @return String
	 */
	@RequestMapping(value="page_delMysheets.action")
	@ResponseBody
	public JSONData delMysheets(Mysheet paramsMysheet){
		JSONData jsonData = new JSONData();
		try {
			 //删除音乐歌单
			indexManager.delMysheets(paramsMysheet);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * 初始化用户歌单
	 * @Title: initUserMenu
	 * @Description: TODO
	 * @param model
	 * @param httpSession
	 * @return void
	 */
	private void initUserMenu(ModelMap model,HttpSession httpSession){
		Users userFront = (Users)httpSession.getAttribute("userFront");
		if (userFront!=null) {
			//查询用户歌单
			Mysheet mysheet = new Mysheet();
			mysheet.setStart(-1);
			mysheet.setUser_id(userFront.getUser_id());
			List<Mysheet> mysheets = indexManager.listMysheets(mysheet,null);
			if (mysheets==null) {
				mysheets = new ArrayList<Mysheet>();
			}
			model.addAttribute("mysheets", mysheets);
		}
	}
}

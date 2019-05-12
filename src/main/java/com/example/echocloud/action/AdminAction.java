package com.example.echocloud.action;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.echocloud.domain.Music;
import com.example.echocloud.domain.Users;
import com.example.echocloud.service.AdminManager;
import com.example.echocloud.util.PaperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.soft.common.util.DateUtil;

@Controller
public class AdminAction{

	@Autowired
	AdminManager adminManager;
	public AdminManager getAdminManager() {
		return adminManager;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}

	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdmin.action",method=RequestMethod.POST)
	public String saveAdmin(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话
			if (!validateAdmin(httpSession,request)) {
				return "admin/loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			Users admin = new Users();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			httpSession.setAttribute("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp", model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑异常", "modifyInfo.jsp", model);
		}
		return "admin/infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdminPass.action",method=RequestMethod.POST)
	public String saveAdminPass(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话
			if (!validateAdmin(httpSession,request)) {
				return "admin/loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			Users admin = (Users)httpSession.getAttribute("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp", model);
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp", model);
		}
		return "admin/infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers.action")
	public String listUsers(Users paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new Users();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//查看注册用户信息
			paramsUser.setUser_type(1);
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询注册用户列表
			List<Users> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询注册用户异常", "main.jsp", model);
			return "admin/infoTip";
		}
		
		return "admin/userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加注册用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUserShow.action",method=RequestMethod.GET)
	public String addUserShow(ModelMap model){
		return "admin/userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUser.action",method=RequestMethod.POST)
	public String addUser(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查注册用户是否存在
			Users user = new Users();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", paramsUser);
				return "admin/userEdit";
			}
			 //添加注册用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("添加注册用户异常", "Admin_listUsers.action", model);
		}
		
		return "admin/infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editUser.action",method=RequestMethod.GET)
	public String editUser(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到注册用户
			Users user = adminManager.queryUser(paramsUser);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询注册用户异常", "Admin_listUsers.action", model);
			return "admin/infoTip";
		}
		
		return "admin/userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveUser.action",method=RequestMethod.POST)
	public String saveUser(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑注册用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("编辑注册用户异常", "Admin_listUsers.action", model);
			return "admin/infoTip";
		}
		
		return "admin/infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delUsers.action")
	public String delUsers(Users paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除注册用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除注册用户成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("删除注册用户异常", "Admin_listUsers.action", model);
		}
		
		return "admin/infoTip";
	}
	
	/**
	 * @Title: listMusics
	 * @Description: 查询歌曲
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listMusics.action")
	public String listMusics(Music paramsMusic, PaperUtil paperUtil,
							 ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		try {
			//验证用户会话
			if (!validateAdmin(httpSession,request)) {
				return "admin/loginTip";
			}
			
			if (paramsMusic==null) {
				paramsMusic = new Music();
			}
			
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsMusic);
			//总的条数
			int[] sum={0};
			//查询歌曲列表
			List<Music> musics = adminManager.listMusics(paramsMusic,sum); 
			
			model.addAttribute("musics", musics);
			model.addAttribute("paramsMusic", paramsMusic);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询歌曲异常", "Admin_listMusics.action", model);
			return "admin/infoTip";
		}
		
		return "admin/musicShow";
	}
	
	/**
	 * @Title: queryMusic
	 * @Description: 查询歌曲详情页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryMusic.action",method=RequestMethod.GET)
	public String queryMusic(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpSession httpSession){
		try {
			//验证用户会话
			if (!validateAdmin(httpSession,request)) {
				return "admin/loginTip";
			}
			//查询歌曲详情
			Music music = adminManager.queryMusic(paramsMusic); 
			model.addAttribute("music", music);
			
		} catch (Exception e) {
			setErrorTip("查询歌曲详情异常", "main.jsp", model);
			return "infoTip";
		}
		return "admin/musicDetail";
	}
	
	/**
	 * @Title: addMusicShow
	 * @Description: 显示添加歌曲页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addMusicShow.action",method=RequestMethod.GET)
	public String addMusicShow(HttpServletRequest request,HttpSession httpSession){
		//验证用户会话
		if (!validateAdmin(httpSession,request)) {
			return "admin/loginTip";
		}
		return "admin/musicEdit";
	}
	
	/**
	 * @Title: addMusic
	 * @Description: 添加歌曲
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addMusic.action",method=RequestMethod.POST)
	public String addMusic(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加歌曲
			paramsMusic.setMusic_name2((String)httpSession.getAttribute("music_name2"));
			paramsMusic.setPic_name((String)httpSession.getAttribute("pic_name"));
			paramsMusic.setLrc_name((String)httpSession.getAttribute("lrc_name"));
			System.out.println("添加歌曲："+paramsMusic.toString());
			adminManager.addMusic(paramsMusic);
			
			setSuccessTip("添加歌曲成功", "Admin_listMusics.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("添加歌曲异常", "Admin_listMusics.action", model);
		}
		
		return "admin/infoTip";
	}
	
	/**
	 * @Title: editMusic
	 * @Description: 编辑注册歌曲
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editMusic.action",method=RequestMethod.GET)
	public String editMusic(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到注册歌曲
			Music music = adminManager.queryMusic(paramsMusic);
			model.addAttribute("music", music);
			
		} catch (Exception e) {
			setErrorTip("查询注册歌曲异常", "Admin_listMusic.action", model);
			return "admin/infoTip";
		}
		
		return "admin/musicEdit";
	}
	
	/**
	 * @Title: saveMusic
	 * @Description: 编辑歌曲
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveMusic.action")
	public String saveMusic(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑歌曲
			adminManager.updateMusic(paramsMusic);
			
			setSuccessTip("操作成功", "Admin_listMusics.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("操作失败", "Admin_listMusics.action",model);
		}
		
		return "admin/infoTip";
	}
	 
	/**
	 * @Title: delMusics
	 * @Description: 删除歌曲
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delMusics.action")
	public String delMusics(Music paramsMusic,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除歌曲
			adminManager.delMusics(paramsMusic);
			
			setSuccessTip("删除歌曲成功", "Admin_listMusics.action",model);
		} catch (Exception e) {
			setErrorTip("删除歌曲异常", "Admin_listMusics.action", model);
		}
		
		return "admin/infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(HttpSession httpSession,HttpServletRequest request){
		Users admin = (Users)httpSession.getAttribute("admin");
		if (admin==null) {
			return false;
		}
		//防盗链
		try {
			String addressReferer=request.getHeader("referer"); 
			String pathReferer="";        //定义空字符串
			if(addressReferer!=null){        //判断当前的页面的请求地址为空时
				URL urlReferer=new URL(addressReferer);//实例化URL方法
				pathReferer=urlReferer.getHost();  //获取请求页面的服务器主机
			}
			String addressHost=request.getRequestURL().toString();  //获取当前页面的地址
			String pathHost="";
			if(addressHost!=null){
				URL urlHost=new URL(addressHost);
				pathHost=urlHost.getHost();        //获取当前服务器的主机
			}
			if(addressReferer!=null && !pathReferer.equals(pathHost)){  //判断当前页面的主机与服务器的主机是否相同    
				return false;
			}
		} catch (Exception e) {
			return true;
		}
		return true;
	}
	
	
	private void setErrorTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "error");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "success");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}

}

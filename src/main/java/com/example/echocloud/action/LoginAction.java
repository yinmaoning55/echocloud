package com.example.echocloud.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.echocloud.domain.Users;
import com.example.echocloud.service.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.soft.common.util.DateUtil;
import com.soft.common.util.JSONData;

@Controller
public class LoginAction {

	@Autowired
	LoginManager loginManager;
	public LoginManager getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	/**
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	@RequestMapping(value="admin/LoginInSystem.action",method=RequestMethod.POST)
	public String InSystem(Users params,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){

		try {
			String random = (String)httpSession.getAttribute("random");
			if (!random.equals(params.getRandom())) {
				model.addAttribute("tip","验证码错误");
				model.addAttribute("params",params);
				return "admin/login";
			}
			
			//用户登录查询
			//params.setUser_type(4);
			Users admin = loginManager.getUser(params);
			if (admin!=null) {
				httpSession.setAttribute("admin", admin);
			}else {
				model.addAttribute("tip","用户名或密码错误");
				model.addAttribute("params",params);
				return "admin/login";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip","登录异常，请稍后重试");
			model.addAttribute("params",params);
			return "admin/login";
		}
		
		return "admin/index";
	}
	
	/**
	 * @Title: OutSystem
	 * @Description: 退出登录
	 * @return String
	 */
	@RequestMapping(value="admin/LoginOutSystem.action",method=RequestMethod.GET)
	public String OutSystem(HttpSession httpSession){
		try {
			//用户查询
			Users user = (Users)httpSession.getAttribute("admin");
			if (user!=null) {
				//退出登录
				httpSession.removeAttribute("admin");
				httpSession.invalidate();
			}
			
		} catch (Exception e) {
			return "admin/login";
		}
		
		return "admin/login";
	}
	
	/**
	 * @Title: RegSystem
	 * @Description: 用户注册
	 * @return String
	 */
	@RequestMapping(value="admin/LoginRegSystem.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData RegSystem(Users params,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//验证码验证
			String random = (String)httpSession.getAttribute("random");
			if (!random.equals(params.getRandom())) {
				jsonData.setErrorReason("验证码错误");
				return jsonData;
			}
			
			//查询用户名是否被占用
			Users user = new Users();
			user.setUser_name(params.getUser_name());
			Users user_temp = loginManager.getUser(user);
			if (user_temp!=null) {
				jsonData.setErrorReason("注册失败，用户名已被注册："+params.getUser_name());
				return jsonData;
			}

			//添加用户入库
			params.setUser_type(1);
			params.setReg_date(DateUtil.getCurDateTime());
			loginManager.addUser(params);
			
		} catch (Exception e) {
			jsonData.setErrorReason("注册异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
}

package com.example.echocloud.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.echocloud.domain.Users;
import com.example.echocloud.service.IndexManager;
import com.example.echocloud.service.LoginIndexManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.soft.common.util.DateUtil;
import com.soft.common.util.JSONData;
import com.soft.common.util.UID;

@Controller
public class LoginIndexAction {

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

	public LoginIndexManager getLoginIndexManager() {
		return loginIndexManager;
	}
	public void setLoginIndexManager(LoginIndexManager loginIndexManager) {
		this.loginIndexManager = loginIndexManager;
	}
	/**
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	@RequestMapping(value="LoginInSystem.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData InSystem(Users params,
							 ModelMap model, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		System.out.println(params.getUser_name());
		try {
			String random = (String)httpSession.getAttribute("random");
			System.out.println(random);
			System.out.println(params.getRandom());
			if (!random.equals(params.getRandom())) {
				jsonData.setErrorReason("验证码错误");
				return jsonData;
			}
			
			//用户登录查询
			params.setUser_type(1);
			Users admin = loginIndexManager.getUser(params);
			if (admin!=null) {
				httpSession.setAttribute("userFront", admin);

			}else {
				jsonData.setErrorReason("用户名或密码错误");
				return jsonData;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("登录异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: OutSystem
	 * @Description: 退出登录
	 * @return String
	 */
	@RequestMapping(value="loginindex2")
	@ResponseBody
	public JSONData OutSystem(HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//用户查询
			Users user = (Users)httpSession.getAttribute("userFront");
			if (user!=null) {
				//退出登录
				httpSession.removeAttribute("userFront");
				httpSession.invalidate();
			}
			
		} catch (Exception e) {
			jsonData.setErrorReason("退出异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: RegSystem
	 * @Description: 用户注册
	 * @return String
	 */
	@RequestMapping(value="LoginRegSystem.action",method=RequestMethod.POST)
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
			Users user_temp = loginIndexManager.getUser(user);
			if (user_temp!=null) {
				jsonData.setErrorReason("注册失败，用户名已被注册："+params.getUser_name());
				return jsonData;
			}
			
			//添加用户入库
			params.setUser_type(1);
			params.setReg_date(DateUtil.getCurDateTime());
			params.setUser_id(UID.getInstanse().getUID());
			loginIndexManager.addUser(params);
			
		} catch (Exception e) {
			jsonData.setErrorReason("注册异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
}

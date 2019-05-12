package com.example.echocloud.service;

import com.example.echocloud.domain.Users;
import com.example.echocloud.mapper.IUsersDao;
import com.example.echocloud.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.common.util.StringUtil;

@Service
public class LoginIndexManager {
	
	@Autowired
	IUsersDao usersDao;

	public IUsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public Users getUser(Users user){
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		Users _user = usersDao.getUsers(user);

		return _user;
	}
	
	/**
	 * @Title: addUser
	 * @Description: 用户注册
	 * @return void
	 */
	public void addUser(Users user) {
		//密码加密
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		usersDao.addUsers(user);
		
	}  
}

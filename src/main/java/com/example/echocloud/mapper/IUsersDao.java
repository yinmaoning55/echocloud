package com.example.echocloud.mapper;

import com.example.echocloud.domain.Users;

import java.util.List;



public interface IUsersDao {

	 int addUsers(Users users);

	 int delUsers(String user_id);

	 int delUserss(String[] user_ids);

	 int updateUsers(Users users);

	 Users getUsers(Users users);

	 List<Users>  listUserss(Users users);

	 int listUserssCount(Users users);

}

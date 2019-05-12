package com.example.echocloud.mapper;

import java.util.List;

import com.example.echocloud.domain.Mydown;


public interface IMydownDao {

	 int addMydown(Mydown mydown);

	 int delMydown(String mydown_id);

	 int delMydowns(String[] mydown_ids);

	 int updateMydown(Mydown mydown);

	 Mydown getMydown(Mydown mydown);

	 List<Mydown>  listMydowns(Mydown mydown);

	 int listMydownsCount(Mydown mydown);


}

package com.example.echocloud.mapper;

import com.example.echocloud.domain.Myplay;

import java.util.List;


public interface IMyplayDao {

	 int addMyplay(Myplay myplay);

	 int delMyplay(String myplay_id);

	 int delMyplays(String[] myplay_ids);

	 int updateMyplay(Myplay myplay);

	 Myplay getMyplay(Myplay myplay);

	 List<Myplay>  listMyplays(Myplay myplay);

	 int listMyplaysCount(Myplay myplay);

}

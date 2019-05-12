package com.example.echocloud.mapper;

import com.example.echocloud.domain.Mycollect;

import java.util.List;


public interface IMycollectDao {

	 int addMycollect(Mycollect mycollect);

	 int delMycollect(String mycollect_id);

	 int delMycollects(String[] mycollect_ids);

	 int updateMycollect(Mycollect mycollect);

	 Mycollect getMycollect(Mycollect mycollect);

	 List<Mycollect>  listMycollects(Mycollect mycollect);

	 int listMycollectsCount(Mycollect mycollect);

}

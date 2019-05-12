package com.example.echocloud.mapper;


import com.example.echocloud.domain.Mysheet;

import java.util.List;

public interface IMysheetDao {

	 int addMysheet(Mysheet mysheet);

	 int delMysheet(String mysheet_id);

	 int delMysheets(String[] mysheet_ids);

	 int updateMysheet(Mysheet mysheet);

	 Mysheet getMysheet(Mysheet mysheet);

	 List<Mysheet> listMysheets(Mysheet mysheet);

	 int listMysheetsCount(Mysheet mysheet);

}

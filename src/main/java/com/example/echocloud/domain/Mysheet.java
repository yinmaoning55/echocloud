package com.example.echocloud.domain;

import com.soft.common.domain.BaseDomain;

public class Mysheet extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6665176154334829067L;
	private String rn;
	private String mysheet_id; // null
	private String user_id; // null
	private String mysheet_name; // null

	private String ids;
	private String random;

	@Override
	public String toString() {
		return "Mysheet{" +
				"rn='" + rn + '\'' +
				", mysheet_id='" + mysheet_id + '\'' +
				", user_id='" + user_id + '\'' +
				", mysheet_name='" + mysheet_name + '\'' +
				", ids='" + ids + '\'' +
				", random='" + random + '\'' +
				'}';
	}

	public void setMysheet_id(String mysheet_id){
		this.mysheet_id=mysheet_id;
	}

	public String getMysheet_id(){
		return mysheet_id;
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	public String getUser_id(){
		return user_id;
	}

	public void setMysheet_name(String mysheet_name){
		this.mysheet_name=mysheet_name;
	}

	public String getMysheet_name(){
		return mysheet_name;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getRn() {
		return rn;
	}

}

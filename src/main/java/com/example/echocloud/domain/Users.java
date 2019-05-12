package com.example.echocloud.domain;

import com.soft.common.domain.BaseDomain;

public class Users extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8332782463163160102L;
	private String rn;
	private String user_id; // null
	private String user_name; // null
	private String user_pass; // null
	private String user_opass;

	public String getUser_opass() {
		return user_opass;
	}

	private String real_name; // null
	private int user_sex; // 1：男 0：女
	private String nick_name; // null
	private String user_mail; // null
	private String user_phone; // null
	private String reg_date; // null
	private String reg_date_show;
	private int user_type; // 1:注册用户 2:管理员

	private String user_types; 
	private String ids;
	private String random;

	public Users() {
	}

	public String getUser_typeDesc(){
		switch (user_type) {
		case 1:
			return "注册用户";
		case 2:
			return "管理员";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "";
		}
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	@Override
	public String toString() {
		return "Users{" +
				"rn='" + rn + '\'' +
				", user_id='" + user_id + '\'' +
				", user_name='" + user_name + '\'' +
				", user_pass='" + user_pass + '\'' +
				", user_opass='" + user_opass + '\'' +
				", real_name='" + real_name + '\'' +
				", user_sex=" + user_sex +
				", nick_name='" + nick_name + '\'' +
				", user_mail='" + user_mail + '\'' +
				", user_phone='" + user_phone + '\'' +
				", reg_date='" + reg_date + '\'' +
				", reg_date_show='" + reg_date_show + '\'' +
				", user_type=" + user_type +
				", user_types='" + user_types + '\'' +
				", ids='" + ids + '\'' +
				", random='" + random + '\'' +
				'}';
	}

	public String getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_pass(String user_pass){
		this.user_pass=user_pass;
	}

	public String getUser_pass(){
		return user_pass;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setUser_sex(int user_sex){
		this.user_sex=user_sex;
	}

	public int getUser_sex(){
		return user_sex;
	}

	public void setUser_mail(String user_mail){
		this.user_mail=user_mail;
	}

	public String getUser_mail(){
		return user_mail;
	}

	public void setUser_phone(String user_phone){
		this.user_phone=user_phone;
	}

	public String getUser_phone(){
		return user_phone;
	}

	public void setReg_date(String reg_date){
		this.reg_date=reg_date;
	}

	public String getReg_date(){
		return reg_date;
	}

	public void setReg_date_show(String reg_date_show){
		this.reg_date_show =reg_date_show;
	}

	public String getReg_date_show(){
		return reg_date_show;
	}

	public void setUser_type(int user_type){
		this.user_type=user_type;
	}

	public int getUser_type(){
		return user_type;
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

	public String getUser_types() {
		return user_types;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public void setUser_opass(String user_opass) {
		this.user_opass = user_opass;
	}
}

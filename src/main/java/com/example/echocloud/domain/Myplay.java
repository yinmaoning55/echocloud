package com.example.echocloud.domain;

import com.soft.common.domain.BaseDomain;

public class Myplay extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8130901661075994885L;
	private String rn;
	private String myplay_id; // null
	private String user_id; // null
	private String music_id; // null
	private String myplay_date; // null

	private String music_name; // null
	private String music_name2; // null
	private String pic_name; // null
	private String lrc_name; // null
	private String music_author; // null
	private String music_album; // null
	private int music_click; // null
	private int music_collect; // null
	private String all_name;
	private String ids;
	private String random;

	public void setMyplay_id(String myplay_id){
		this.myplay_id=myplay_id;
	}

	public String getMyplay_id(){
		return myplay_id;
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	public String getUser_id(){
		return user_id;
	}

	public void setMusic_id(String music_id){
		this.music_id=music_id;
	}

	public String getMusic_id(){
		return music_id;
	}

	public void setMyplay_date(String myplay_date){
		this.myplay_date=myplay_date;
	}

	public String getMyplay_date(){
		return myplay_date;
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

	public String getMusic_name() {
		return music_name;
	}

	public String getMusic_name2() {
		return music_name2;
	}

	public String getPic_name() {
		return pic_name;
	}

	public String getLrc_name() {
		return lrc_name;
	}

	public String getMusic_author() {
		return music_author;
	}

	public String getMusic_album() {
		return music_album;
	}

	public int getMusic_click() {
		return music_click;
	}

	public int getMusic_collect() {
		return music_collect;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public void setMusic_name2(String music_name2) {
		this.music_name2 = music_name2;
	}

	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}

	public void setLrc_name(String lrc_name) {
		this.lrc_name = lrc_name;
	}

	public void setMusic_author(String music_author) {
		this.music_author = music_author;
	}

	public void setMusic_album(String music_album) {
		this.music_album = music_album;
	}

	public void setMusic_click(int music_click) {
		this.music_click = music_click;
	}

	public void setMusic_collect(int music_collect) {
		this.music_collect = music_collect;
	}

	public String getAll_name() {
		return all_name;
	}

	public void setAll_name(String all_name) {
		this.all_name = all_name;
	}

}

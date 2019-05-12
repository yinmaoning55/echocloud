package com.example.echocloud.domain;

import com.soft.common.domain.BaseDomain;

public class Music extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5501254916591147274L;
	private String rn;
	private String music_id; // null
	private String music_name; // null
	private String music_name2; // null
	private String pic_name; // null
	private String lrc_name; // null
	private String music_author; // null
	private String music_album; // null
	private int music_collect; // null
	private String music_date; // null
	private String music_desc; // null

	private String all_name;
	private String ids;
	private String random;

	@Override
	public String toString() {
		return "Music{" +
				"rn='" + rn + '\'' +
				", music_id='" + music_id + '\'' +
				", music_name='" + music_name + '\'' +
				", music_name2='" + music_name2 + '\'' +
				", pic_name='" + pic_name + '\'' +
				", lrc_name='" + lrc_name + '\'' +
				", music_author='" + music_author + '\'' +
				", music_album='" + music_album + '\'' +
				", music_collect=" + music_collect +
				", music_date='" + music_date + '\'' +
				", music_desc='" + music_desc + '\'' +
				", all_name='" + all_name + '\'' +
				", ids='" + ids + '\'' +
				", random='" + random + '\'' +
				'}';
	}

	public void setMusic_id(String music_id){
		this.music_id=music_id;
	}

	public String getMusic_id(){
		return music_id;
	}

	public void setMusic_name(String music_name){
		this.music_name=music_name;
	}

	public String getMusic_name(){
		return music_name;
	}

	public void setMusic_name2(String music_name2){
		this.music_name2=music_name2;
	}

	public String getMusic_name2(){
		return music_name2;
	}

	public void setPic_name(String pic_name){
		this.pic_name=pic_name;
	}

	public String getPic_name(){
		return pic_name;
	}

	public void setLrc_name(String lrc_name){
		this.lrc_name=lrc_name;
	}

	public String getLrc_name(){
		return lrc_name;
	}

	public void setMusic_author(String music_author){
		this.music_author=music_author;
	}

	public String getMusic_author(){
		return music_author;
	}

	public void setMusic_album(String music_album){
		this.music_album=music_album;
	}

	public String getMusic_album(){
		return music_album;
	}

	public void setMusic_collect(int music_collect){
		this.music_collect=music_collect;
	}

	public int getMusic_collect(){
		return music_collect;
	}

	public void setMusic_date(String music_date){
		this.music_date=music_date;
	}

	public String getMusic_date(){
		return music_date;
	}

	public void setMusic_desc(String music_desc){
		this.music_desc=music_desc;
	}

	public String getMusic_desc(){
		return music_desc;
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

	public String getAll_name() {
		return all_name;
	}

	public void setAll_name(String all_name) {
		this.all_name = all_name;
	}

}

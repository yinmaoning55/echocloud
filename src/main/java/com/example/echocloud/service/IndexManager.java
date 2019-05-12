package com.example.echocloud.service;

import java.util.HashMap;
import java.util.List;

import com.example.echocloud.domain.*;
import com.example.echocloud.mapper.*;
import com.example.echocloud.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.common.util.UID;

@Service
public class IndexManager {
	
	//所有待注入Dao类
	@Autowired
	IUsersDao usersDao;
	@Autowired
	IMusicDao musicDao;
	@Autowired
	IMysheetDao mysheetDao;
	@Autowired
	IMycollectDao mycollectDao;
	@Autowired
	IMyplayDao myplayDao;
	@Autowired
	IMydownDao mydownDao; 

	public static HashMap<String, String> praiseMap = new HashMap<String, String>();  
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<User>
	 */
	public List<Users> listUsers(Users user, int[] sum) {
		if (sum != null) {
			sum[0] = usersDao.listUserssCount(user);
		}
		List<Users> users = usersDao.listUserss(user);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户单个查询
	 * @param user
	 * @return User
	 */
	public Users queryUser(Users user) {
		Users _user = usersDao.getUsers(user);
		return _user;
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(Users user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		usersDao.updateUsers(user);
	}

	/**
	 * @Title: listMusics
	 * @Description: 音乐查询
	 * @param music
	 * @return List<Music>
	 */
	public List<Music> listMusics(Music music, int[] sum) {
		if (sum != null) {
			sum[0] = musicDao.listMusicsCount(music);
		}
		List<Music> musics = musicDao.listMusics(music);

		return musics;
	}

	/**
	 * @Title: queryMusic
	 * @Description: 音乐查询
	 * @param music
	 * @return Music
	 */
	public Music queryMusic(Music music) {
		Music _music = musicDao.getMusic(music);
		return _music;
	}
	
	
	/**
	 * @Title: listMysheets
	 * @Description: 查询歌单信息
	 * @param mysheet
	 * @return List<Mysheet>
	 */
	public List<Mysheet>  listMysheets(Mysheet mysheet, int[] sum){
		if (sum!=null) {
			sum[0] = mysheetDao.listMysheetsCount(mysheet);
		}
		List<Mysheet> mysheets = mysheetDao.listMysheets(mysheet);
		return mysheets;
	}
	
	/**
	 * @Title: queryMysheet
	 * @Description: 查询歌单
	 * @param mysheet
	 * @return Mysheet
	 */
	public Mysheet queryMysheet(Mysheet mysheet) {
		Mysheet _mysheet = mysheetDao.getMysheet(mysheet);
		return _mysheet;
	}
	
	/**
	 * @Title: addMysheet
	 * @Description: 添加歌单
	 * @param mysheet
	 * @return void
	 */
	public void addMysheet(Mysheet mysheet) {
		//发布日期
		mysheet.setMysheet_id(UID.getInstanse().getUID());
		System.out.println(mysheet.toString());
		mysheetDao.addMysheet(mysheet);
	}

	/**
	 * @Title: updateMysheet
	 * @Description: 更新歌单信息
	 * @param mysheet
	 * @return void
	 */
	public void updateMysheet(Mysheet mysheet) {
		mysheetDao.updateMysheet(mysheet);
	}
	
	/**
	 * @Title: delMysheets
	 * @Description: 删除歌单信息
	 * @param mysheet
	 * @return void
	 */
	public void  delMysheets(Mysheet mysheet){
		mysheetDao.delMysheets(mysheet.getIds().split(","));
	}
	
	/**
	 * @Title: listMyplays
	 * @Description: 歌曲播放查询
	 * @param myplay
	 * @return List<Myplay>
	 */
	public List<Myplay> listMyplays(Myplay myplay, int[] sum) {
		if (sum != null) {
			sum[0] = myplayDao.listMyplaysCount(myplay);
		}
		List<Myplay> myplays = myplayDao.listMyplays(myplay);

		return myplays;
	}

	/**
	 * @Title: queryMyplay
	 * @Description: 歌曲播放查询
	 * @param myplay
	 * @return Myplay
	 */
	public Myplay queryMyplay(Myplay myplay) {
		Myplay _myplay = myplayDao.getMyplay(myplay);
		return _myplay;
	}

	/**
	 * @Title: addMyplay
	 * @Description: 添加歌曲播放
	 * @param myplay
	 * @return void
	 */
	public void addMyplay(Myplay myplay) {
		//发布日期
		Myplay _myplay = myplayDao.getMyplay(myplay);
		if (_myplay==null) {
			myplay.setMyplay_date(DateUtil.getCurDate());
			myplay.setMyplay_id(UID.getInstanse().getUID());
			myplayDao.addMyplay(myplay);
		}
	}

	/**
	 * @Title: delMyplay
	 * @Description: 删除歌曲播放信息
	 * @param myplay
	 * @return void
	 */
	public void delMyplays(Myplay myplay) {
		myplayDao.delMyplays(myplay.getIds().split(","));
	}
	
	/**
	 * @Title: listMydowns
	 * @Description: 歌曲下载查询
	 * @param mydown
	 * @return List<Mydown>
	 */
	public List<Mydown> listMydowns(Mydown mydown, int[] sum) {
		if (sum != null) {
			sum[0] = mydownDao.listMydownsCount(mydown);
		}
		List<Mydown> mydowns = mydownDao.listMydowns(mydown);

		return mydowns;
	}

	/**
	 * @Title: queryMydown
	 * @Description: 歌曲下载查询
	 * @param mydown
	 * @return Mydown
	 */
	public Mydown queryMydown(Mydown mydown) {
		Mydown _mydown = mydownDao.getMydown(mydown);
		return _mydown;
	}

	/**
	 * @Title: addMydown
	 * @Description: 添加歌曲下载
	 * @param mydown
	 * @return void
	 */
	public void addMydown(Mydown mydown) {
		Mydown _mydown = mydownDao.getMydown(mydown);
		if (_mydown==null) {
			mydown.setMydown_date(DateUtil.getCurDate());
			mydown.setMydown_id(UID.getInstanse().getUID());
			mydownDao.addMydown(mydown);
		}
	}


	/**
	 * @Title: delMydown
	 * @Description: 删除歌曲下载信息
	 * @param mydown
	 * @return void
	 */
	public void delMydowns(Mydown mydown) {
		mydownDao.delMydowns(mydown.getIds().split(","));
	}
	
	/**
	 * @Title: listMycollects
	 * @Description: 收藏查询
	 * @param mycollect
	 * @return List<Mycollect>
	 */
	public List<Mycollect> listMycollects(Mycollect mycollect, int[] sum) {
		if (sum != null) {
			sum[0] = mycollectDao.listMycollectsCount(mycollect);
		}
		List<Mycollect> mycollects = mycollectDao.listMycollects(mycollect);

		return mycollects;
	}

	/**
	 * @Title: queryMycollect
	 * @Description: 收藏查询
	 * @param mycollect
	 * @return Mycollect
	 */
	public Mycollect queryMycollect(Mycollect mycollect) {
		Mycollect _mycollect = mycollectDao.getMycollect(mycollect);
		return _mycollect;
	}

	/**
	 * @Title: addMycollect
	 * @Description: 添加收藏
	 * @param mycollect
	 * @return void
	 */
	public void addMycollect(Mycollect mycollect) {
		Mycollect _mycollect = mycollectDao.getMycollect(mycollect);
		if (_mycollect==null) {
			mycollect.setMycollect_date(DateUtil.getCurDate());
			mycollect.setMycollect_id(UID.getInstanse().getUID());
			mycollectDao.addMycollect(mycollect);
		}
	}

	/**
	 * @Title: delMycollect
	 * @Description: 删除收藏信息
	 * @param mycollect
	 * @return void
	 */
	public void delMycollects(Mycollect mycollect) {
		if (!StringUtil.isEmptyString(mycollect.getIds())) {
			mycollectDao.delMycollects(mycollect.getIds().split(","));
		}else {
			mycollect = mycollectDao.getMycollect(mycollect);
			mycollectDao.delMycollect(mycollect.getMycollect_id());
		}
	}
	
	/**
	 * @Title: addMypraise
	 * @Description: 添加歌曲点赞
	 * @param myplay
	 * @return void
	 */
	public int addMypraise(Music music, String user_id) {
		String key = user_id+"_"+music.getMusic_id()+"_"+DateUtil.getCurDate();
		if (!praiseMap.containsKey(key)) {
			praiseMap.put(key, music.getMusic_id());
			music = musicDao.getMusic(music);
			music.setMusic_collect(music.getMusic_collect()+1);
			musicDao.updateMusic(music);
			return 0;
		}
		return 1;
	}
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public IMusicDao getMusicDao() {
		return musicDao;
	}

	public IMysheetDao getMysheetDao() {
		return mysheetDao;
	}

	public IMycollectDao getMycollectDao() {
		return mycollectDao;
	}

	public IMyplayDao getMyplayDao() {
		return myplayDao;
	}

	public IMydownDao getMydownDao() {
		return mydownDao;
	}

	public void setMusicDao(IMusicDao musicDao) {
		this.musicDao = musicDao;
	}

	public void setMysheetDao(IMysheetDao mysheetDao) {
		this.mysheetDao = mysheetDao;
	}

	public void setMycollectDao(IMycollectDao mycollectDao) {
		this.mycollectDao = mycollectDao;
	}

	public void setMyplayDao(IMyplayDao myplayDao) {
		this.myplayDao = myplayDao;
	}

	public void setMydownDao(IMydownDao mydownDao) {
		this.mydownDao = mydownDao;
	}

	
}

package com.example.echocloud.mapper;

import com.example.echocloud.domain.Music;

import java.util.List;


public interface IMusicDao {

	 int addMusic(Music music);

	 int delMusic(String music_id);

	 int delMusics(String[] music_ids);

	 int updateMusic(Music music);

	 Music getMusic(Music music);

	 List<Music>  listMusics(Music music);

	 int listMusicsCount(Music music);

}

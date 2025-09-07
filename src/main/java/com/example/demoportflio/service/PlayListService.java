package com.example.demoportflio.service;

import com.example.demoportflio.model.PlayList;

import java.util.List;

public interface PlayListService {

     List<PlayList> getAllPlayLists();
     PlayList getPlayListById(Long id);
     PlayList savePlayList(PlayList playList);
     PlayList deletePlayList(PlayList playList);
     PlayList updatePlayList(PlayList playList);

}

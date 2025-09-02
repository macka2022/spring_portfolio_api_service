package com.example.demoportflio.service;

import com.example.demoportflio.model.PlayList;

import java.util.List;

public interface PlayListService {

    public List<PlayList> getAllPlayLists();
    public PlayList getPlayListById(Long id);
    public PlayList savePlayList(PlayList playList);
    public PlayList deletePlayList(PlayList playList);
    public PlayList updatePlayList(PlayList playList);

}

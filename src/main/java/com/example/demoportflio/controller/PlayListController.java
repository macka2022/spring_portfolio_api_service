package com.example.demoportflio.controller;

import com.example.demoportflio.model.PlayList;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.PlayListService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/playlist")
 public class  PlayListController{
    private final PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPlayList(PlayList playList) {
            return ResponseHandler.ResponseBuilder("Play liste Ajoutée", HttpStatus.OK,playListService.savePlayList(playList) );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePlayList(@PathVariable Long id) {
            PlayList playList = playListService.getPlayListById(id);
         return    ResponseHandler.ResponseBuilder("Play liste suprimée", HttpStatus.OK, playListService.deletePlayList(playList));
    }

    @PostMapping("/modifify")
    public ResponseEntity<Object> modifyPlayList( @RequestBody PlayList playList){
        return  ResponseHandler.ResponseBuilder("Playmist modifiée", HttpStatus.OK,playListService.updatePlayList(playList));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> getPlayListById(@PathVariable Long id) {
        return  ResponseHandler.ResponseBuilder("playList Recupere", HttpStatus.OK, playListService.getPlayListById(id))  ;

    }

}

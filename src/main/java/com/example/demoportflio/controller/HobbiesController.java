package com.example.demoportflio.controller;

import com.example.demoportflio.model.Hobbies;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.HobbiesService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hobbies")
public class HobbiesController {
    private final HobbiesService hobbiesService;

    public HobbiesController(HobbiesService hobbiesService) {
        this.hobbiesService = hobbiesService;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addHobbies(@Valid @RequestBody Hobbies h, BindingResult result) {
            return ResponseHandler.ResponseBuilder("Hobbies ajouté", HttpStatus.OK,hobbiesService.addHobbies(h) );
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateHobbies(@Valid @RequestBody Hobbies h, BindingResult result) {
            return ResponseHandler.ResponseBuilder("Hobbies Modifié", HttpStatus.OK,hobbiesService.updateHobbies(h) );
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listHobbies() {
            return ResponseHandler.ResponseBuilder("Liste Hobbies", HttpStatus.OK, hobbiesService.getAllHobbies());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listHobbiesById(@PathVariable Long id) {
        return  ResponseHandler.ResponseBuilder("Hobby recupere", HttpStatus.OK, hobbiesService.getHobbies(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHobbies(@PathVariable Long id) {
            return ResponseHandler.ResponseBuilder("Hobbie suprimé", HttpStatus.OK, hobbiesService.deleteHobbies(id));
    }



}

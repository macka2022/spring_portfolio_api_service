package com.example.demoportflio.controller;

import com.example.demoportflio.model.Experience;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.ExperienceService;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/experience/id/username")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createExperience(@Valid  @RequestBody Experience experience) {
            return ResponseHandler.responseBuilder("Experience créée", HttpStatus.CREATED, experienceService.createExperience(experience));
    }


    @PostMapping("/modify")
    public ResponseEntity<Object> updateExperience(@Valid @RequestBody Experience experience) {

            return ResponseHandler.responseBuilder("Experiences", HttpStatus.CREATED,
                    experienceService.updateExperience(experience));
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Object> getExperienceDetail(@PathVariable("id") Long id) {

            return ResponseHandler.responseBuilder("Experience n'existe pas", HttpStatus.OK, experienceService.getExperienceById(id));

    }

    @GetMapping("list/{type}")
    public ResponseEntity<Object> findByType(@PathVariable("type") String type) {
        return ResponseHandler.responseBuilder("Listes experiences", HttpStatus.OK, experienceService.findByType(type));

    }


    @GetMapping("/list")
    public ResponseEntity<Object> getExperienceAll() {

            return ResponseHandler.responseBuilder("Apropos n'existe pas&", HttpStatus.OK, experienceService.getExperiences());

    }


    @DeleteMapping("/delete/{idGroup}")
    public ResponseEntity<Object> DeleteExperience(@PathVariable String idGroup) {
            return ResponseHandler.responseBuilder("Experience n'existe pas", HttpStatus.OK, experienceService.deleteExperience(idGroup));

    }


}

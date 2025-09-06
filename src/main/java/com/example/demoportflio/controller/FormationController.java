package com.example.demoportflio.controller;

import com.example.demoportflio.model.Formation;

import com.example.demoportflio.response.ResponseHandler;

import com.example.demoportflio.service.FormationService;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/formation")
public class FormationController {
    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addFormation( @Valid  @RequestBody Formation formation) {

            return ResponseHandler.responseBuilder("Formation ajouté", HttpStatus.OK,formationService.createFormation(formation));

    }
    @PostMapping("/update")
    public ResponseEntity<Object> updateFormation(@Valid @RequestBody Formation formation ) {
            return ResponseHandler.responseBuilder("Formation ajouté", HttpStatus.OK,formationService.updateFormation(formation));
    }
    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<Object> deleteFormation(@PathVariable String groupId) {
            return ResponseHandler.responseBuilder("La formation a été supprimée", HttpStatus.OK,formationService.deleteFormation(groupId));
    }
    @GetMapping("user/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable("id") Long id) {

            return ResponseHandler.responseBuilder("Formation ajouté", HttpStatus.OK,formationService.getFormationById(id));

    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllFormations() {
            return ResponseHandler.responseBuilder("Formation ajouté", HttpStatus.OK,formationService.getAllFormation());
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<Object> getFormationsByType(@PathVariable String type) {
        return ResponseHandler.responseBuilder("Formation ajoutée", HttpStatus.OK,formationService.findByType(type));
    }

}

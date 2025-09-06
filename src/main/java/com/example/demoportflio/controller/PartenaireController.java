package com.example.demoportflio.controller;


import com.example.demoportflio.model.Partenaire;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.PartenaireService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



//atout , ,experience , formation, , langue, competence , apropos, , temoignage, partenaire, certification, blog,
// playlist  , Hobies

@RestController
@RequestMapping("/partenaires")
public class PartenaireController {

    private final PartenaireService partenaireService;


    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createConnaissance(@Valid  @RequestBody Partenaire connaissance){
            return ResponseHandler.responseBuilder("Connaissance crée", HttpStatus.OK, partenaireService.saveConnaissance(connaissance));
    }
    @PostMapping("/update")
    public ResponseEntity<Object> updateConnaissance(@Valid @RequestBody Partenaire connaissance){
        return ResponseHandler.responseBuilder("Modifie avec succes", HttpStatus.OK,  partenaireService.updateConnaissance(connaissance));
    }
    @GetMapping("/list")
    public ResponseEntity<Object> getAllConnaissances(){
        return ResponseHandler.responseBuilder("Partenaires listés", HttpStatus.OK,  partenaireService.getConnaissances());
    }

    @GetMapping("/liste/{id}")
    public ResponseEntity<Object> getConnaissanceById(@PathVariable Long id){
            return ResponseHandler.responseBuilder("Détail de la section", HttpStatus.OK, partenaireService.getConnaissanceById(id));
        }
        @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteConnaissance(@PathVariable Long id){
                return  ResponseHandler.responseBuilder("Supprime de la section", HttpStatus.OK, partenaireService.deleteConnaissance(id));
        }
    }



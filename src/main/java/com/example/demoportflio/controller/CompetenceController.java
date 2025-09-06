package com.example.demoportflio.controller;


import com.example.demoportflio.model.Competence;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.CompetenceService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/competence/id/username")
public class CompetenceController  {
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCompetence(@Valid @RequestBody Competence competence, BindingResult resul) {



            return ResponseHandler.responseBuilder("Competence ajouté", HttpStatus.OK, competenceService.createCompetence(competence));


    }
    @GetMapping("/list")
    public ResponseEntity<Object> listCompetences() {



            return ResponseHandler.responseBuilder("Liste des compétences", HttpStatus.OK, competenceService.findAllCompetence());


    }

    @GetMapping("/list/{type}")
    public ResponseEntity<Object> listCompetencesByType(@PathVariable String type) {
        return ResponseHandler.responseBuilder("certificats listés", HttpStatus.OK, competenceService.findByType(type) );
    }

    @DeleteMapping("/delete/{idGroup}")
    public ResponseEntity<Object> deleteCompetence(@PathVariable String idGroup) {


            return ResponseHandler.responseBuilder("Competence suprimé", HttpStatus.OK, competenceService.deleteCompetence(idGroup));




    }
    @PostMapping("/update")
    public  ResponseEntity<Object> updateCompetence( @Valid @RequestBody Competence competence) {
        return  ResponseHandler.responseBuilder("Competence modifié", HttpStatus.OK, competenceService.updateCompetence(competence));
    }
    @GetMapping("/liste/{id}")
    public ResponseEntity<Object> getCompetenceById(@PathVariable Long id) {
            return ResponseHandler.responseBuilder("Détail de la section", HttpStatus.OK, competenceService.findCompetenceById(id));

        }



    }



package com.example.demoportflio.controller;


import com.example.demoportflio.model.Langues;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.LanguesService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/langues")
public class LanguesController {
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
    private  final LanguesService languesService;

    public LanguesController(LanguesService languesService) {
        this.languesService = languesService;
    }
    @PostMapping("/add")
    public ResponseEntity<Object> createLangues(@Valid @RequestBody Langues langues ) {
            return ResponseHandler.responseBuilder("Langue créée", HttpStatus.CREATED, languesService.createLangues(langues));
    }
    @GetMapping("/list")
    public ResponseEntity<Object> listLangues() {
            return ResponseHandler.responseBuilder("Listes des langues", HttpStatus.OK,  languesService.getAllLangues());
    }
    @PostMapping("/update")
    public ResponseEntity<Object> updateLangues(@RequestBody Langues langues) {
             return ResponseHandler.responseBuilder("Langues modifiée", HttpStatus.OK, languesService.updateLangues(langues));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listLangueDetail(@PathVariable Long id) {
            return ResponseHandler.responseBuilder("Liste des langues "+ id, HttpStatus.OK, languesService.getLanguesById(id));
    }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Object> deleteLangue(@PathVariable Long id){
                return ResponseHandler.responseBuilder("Langues avec id "+ id, HttpStatus.OK,  languesService.deleteLangues(id));
    }

}

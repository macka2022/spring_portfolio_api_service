package com.example.demoportflio.controller;


import com.example.demoportflio.model.Atout;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.AtoutService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atout")
public class AtoutController {

    @Autowired
    private  AtoutService atoutService;



    @GetMapping("/list")
    public ResponseEntity<Object>  listCertification() {
            return ResponseHandler.responseBuilder("Liste des atouts", HttpStatus.OK, atoutService.getAllSkills());
    }
//hhh

        @PostMapping("/add")
        public ResponseEntity<Object> addCertification (@Valid @RequestBody Atout atout){
                return ResponseHandler.responseBuilder("Ajoutés avec succéss", HttpStatus.CREATED, atoutService.addAtout(atout));
        }
        @GetMapping("list/{id}")
        public ResponseEntity<Object> listCertification (@PathVariable Long id){
                 return ResponseHandler.responseBuilder("AAtout recuperé avec success", HttpStatus.OK, atoutService.getAtoutById(id));
        }

        @PostMapping("/update")
        public ResponseEntity<Object> updateCertification (@RequestBody Atout atout){
                return ResponseHandler.responseBuilder("Atout Modifié", HttpStatus.OK, atoutService.updateAtout(atout));
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Object> deleteCertification (@PathVariable Long id){
           return ResponseHandler.responseBuilder("Atout Suprimé avec success", HttpStatus.OK, atoutService.deleteAtout(id));
        }


    }


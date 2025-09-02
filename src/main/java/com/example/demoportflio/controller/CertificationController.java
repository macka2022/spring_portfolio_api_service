package com.example.demoportflio.controller;

import com.example.demoportflio.model.Certification;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.CertificationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("certification/id/username")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addCertification(@Valid @RequestBody Certification certification) {
            return ResponseHandler.ResponseBuilder("certificat ajouté", HttpStatus.OK, certificationService.saveCertification(certification) );

    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> findCertificationById(@PathVariable   Long id) {
        return ResponseHandler.ResponseBuilder("certificat id= "+id , HttpStatus.OK, certificationService.findCertificationById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCertification(@RequestBody Certification certification) {
            return ResponseHandler.ResponseBuilder("certificat modifié", HttpStatus.OK, certificationService.updateCertification(certification) );
    }
    @GetMapping("/list")
    public ResponseEntity<Object> findAllCertifications() {
            return ResponseHandler.ResponseBuilder("certificats listés", HttpStatus.OK, certificationService.findAllCertification() );
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<Object> findCertificationsByType(@PathVariable String type) {
        return ResponseHandler.ResponseBuilder("certificats listés", HttpStatus.OK, certificationService.findByType(type) );
    }



    @DeleteMapping("/delete/{idGroupe}")
    public ResponseEntity<Object> deleteCertification(@PathVariable String idGroupe) {
            return ResponseHandler.ResponseBuilder("certificat suprimé", HttpStatus.OK, certificationService.deleteCertification(idGroupe) );
    }
}

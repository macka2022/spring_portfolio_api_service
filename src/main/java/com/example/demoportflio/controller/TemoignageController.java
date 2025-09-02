package com.example.demoportflio.controller;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Langues;
import com.example.demoportflio.model.Temoignage;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.TemoignageService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/temoignages")
@RequestMapping()
public class TemoignageController {
    private final TemoignageService temoignageService;
    private static final Logger logger = LoggerFactory.getLogger(TemoignageController.class);
    public TemoignageController(TemoignageService temoignageService) {
        this.temoignageService = temoignageService;
    }

    @GetMapping("/add")
    public ResponseEntity<Object> addTemoignage(@Valid @RequestBody Temoignage temoignage, BindingResult result) {



        if (result.hasErrors()) {

            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseHandler.ResponseBuilder("Temoignage cree avec success ", HttpStatus.OK, temoignageService.saveTemoignage(temoignage));


        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.ResponseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.ResponseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }

    }
    @PostMapping("/upadate")
    public ResponseEntity<Object> updateTemoignage(@RequestBody Temoignage temoignage, BindingResult result) {


        if (result.hasErrors()) {

            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        try {

            return  ResponseHandler.ResponseBuilder("Temoignage modifie avec success ", HttpStatus.OK, temoignageService.updateTemoignage(temoignage));

        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.ResponseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.ResponseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }



    }

    @GetMapping("/list")
    public ResponseEntity<Object> listTemoignages() {


        try {

            return  ResponseHandler.ResponseBuilder("Liste des temoignages", HttpStatus.OK,  temoignageService.listTemoignage());

        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.ResponseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.ResponseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }

    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Object> deleteTemoignage(@PathVariable Long id) {
        try {

            return  ResponseHandler.ResponseBuilder("Temoignages suprimé", HttpStatus.OK,  temoignageService.deleteTemoignage(id));

        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.ResponseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.ResponseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @DeleteMapping("/list/{id}")
    public  ResponseEntity<Object> listOneTemoignage(@PathVariable Long id) {
        try {

            return  ResponseHandler.ResponseBuilder("Temoignages avec id "+ id, HttpStatus.OK,  temoignageService.getTemoignageById(id));

        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.ResponseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.ResponseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.ResponseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }



    }


}

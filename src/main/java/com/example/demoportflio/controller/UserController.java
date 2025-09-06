package com.example.demoportflio.controller;


import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.User;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.UserService;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
    private final UserService userService;

    public UserController(UserService userService) {
        //
        this.userService = userService;

    }

    @PostMapping("/inscription")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){

      /*  if (result.hasErrors()) {

            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            return ResponseHandler.responseBuilder("Ajouté utilisateur", HttpStatus.OK, userService.CreateUser(user) );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );
        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }*/
        return ResponseHandler.responseBuilder("Ajouté utilisateur", HttpStatus.OK, userService.createUser(user) );
    }


    @GetMapping("/listuser")
    public ResponseEntity<Object> listUser() {

        /*try {
            return ResponseHandler.responseBuilder("Ajouté utilisateur", HttpStatus.OK, userService.listUser() );
        } catch (ApiExecptionHandler.UserNotFoundException e) {
            return ResponseHandler.responseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );
        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }*/


        return ResponseHandler.responseBuilder("Liste utilisateur", HttpStatus.OK, userService.listUser());
    }
}

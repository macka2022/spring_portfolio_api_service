package com.example.demoportflio.controller;


import com.example.demoportflio.model.Section;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.SectionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/section/id/username")

public class SectionController {
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")

    private final SectionService sectionService;

    public SectionController(@NotBlank  SectionService sectionService) {
        this.sectionService = Objects.requireNonNull(sectionService);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createSection(@Valid @RequestBody Section section) {
       /* if (result.hasErrors()) {

            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        try {*/


            return ResponseHandler.responseBuilder(
                    "Section ajouté",
                    HttpStatus.OK,
                    sectionService.createSection(section)
            );

      /*  }catch (ApiExecptionHandler.UserNotFoundException e){
            return ResponseHandler.responseBuilder()(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        }


        catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder()(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }*/


    }


    @PostMapping("/user/modify")
    public ResponseEntity<Object> updateSection(@Valid @RequestBody Section section , BindingResult result) {
       /* if (result.hasErrors()) {

            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        try {*/
            Section sections = sectionService.updateSection(section);
            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    sections
            );

      /*  }catch (ApiExecptionHandler.UserNotFoundException e){
            return ResponseHandler.responseBuilder()(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


        } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

         } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder()(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }*/

    }

    @GetMapping("/user/list")
    public ResponseEntity<Object> getAllSection() {
       /* try {*/
            List<Section> sections = sectionService.getSections();
            return ResponseHandler.responseBuilder(
                    "Liste des sections",
                    HttpStatus.OK,
                    sections
            );

       /* } catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder()(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }*/
    }


    @GetMapping("user/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable("id") Long id) {
       /* try {*/
            Section section = sectionService.getSectionById(id);
            return ResponseHandler.responseBuilder(
                    "La section id" + id,
                    HttpStatus.OK,
                    section
            );

       /* }catch (ApiExecptionHandler.UserNotFoundException e){
            return ResponseHandler.responseBuilder()(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );


    }catch (org.springframework.dao.DataAccessException e) {
            logger.error("Erreur d'accès à la base de données", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur interne : problème d'accès aux données",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );

        } catch (org.springframework.web.client.RestClientException e) {
            logger.error("Erreur de connexion à un service externe", e);
            return ResponseHandler.responseBuilder()(
                    "Erreur réseau ou service indisponible",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    null
            );

        } catch (Exception e) {
            logger.error("Erreur interne inattendue", e);
            return ResponseHandler.responseBuilder()(
                    "Une erreur interne s'est produite",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }

       // return ResponseHandler.responseBuilder()("Détail de la section", HttpStatus.OK, section);*/
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteSection(@PathVariable("id") Long id) {
       // try {
            Section section = sectionService.deleteSection(id);
            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    section
            );

    }
}

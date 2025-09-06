package com.example.demoportflio.controller;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Apropos;

import com.example.demoportflio.model.Section;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.AproposService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/apropos/id/username")
@RestController

public class AproposController {
    @Autowired
    private AproposService aproposService;

    @PostMapping("/add")
    public ResponseEntity<Object> createApropos(@Valid @RequestBody Apropos apropos) {


        return ResponseHandler.responseBuilder(
                "Section ajoutée",
                HttpStatus.OK,
                aproposService.createApropos(apropos)
        );

    }


    @PostMapping("/modify")
    public ResponseEntity<Object> updateApropos(@Valid @RequestBody Apropos apropos) {



            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    aproposService.updateApropos(apropos)
            );

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable("id") Long id) {
     /*   try {*/
            Apropos apropos = aproposService.getAproposById(id);
            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    apropos
            );


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteApropos(@PathVariable("id") Long id) {

         Long  idpath= aproposService.getAproposById(id).getId();
            Apropos apropos = aproposService.deleteApropos(idpath);
          return   ResponseHandler.responseBuilder("A propos suprimé", HttpStatus.OK, apropos);

    }

      @GetMapping("/list")
    public ResponseEntity<Object> getAllApropos() {

            return ResponseHandler.responseBuilder("Liste a propos", HttpStatus.OK, aproposService.getAllApropos());

        }
      }





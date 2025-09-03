package com.example.demoportflio.controller;
import com.example.demoportflio.model.Temoignage;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.TemoignageService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController("/temoignages")
@RequestMapping()
public class TemoignageController {
    private final TemoignageService temoignageService;
    private static final Logger logger = LoggerFactory.getLogger(TemoignageController.class);
    public TemoignageController(TemoignageService temoignageService) {
        this.temoignageService = temoignageService;
    }

    @GetMapping("/add")
    public ResponseEntity<Object> addTemoignage(@Valid @RequestBody Temoignage temoignage) {
            return ResponseHandler.ResponseBuilder("Temoignage cree avec success ", HttpStatus.OK, temoignageService.saveTemoignage(temoignage));
    }


    @PostMapping("/upadate")
    public ResponseEntity<Object> updateTemoignage(@RequestBody Temoignage temoignage) {
            return  ResponseHandler.ResponseBuilder("Temoignage modifie avec success ", HttpStatus.OK, temoignageService.updateTemoignage(temoignage));
    }


    @GetMapping("/list")
    public ResponseEntity<Object> listTemoignages() {
            return  ResponseHandler.ResponseBuilder("Liste des temoignages", HttpStatus.OK,  temoignageService.listTemoignage());
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Object> deleteTemoignage(@PathVariable Long id) {
            return  ResponseHandler.ResponseBuilder("Temoignages suprimé", HttpStatus.OK,  temoignageService.deleteTemoignage(id));
    }

    @DeleteMapping("/list/{id}")
    public  ResponseEntity<Object> listOneTemoignage(@PathVariable Long id) {
            return  ResponseHandler.ResponseBuilder("Temoignages avec id "+ id, HttpStatus.OK,  temoignageService.getTemoignageById(id));
    }


}

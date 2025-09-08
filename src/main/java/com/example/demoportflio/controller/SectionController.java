package com.example.demoportflio.controller;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.SectionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


        return ResponseHandler.responseBuilder(
                "Section ajouté",
                HttpStatus.CREATED,
                sectionService.createSection(section)
        );
    }
    @PostMapping("/user/modify")
    public ResponseEntity<Object> updateSection(@Valid @RequestBody Section section ) {
            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    sectionService.updateSection(section)
            );


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


    }



    @GetMapping("user/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable("id") Long id) {
            return ResponseHandler.responseBuilder(
                    "La section id" + id,
                       HttpStatus.OK,
                    sectionService.getSectionById(id)
            );
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteSection(@PathVariable("id") Long id) {

            return ResponseHandler.responseBuilder(
                    "Section modifié",
                    HttpStatus.OK,
                    sectionService.deleteSection(id)
            );

    }
}

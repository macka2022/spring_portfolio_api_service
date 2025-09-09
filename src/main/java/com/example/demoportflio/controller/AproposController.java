package com.example.demoportflio.controller;


import com.example.demoportflio.model.Apropos;


import com.example.demoportflio.model.User;
import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.AproposService;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RequestMapping("/apropos")
@RestController

public class AproposController {


    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")


    private final AproposService aproposService;

    public AproposController(final AproposService aproposService) {
        this.aproposService = aproposService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPortfolioPage(@PathVariable Long id) {
        Apropos propos = aproposService.findBySectionUserId(id);

        if (propos == null) return ResponseEntity.notFound().build();


        User user = propos.getSection().getUser();

        // Image par défaut si photo null
        String photoUrl = propos.getPhoto() != null && !propos.getPhoto().isEmpty()
                ? propos.getPhoto()
                : "https://via.placeholder.com/150";

        // Liens GitHub et LinkedIn par défaut si null
        String gitUrl = propos.getGit() != null && !propos.getGit().isEmpty()
                ? propos.getGit()
                : "#";
        String linkedinUrl = propos.getLinkedin() != null && !propos.getLinkedin().isEmpty()
                ? propos.getLinkedin()
                : "#";

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta property=\"og:title\" content=\"" + propos.getTitre() + "\" />\n" +
                "    <meta property=\"og:description\" content=\"" + propos.getProfile() + "\" />\n" +
                "    <meta property=\"og:image\" content=\"" + photoUrl + "\" />\n" +
                "    <meta property=\"og:url\" content=\"https://tonapp.com/u/" + user.getName() + "\" />\n" +
                "    <title>Portfolio - " + user.getName() + "</title>\n" +
                "    <style>\n" +
                "        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f2f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }\n" +
                "        .card { background: white; border-radius: 15px; box-shadow: 0 8px 20px rgba(0,0,0,0.15); max-width: 600px; width: 100%; padding: 30px; text-align: center; }\n" +
                "        .card img { width: 150px; height: 150px; object-fit: cover; border-radius: 50%; border: 4px solid #007bff; margin-bottom: 20px; }\n" +
                "        h1 { color: #007bff; margin-bottom: 10px; }\n" +
                "        p { color: #555; line-height: 1.6; }\n" +
                "        .user-name { font-weight: bold; color: #333; margin-top: 15px; }\n" +
                "        .links a { margin: 0 10px; text-decoration: none; color: white; background-color: #007bff; padding: 10px 15px; border-radius: 5px; transition: 0.3s; }\n" +
                "        .links a:hover { background-color: #0056b3; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"card\">\n" +
                "        <img src=\"" + photoUrl + "\" alt=\"Portfolio image\">\n" +
                "        <h1>" + propos.getTitre() + "</h1>\n" +
                "        <p>" + propos.getProfile() + "</p>\n" +
                "        <p class=\"user-name\">" + user.getName() + "</p>\n" +
                "        <div class=\"links\">\n" +
                "            <a href=\"" + gitUrl + "\" target=\"_blank\">GitHub</a>\n" +
                "            <a href=\"" + linkedinUrl + "\" target=\"_blank\">LinkedIn</a>\n" +
                "            <a href=\"mailto:" + propos.getEmail() + "\">Contact</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        //s

        return ResponseEntity.ok().body(html);
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





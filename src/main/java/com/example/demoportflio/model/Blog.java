package com.example.demoportflio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Blog {
        @Id
        @SequenceGenerator(
                name = "blog_seq",               // logique Hibernate
                sequenceName = "blog_sequence",  // objet séquence dans PostgreSQL
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "blog_seq"
        )
        private Long id;

        @NotBlank(message = "Article est requis")
        @Size(message = "Titre est compris entre 10/50", min = 10, max = 30)
        private String titre;


        private LocalDate datePublication;
        @NotBlank(message = "Resume est requis")
        @Size(message = "Resumé est compris entre 20/200", min = 10, max = 200)

        private String resume;
        @Column(columnDefinition = "TEXT")
        @Size( min=200,max = 500, message = "Contenu est entre 200/500 ")
        private String contenu;
        private String imagePrincipale;

        private String categorie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Section section;

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImagePrincipale() {
        return imagePrincipale;
    }

    public void setImagePrincipale(String imagePrincipale) {
        this.imagePrincipale = imagePrincipale;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}



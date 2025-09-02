package com.example.demoportflio.model;

import com.example.demoportflio.exception.user.UniqueEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_seq",               // logique Hibernate
            sequenceName = "user_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    private Long id;


    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 4, max = 50)
    private String name;


    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, max = 20)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,20}$",
            message = "Le mot de passe doit contenir entre 8 et 20 caractères, avec au moins une lettre minuscule,  majuscule et un caractère spécial."
    )
    private String password;


    @NotBlank(message = "L'émail est obligatoire")
    @Size(min = 4, max = 100)
   // @Column(unique = true)


    private  String email;

    @NotBlank(message = "Le domaine est obligatoire")
     @Size(min = 2, max = 80)
    private String domaine;

    @NotBlank(message = "La localisation est obligatoire")
    @Size(min = 2, max = 100)
    private String localisation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }





    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String location) {
        this.localisation = location;
    }
}

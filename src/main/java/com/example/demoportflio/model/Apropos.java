package com.example.demoportflio.model;

import com.example.demoportflio.exception.user.UniqueEmail;
import com.example.demoportflio.exception.user.UniquePhone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.w3c.dom.Text;

import java.sql.Date;

@Setter
@Getter
@Entity
public class Apropos {
    @Id
    @SequenceGenerator(
            name = "propos_seq",               // logique Hibernate
            sequenceName = "propos_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "propos_seq"
    )
    private Long id;




    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 4, max = 100)
    private String nom;

    @Size( max = 200)
    private String photo;

   // @NotBlank(message = "Le lien GitHub est obligatoire")

    @Column(name = "git_url")
    @Size( max = 200)
    private String git;

   // @NotBlank(message = "Le lien LinkedIn est obligatoire")

    @Column(name = "linkedin_url")
    @Size( max = 200)
    private String linkedin;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")

    @Size(min = 4, max = 150)

    private String email;

    private Type types;
    enum Type{
        CV, PORTFOLIO, BOTH
    }

    //@NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^\\+?[0-9\\s-]{20,}$", message = "Numéro de téléphone invalide")
    //@UniquePhone
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;



    @NotBlank(message = "Niveau est obligatoire")
    @Size(min = 4, max =100)

    private String localisation;



    @Size(min = 4, max = 100)
    private String niveau;

    @NotBlank(message = "profile est obligatoire")
    @Size(min = 50, max = 300)
    private String profile;

    @NotBlank(message = "titre est obligatoire")
    @Size(min = 4, max = 200)
    private String titre;

    // Audit fields
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Relationships (example)


}

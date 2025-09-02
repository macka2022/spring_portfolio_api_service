package com.example.demoportflio.service;

import com.example.demoportflio.model.Partenaire;

import java.util.List;

public interface PartenaireService {
    List<Partenaire> getConnaissances();
    Partenaire getConnaissanceById(Long id);
    Partenaire saveConnaissance(Partenaire c);
    Partenaire deleteConnaissance(Long id);
    Partenaire updateConnaissance(Partenaire c);


}

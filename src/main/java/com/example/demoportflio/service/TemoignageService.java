package com.example.demoportflio.service;

import com.example.demoportflio.model.Temoignage;

import java.util.List;

public interface TemoignageService {

    List<Temoignage> listTemoignage();
    Temoignage getTemoignageById(Long id);
    Temoignage saveTemoignage(Temoignage temoignage);
    Temoignage deleteTemoignage(Long id);
     Temoignage updateTemoignage(Temoignage temoignage);
}

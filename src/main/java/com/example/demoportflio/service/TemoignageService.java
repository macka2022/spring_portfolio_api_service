package com.example.demoportflio.service;

import com.example.demoportflio.model.Temoignage;

import java.util.List;

public interface TemoignageService {

    public List<Temoignage> listTemoignage();
    public Temoignage getTemoignageById(Long id);
    public Temoignage saveTemoignage(Temoignage temoignage);
    public Temoignage deleteTemoignage(Long id);
    public Temoignage updateTemoignage(Temoignage temoignage);
}

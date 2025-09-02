package com.example.demoportflio.service;

import com.example.demoportflio.model.Langues;

import java.util.List;

public interface LanguesService {
    Langues createLangues(Langues langues);
    List<Langues> getAllLangues();
    Langues getLanguesById(Long id);
    Langues updateLangues(Langues langues);
    Langues deleteLangues(Long id);

}

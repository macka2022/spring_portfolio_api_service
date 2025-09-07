package com.example.demoportflio.service;

import com.example.demoportflio.model.Apropos;


import java.util.List;

public interface AproposService {
    Apropos createApropos(Apropos apropos);
    List<Apropos> getAllApropos();
    Apropos getAproposById(Long id);
    Apropos updateApropos(Apropos apropos);
    Apropos deleteApropos(Long id);
}

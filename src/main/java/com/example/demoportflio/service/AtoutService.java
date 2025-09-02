package com.example.demoportflio.service;

import com.example.demoportflio.model.Atout;

import java.util.List;

public interface AtoutService {

    public List<Atout> getAllSkills();
    public Atout getAtoutById(Long id);
    public Atout updateAtout(Atout to);
    public Atout deleteAtout(Long id);
    public Atout addAtout(Atout to);
}

package com.example.demoportflio.repository;

import com.example.demoportflio.model.Atout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AtoutRepository extends JpaRepository<Atout, Long> {
}

package com.example.demoportflio.repository;

import com.example.demoportflio.model.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbiesRRepository extends JpaRepository<Hobbies, Long> {
}

package com.example.demoportflio.repository;

import com.example.demoportflio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}


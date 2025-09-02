package com.example.demoportflio.repository;

import com.example.demoportflio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}


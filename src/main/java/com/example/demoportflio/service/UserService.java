package com.example.demoportflio.service;

import com.example.demoportflio.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User CreateUser(User user);
    public List<User> listUser();
    public User activeDesactive(User user);
}

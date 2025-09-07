package com.example.demoportflio.service;

import com.example.demoportflio.model.User;


import java.util.List;


public interface UserService {
    public User createUser(User user);
    public List<User> listUser();
    public User activeDesactive(User user);
}

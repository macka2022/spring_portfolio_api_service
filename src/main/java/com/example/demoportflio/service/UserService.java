package com.example.demoportflio.service;

import com.example.demoportflio.model.User;


import java.util.List;


public interface UserService {
     User createUser(User user);
     List<User> listUser();
    User activeDesactive(User user);
}

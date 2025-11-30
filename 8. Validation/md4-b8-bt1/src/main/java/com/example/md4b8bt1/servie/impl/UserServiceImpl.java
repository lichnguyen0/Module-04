package com.example.md4b8bt1.servie.impl;


import com.example.md4b8bt1.model.User;
import com.example.md4b8bt1.servie.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user); // lưu tạm vào list
        System.out.println("Saved user: " + user.getFirstName() + " " + user.getLastName());
    }
}


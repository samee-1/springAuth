package com.example.auth.Service;

import com.example.auth.User.user;

public interface userService {
    public user saveUser(user user);
    public user findById(Integer id);

}

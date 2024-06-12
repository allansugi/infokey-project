package com.infokey.backend.User;

import java.util.List;


public interface UserService {
    int save(UserAccount userAccount);
    int update();
    int delete();
    List<UserAccount> findAll();
    UserAccount findById(String id);
}

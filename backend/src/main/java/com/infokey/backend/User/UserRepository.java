package com.infokey.backend.User;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    List<UserAccount> findAll();

    Optional<UserAccount> findById(String id);

    Optional<UserAccount> findByUsername(String username);

    int create(UserAccount userAccount);

    void update(UserAccount userAccount, String id);

    void delete(String id);
}

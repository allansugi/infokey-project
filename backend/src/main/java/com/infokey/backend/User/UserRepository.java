package com.infokey.backend.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    List<UserAccount> findAll();

    Optional<UserAccount> findById(String id);

    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByEmail(String email);

    int create(UserAccount userAccount);

    void update(UserAccount userAccount, String id);

    void delete(String id);
}

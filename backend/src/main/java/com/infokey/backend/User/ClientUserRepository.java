package com.infokey.backend.User;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientUserRepository implements UserRepository{

    private final JdbcClient jdbcClient;

    public ClientUserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<UserAccount> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserAccount> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return jdbcClient.sql("SELECT * FROM user_account WHERE username = :username")
                .param("username", username)
                .query(UserAccount.class)
                .optional();
    }

    @Override
    public int create(UserAccount userAccount) {
            return jdbcClient.sql("INSERT INTO user_account (id, username, email, password) VALUES (?, ?, ?, ?)")
                    .params(List.of(UUID.randomUUID().toString(), userAccount.username(), userAccount.email(), userAccount.password()))
                    .update();
    }

    @Override
    public void update(UserAccount userAccount, String id) {

    }

    @Override
    public void delete(String id) {

    }
}

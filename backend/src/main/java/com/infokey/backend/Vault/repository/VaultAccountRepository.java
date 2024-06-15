package com.infokey.backend.Vault.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.infokey.backend.Vault.dto.VaultAccountItem;

@Repository
public class VaultAccountRepository implements VaultRepository {

    private final JdbcClient jdbcClient;

    public VaultAccountRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public int create(VaultAccountItem item) {
        return jdbcClient.sql("INSERT INTO vault_account (id, owner, name, username, password) VALUES (?, ?, ?, ?, ?)")
                            .params(
                                List.of(
                                    item.id(), 
                                    item.owner(),
                                    item.name(),
                                    item.username(), 
                                    item.password()
                                )
                            )
                            .update();
    }

    @Override
    public List<VaultAccountItem> findByOwner(String owner) {
        return jdbcClient.sql("SELECT * FROM vault_account WHERE owner = :owner")
                            .param("owner", owner)
                            .query(VaultAccountItem.class)
                            .list();
    }

    @Override
    public int update(VaultAccountItem item) {
        return jdbcClient.sql("""
                            UPDATE vault_account
                            SET name = ?, username = ?, password = ?
                            WHERE id = ?
                            """)
                            .params(
                                List.of(
                                    item.name(),
                                    item.username(), 
                                    item.password(),
                                    item.id()
                                )
                            )
                            .update();
    }

    @Override
    public void delete(String id) {
        jdbcClient.sql("DELETE FROM vault_account WHERE id = :id")
                    .param("id", id)
                    .update();
    }

    @Override
    public Optional<VaultAccountItem> findById(String id) {
        return jdbcClient.sql("SELECT * FROM vault_account WHERE id = :id")
                            .param("id", id)
                            .query(VaultAccountItem.class)
                            .optional();
    }

}

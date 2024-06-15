package com.infokey.backend.Vault;

import java.util.List;
import java.util.Optional;

public interface VaultRepository {
    int create(VaultAccountItem item);
    List<VaultAccountItem> findByOwner(String owner);
    Optional<VaultAccountItem> findById(String id);
    int update(VaultAccountItem item);
    void delete(String id);
}

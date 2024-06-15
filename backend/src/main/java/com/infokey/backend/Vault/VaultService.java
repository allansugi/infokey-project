package com.infokey.backend.Vault;

import java.util.List;

public interface VaultService {
    String saveVault(NewVaultAccountItemRequest item, String userId);
    void updateVault(VaultAccountItem item);
    List<VaultAccountItem> findAllVaultByOwner(String owner);
    void deleteVaultItem(String id);
}

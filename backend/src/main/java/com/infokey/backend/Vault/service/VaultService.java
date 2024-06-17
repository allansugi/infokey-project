package com.infokey.backend.Vault.service;

import java.util.List;

import com.infokey.backend.Vault.dto.VaultAccountItem;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;

public interface VaultService {
    String saveVault(NewVaultAccountItemRequest item, String userId);
    void updateVault(VaultAccountItem item);
    List<VaultAccountItem> findAllVaultByOwner(String owner);
    VaultAccountItem findById(String id);
    void deleteVaultItem(String id);
}

package com.infokey.backend.Vault.service;

import java.util.List;

import com.infokey.backend.Vault.dto.VaultAccountItem;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;
import com.infokey.backend.Vault.response.VaultAccountView;

public interface VaultService {
    String saveVault(NewVaultAccountItemRequest item, String userId);
    void updateVault(VaultAccountItem item);
    List<VaultAccountView> findAllVaultByOwner(String owner);
    void deleteVaultItem(String id, String owner);
    VaultAccountItem findById(String id);
}

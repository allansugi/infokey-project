package com.infokey.backend.Vault.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.infokey.backend.Vault.dto.VaultAccountItem;
import com.infokey.backend.Vault.exception.ItemNotFoundException;
import com.infokey.backend.Vault.repository.VaultAccountRepository;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;

@Service
public class VaultAccountService implements VaultService {

    VaultAccountRepository VaultAccountRepository;

    public VaultAccountService(VaultAccountRepository VaultAccountRepository) {
        this.VaultAccountRepository = VaultAccountRepository;
    }

    @Override
    public String saveVault(NewVaultAccountItemRequest item, String userId) {
        String accountId = UUID.randomUUID().toString();
        VaultAccountItem newItem = new VaultAccountItem(accountId, item.name(), userId, item.username(), item.password());
        VaultAccountRepository.create(newItem);
        return accountId;
    }

    @Override
    public void updateVault(VaultAccountItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateVault'");
    }

    @Override
    public List<VaultAccountItem> findAllVaultByOwner(String owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllVaultByOwner'");
    }

    @Override
    public void deleteVaultItem(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteVaultItem'");
    }

    @Override
    public VaultAccountItem findById(String id) {
        return VaultAccountRepository.findById(id).orElseThrow(() -> new ItemNotFoundException());
    }

}

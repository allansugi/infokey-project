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

    VaultAccountRepository vaultAccountRepository;

    public VaultAccountService(VaultAccountRepository VaultAccountRepository) {
        this.vaultAccountRepository = VaultAccountRepository;
    }

    @Override
    public String saveVault(NewVaultAccountItemRequest item, String userId) {
        String accountId = UUID.randomUUID().toString();
        VaultAccountItem newItem = new VaultAccountItem(accountId, item.name(), userId, item.username(), item.password());
        vaultAccountRepository.create(newItem);
        return accountId;
    }

    @Override
    public void updateVault(VaultAccountItem item) {
        if (!vaultAccountRepository.existByIdAndOwner(item.id(), item.owner())) {
            throw new ItemNotFoundException();
        }
        vaultAccountRepository.update(item);
    }

    @Override
    public List<VaultAccountItem> findAllVaultByOwner(String owner) {
       return vaultAccountRepository.findByOwner(owner);
    }

    @Override
    public void deleteVaultItem(String id, String owner) {
        if (!vaultAccountRepository.existByIdAndOwner(id, owner)) {
            throw new ItemNotFoundException();
        }
        vaultAccountRepository.delete(id);
    }

    @Override
    public VaultAccountItem findById(String id) {
        return vaultAccountRepository.findById(id).orElseThrow(() -> new ItemNotFoundException());
    }

}

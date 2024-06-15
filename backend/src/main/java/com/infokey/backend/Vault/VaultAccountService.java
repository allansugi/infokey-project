package com.infokey.backend.Vault;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VaultAccountService implements VaultService {

    @Override
    public String saveVault(NewVaultAccountItemRequest item, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveVault'");
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

}

package com.infokey.backend.repository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.infokey.backend.User.dto.UserAccount;
import com.infokey.backend.User.repository.ClientUserRepository;
import com.infokey.backend.Vault.dto.VaultAccountItem;
import com.infokey.backend.Vault.repository.VaultAccountRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class VaultAccountRepositoryTest {

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Autowired
    private VaultAccountRepository vaultAccountRepository;

    private String userId;

    @BeforeEach
    void setup() {
        this.userId = UUID.randomUUID().toString();
        UserAccount userAccount = new UserAccount(userId, "username", "email", "password");
        clientUserRepository.create(userAccount);
    }

    @Test
    void insertAnAccountIntoVault() {
        VaultAccountItem item = new VaultAccountItem(
            UUID.randomUUID().toString(), 
            "name",
            userId,
            "username23423423",
            "password234234234"
        );
        int update = vaultAccountRepository.create(item);
        assertEquals(update, 1);
    }

    @Test
    void findAccountsOwnedByUserId() {

        VaultAccountItem item1 = new VaultAccountItem(
            UUID.randomUUID().toString(), 
            "name1",
            userId,
            "username1212121",
            "password21212121"
        );

        VaultAccountItem item2 = new VaultAccountItem(
            UUID.randomUUID().toString(), 
            "name2",
            userId,
            "usernameagg GWfs",
            "passwordasdacgwgw"
        );

        VaultAccountItem item3 = new VaultAccountItem(
            UUID.randomUUID().toString(), 
            "name3",
            userId,
            "username23423423",
            "password234234234"
        );

        vaultAccountRepository.create(item1);
        vaultAccountRepository.create(item2);
        vaultAccountRepository.create(item3);

        List<VaultAccountItem> items = vaultAccountRepository.findByOwner(userId);
        assertEquals(items.size(), 3);
    }

    @Test
    void updateVaultAccount() {

        String id = UUID.randomUUID().toString();
        VaultAccountItem item = new VaultAccountItem(
            id, 
            "name",
            userId,
            "username23423423",
            "password234234234"
        );
        
        vaultAccountRepository.create(item);

        VaultAccountItem itemUpdate = new VaultAccountItem(
            id, 
            "name",
            userId,
            "newusername",
            "newpassword"
        );

        int update = vaultAccountRepository.update(itemUpdate);
        assertEquals(update, 1);

        VaultAccountItem updatedItem = vaultAccountRepository.findById(id).get();
        assertEquals(itemUpdate.username(), updatedItem.username());
        assertEquals(itemUpdate.password(), updatedItem.password());
    }

    @Test
    void deleteAccountFromVault() {

        String id1 = UUID.randomUUID().toString();
        VaultAccountItem item1 = new VaultAccountItem(
            id1, 
            "name1",
            userId,
            "username1212121",
            "password21212121"
        );

        VaultAccountItem item2 = new VaultAccountItem(
            UUID.randomUUID().toString(), 
            "name2",
            userId,
            "usernameagg GWfs",
            "passwordasdacgwgw"
        );

        vaultAccountRepository.create(item1);
        vaultAccountRepository.create(item2);

        List<VaultAccountItem> allItems = vaultAccountRepository.findByOwner(userId);
        assertEquals(allItems.size(), 2);
        
        vaultAccountRepository.delete(id1);

        List<VaultAccountItem> items = vaultAccountRepository.findByOwner(userId);
        assertEquals(items.size(), 1);
    }
}

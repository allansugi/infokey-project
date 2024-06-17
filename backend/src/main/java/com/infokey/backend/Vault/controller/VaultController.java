package com.infokey.backend.Vault.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.infokey.backend.Vault.dto.VaultAccountItem;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;
import com.infokey.backend.Vault.service.VaultAccountService;

@RestController
@RequestMapping("/api/v1/vaults")
public class VaultController {

    VaultAccountService vaultService;

    public VaultController(VaultAccountService vaultService) {
        this.vaultService = vaultService;
    }

    @PostMapping
    public ResponseEntity<Void> newVaultAccount(@RequestBody NewVaultAccountItemRequest item, Authentication authentication) {
        String accountId = vaultService.saveVault(item, authentication.getName());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                                .path("/{accountId}")
                                                .buildAndExpand(accountId)
                                                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<VaultAccountItem>> getVaultItems(Authentication authentication) {
        List<VaultAccountItem> items = new ArrayList<>();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaultAccountItem> getVaultAccountInfo(@PathVariable String id) {
        VaultAccountItem item = vaultService.findById(id);
        return ResponseEntity.ok(item);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable String id, @RequestBody VaultAccountItem item) {
        //TODO: process PUT request
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteItem(@PathVariable String id) {
        //TODO: process DELETE request
        return ResponseEntity.noContent().build();
    }
}

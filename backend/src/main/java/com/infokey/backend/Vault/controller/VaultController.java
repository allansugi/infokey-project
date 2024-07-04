package com.infokey.backend.Vault.controller;

import java.net.URI;
import java.util.List;

import com.infokey.backend.Vault.request.UpdateVaultAccountItem;
import com.infokey.backend.Vault.response.ListVaultAccountResponse;
import com.infokey.backend.Vault.response.VaultAccountView;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
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
    public ResponseEntity<ListVaultAccountResponse> getVaultItems(Authentication authentication) {
        List<VaultAccountView> items = vaultService.findAllVaultByOwner(authentication.getName());
        return ResponseEntity.ok(new ListVaultAccountResponse(items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaultAccountItem> getVaultAccountInfo(@PathVariable String id) {
        VaultAccountItem item = vaultService.findById(id);
        return ResponseEntity.ok(item);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody UpdateVaultAccountItem item,
            Authentication authentication
    ) {
        VaultAccountItem updateItem = new VaultAccountItem(
                id,
                item.name(),
                authentication.getName(),
                item.username(),
                item.password()
        );
        vaultService.updateVault(updateItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteItem(@PathVariable String id, Authentication authentication) {
        vaultService.deleteVaultItem(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}

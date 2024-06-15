package com.infokey.backend.Vault;

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



@RestController
@RequestMapping("/api/v1/vaults")
public class VaultController {

    @PostMapping
    public ResponseEntity<Void> newVault(@RequestBody VaultAccountItem item, Authentication authentication) {
        //TODO: process POST request
        System.out.println(authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VaultAccountItem>> getVaultItems(Authentication authentication) {
        List<VaultAccountItem> items = new ArrayList<>();
        return ResponseEntity.ok(items);
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

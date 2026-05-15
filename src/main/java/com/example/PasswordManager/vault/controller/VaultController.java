package com.example.PasswordManager.vault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.vault.dto.VaultDTO;
import com.example.PasswordManager.vault.service.VaultService;

@RestController
@RequestMapping("/vault")
public class VaultController {

    @Autowired
    private VaultService vaultService;

    @CacheEvict(value = "vaultList", key = "#dto.userId")
    @PostMapping
    public ResponseEntity<ApiResponseDTO> addVault(@RequestBody VaultDTO dto) {
        ApiResponseDTO result = vaultService.addVault(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    @Cacheable(value = "vaultList", key = "#userId")
    public ResponseEntity<ApiResponseDTO> getVaultByUserId(@PathVariable Long userId) {
        ApiResponseDTO result = vaultService.getVaultByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "vaultDetails", key = "#id")
    public ResponseEntity<ApiResponseDTO> getPasswordById(@PathVariable Long id) {
        ApiResponseDTO result = vaultService.getPasswordById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/password/{id}")
    @CachePut(value = "vaultDetails", key = "#id")
    @CacheEvict(value = "vaultList", allEntries = true)
    public ResponseEntity<ApiResponseDTO> updatePasswordById(
            @PathVariable Long id,
            @RequestBody VaultDTO dto) {

        ApiResponseDTO result = vaultService.updatePasswordById(id, dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/delete/{id}")
  @Caching(evict = {
        @CacheEvict(value = "vaultDetails", key = "#id"),
        @CacheEvict(value = "vaultList", allEntries = true)
})
    public ResponseEntity<ApiResponseDTO> deleteById(@PathVariable Long id) {
        ApiResponseDTO result = vaultService.deleteById(id);
        return ResponseEntity.ok(result);
    }
}
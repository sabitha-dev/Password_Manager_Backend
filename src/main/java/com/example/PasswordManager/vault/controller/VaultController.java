package com.example.PasswordManager.vault.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<ApiResponseDTO> addVault(@RequestBody VaultDTO dto) {
        ApiResponseDTO result = vaultService.addVault(dto);
        return ResponseEntity.ok(result);
    }

        @GetMapping("/user/{userId}")
        public ResponseEntity<ApiResponseDTO> getVaultByUserId(@PathVariable Long userId) {
            ApiResponseDTO result = vaultService.getVaultByUserId(userId);
            return ResponseEntity.ok(result);
        }

        @GetMapping("/password/{id}")
        public ResponseEntity<ApiResponseDTO> getPasswordById(@PathVariable Long id) {
            ApiResponseDTO result = vaultService.getPasswordById(id);
            return ResponseEntity.ok(result);
        }

    @PatchMapping("/update/{id}")
 
    public ResponseEntity<ApiResponseDTO> updatePasswordById(
            @PathVariable Long id,
            @RequestBody VaultDTO dto) {
System.out.println("cont");
        ApiResponseDTO result = vaultService.updatePasswordById(id, dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/delete/{id}")
   
    public ResponseEntity<ApiResponseDTO> deleteById(@PathVariable Long id) {
        ApiResponseDTO result = vaultService.deleteById(id);
        return ResponseEntity.ok(result);
    }
}
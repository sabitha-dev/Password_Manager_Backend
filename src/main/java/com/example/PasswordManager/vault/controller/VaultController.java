package com.example.PasswordManager.vault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ApiResponseDTO result = vaultService.addVault(dto, email);
        return ResponseEntity.ok(result);
    }

 @GetMapping
public ResponseEntity<ApiResponseDTO> getMyVaultList() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();

    var result = vaultService.getVaultListByEmail(email);

    return ResponseEntity.ok(new ApiResponseDTO("Data fetched successfully!", result));
}

   @GetMapping("/{id}")
public ResponseEntity<ApiResponseDTO> getVaultById(@PathVariable Long id) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    VaultDTO dto = vaultService.getVaultById(id, email);

    return ResponseEntity.ok(new ApiResponseDTO("Password fetched successfully!", dto));
}

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> updateVault(
            @PathVariable Long id,
            @RequestBody VaultDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ApiResponseDTO result = vaultService.updatePasswordById(id, dto, email);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteVault(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ApiResponseDTO result = vaultService.deleteById(id, email);
        return ResponseEntity.ok(result);
    }
}
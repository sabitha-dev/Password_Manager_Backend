package com.example.PasswordManager.vault.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.vault.dto.VaultDTO;
import com.example.PasswordManager.vault.service.VaultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/vault")
public class VaultController {
    @Autowired
    private VaultService vaultService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> addVault(@RequestBody VaultDTO dto) {
        ApiResponseDTO result=vaultService.addVault(dto);
        return ResponseEntity.ok(result);
    }
     @GetMapping("/{userId}")
     public ResponseEntity<ApiResponseDTO>  getVaultByUserId(@RequestParam Long id) {
        ApiResponseDTO result =vaultService.getVaultByUserId(id);
         return ResponseEntity.ok(result);
     }

     @GetMapping("/{id}")
     public ResponseEntity<ApiResponseDTO>  getPasswordById(@RequestParam Long id) {
        ApiResponseDTO result =vaultService.getVaultByUserId(id);
         return ResponseEntity.ok(result);
     }

     @PatchMapping("/password/{id}")
     public ResponseEntity<ApiResponseDTO>  updatePasswordById(@RequestParam Long id ,@RequestBody VaultDTO dto) {
        ApiResponseDTO result =vaultService.updatePasswordById(id,dto);
         return ResponseEntity.ok(result);
     }

       @PatchMapping("/delete/{id}")
     public ResponseEntity<ApiResponseDTO>  deleteById(@RequestParam Long id ) {
        ApiResponseDTO result =vaultService.deleteById(id);
         return ResponseEntity.ok(result);
     }
     
   
    
}

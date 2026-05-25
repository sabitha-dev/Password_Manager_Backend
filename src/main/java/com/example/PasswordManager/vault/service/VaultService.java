package com.example.PasswordManager.vault.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;
import com.example.PasswordManager.utils.aes.AesService;
import com.example.PasswordManager.vault.dto.VaultDTO;
import com.example.PasswordManager.vault.dto.VaultResponseDTO;
import com.example.PasswordManager.vault.model.Vault;
import com.example.PasswordManager.vault.repository.VaultRepository;

@Service
public class VaultService {

    @Autowired
    private VaultRepository vaultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AesService aesService;

    public ApiResponseDTO addVault(VaultDTO dto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vault vault = new Vault();
        vault.setUser(user);
        vault.setSiteUrl(dto.getSiteUrl());
        vault.setAppName(dto.getAppName());
        vault.setLoginUsername(dto.getLoginUsername());
        vault.setEncryptedPassword(aesService.encrypt(dto.getPassword()));
        vault.setNotes(dto.getNotes());
        vault.setCreatedAt(LocalDateTime.now());

        vaultRepository.save(vault);

        return new ApiResponseDTO("saved successfully!");
    }

public List<VaultResponseDTO> getVaultListByEmail(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Vault> vaults = vaultRepository.findAllByUserIdAndIsDeletedFalse(user.getId());

    return vaults.stream().map(v -> {
        VaultResponseDTO dto = new VaultResponseDTO();
        dto.setId(v.getId());
    dto.setSiteUrl(v.getSiteUrl());
        dto.setAppName(v.getAppName());
        dto.setLoginUsername(v.getLoginUsername());
        dto.setEncryptedPassword(v.getEncryptedPassword());
        dto.setNotes(v.getNotes());
        return dto;
    }).toList();
}

public VaultDTO getVaultById(Long id, String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Vault vault = vaultRepository.findByIdAndUserId(id, user.getId())
            .orElseThrow(() -> new RuntimeException("Vault not found"));

    VaultDTO dto = new VaultDTO();
    dto.setId(vault.getId());
    dto.setSiteUrl(vault.getSiteUrl());
    dto.setAppName(vault.getAppName());
    dto.setLoginUsername(vault.getLoginUsername());
    dto.setPassword(aesService.decrypt(vault.getEncryptedPassword()));
    dto.setNotes(vault.getNotes());

    return dto;
}

   
    public ApiResponseDTO updatePasswordById(Long id, VaultDTO dto, String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Vault vault = vaultRepository.findByIdAndUserId(id, user.getId())
            .orElseThrow(() -> new RuntimeException("Vault not found"));

    vault.setAppName(dto.getAppName());
    vault.setLoginUsername(dto.getLoginUsername());
    vault.setNotes(dto.getNotes());

    if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
        vault.setEncryptedPassword(aesService.encrypt(dto.getPassword()));
    }

    vaultRepository.save(vault);

    return new ApiResponseDTO("Vault updated successfully!");
}

   
    public ApiResponseDTO deleteById(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vault vault = vaultRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        vault.setDeleted(true);
        vaultRepository.save(vault);

        return new ApiResponseDTO("Deleted successfully!");
    }
}

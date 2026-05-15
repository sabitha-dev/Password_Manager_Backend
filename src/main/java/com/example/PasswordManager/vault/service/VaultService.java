package com.example.PasswordManager.vault.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;
import com.example.PasswordManager.vault.dto.VaultDTO;
import com.example.PasswordManager.vault.model.Vault;
import com.example.PasswordManager.vault.repository.VaultRepository;

@Service
public class VaultService {
    @Autowired
    private VaultRepository vaultRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponseDTO addVault(VaultDTO dto) {
User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

Vault vault = new Vault();
vault.setUserId(user);  
        vault.setAppName(dto.getAppName());
        vault.setLoginUsername(dto.getLoginUsername());
        vault.setEncryptedPassword(dto.getPassword());
 vault.setNotes(dto.getNotes());
    vault.setCreatedAt(LocalDateTime.now());
      vaultRepository.save(vault);

        return new ApiResponseDTO("saved successfully!");
    }

    public ApiResponseDTO getVaultByUserId(Long id) {
        Optional<User> isExistUser = userRepository.findById(id);
        if (!isExistUser.isPresent()) {
            return new ApiResponseDTO("User not found", isExistUser);
        }
        List<Vault> result = vaultRepository.findAllByUserId(id);
        return new ApiResponseDTO("Data fetched succesfully!", result);
    }

    public ApiResponseDTO getPasswordById(Long id) {
        Optional<Vault> result = vaultRepository.findById(id);
        return new ApiResponseDTO("Data fetched succesfully!", result);
    }

     public ApiResponseDTO updatePasswordById(Long id,VaultDTO dto) {
       Vault vault = vaultRepository.findById(id).orElse(null);
        if (vault==null) {
            return new ApiResponseDTO("Data not found", null);
        }
      vault.setEncryptedPassword(dto.getPassword());
Vault result=vaultRepository.save(vault);
        return new ApiResponseDTO("Password updated succesfully!", result);
    }

     public ApiResponseDTO deleteById(Long id) {
       Vault vault = vaultRepository.findById(id).orElse(null);
        if (vault==null) {
            return new ApiResponseDTO("Data not found", null);
        }
      vault.setDeleted(true);
Vault result=vaultRepository.save(vault);
        return new ApiResponseDTO("Deleted succesfully!", result);
    }
}

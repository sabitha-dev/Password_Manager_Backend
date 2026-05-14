package com.example.PasswordManager.vault.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;
import com.example.PasswordManager.vault.dto.VaultDTO;
import com.example.PasswordManager.vault.model.Vault;
import com.example.PasswordManager.vault.repository.VaultRepository;

public class VaultService {
    @Autowired
    private VaultRepository vaultRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponseDTO addVault(VaultDTO dto) {

        Vault vault = new Vault();
        vault.setAppName(dto.getAppName());
        vault.setLoginUsername(dto.getLoginUsername());
        vault.setEncryptedPassword(dto.getPassword());

        Vault savedPassword = vaultRepository.save(vault);

        return new ApiResponseDTO("saved successfully!", savedPassword);
    }

    public ApiResponseDTO getVaultByUserId(Long id) {
        Optional<User> isExistUser = userRepository.findById(id);
        if (!isExistUser.isPresent()) {
            return new ApiResponseDTO("User not found", isExistUser);
        }
        List<Vault> result = vaultRepository.findAllByUserID(id);
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

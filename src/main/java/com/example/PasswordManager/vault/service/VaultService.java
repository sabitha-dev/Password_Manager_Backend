package com.example.PasswordManager.vault.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "vaultList", key = "#dto.userId")
    public ApiResponseDTO addVault(VaultDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vault vault = new Vault();
        vault.setUser(user);
        vault.setAppName(dto.getAppName());
        vault.setLoginUsername(dto.getLoginUsername());
        String encryptedPassword = aesService.encrypt(dto.getPassword());

        vault.setEncryptedPassword(encryptedPassword);
        vault.setNotes(dto.getNotes());
        vault.setCreatedAt(LocalDateTime.now());
        vaultRepository.save(vault);

        return new ApiResponseDTO("saved successfully!");
    }

    @Cacheable(value = "vaultList", key = "#id")
    public ApiResponseDTO getVaultByUserId(Long id) {

        List<Vault> vaults = vaultRepository.findAllByUserId(id);

        List<VaultResponseDTO> result = vaults.stream().map(v -> {
            VaultResponseDTO dto = new VaultResponseDTO();
            dto.setId(v.getId());
            dto.setAppName(v.getAppName());
            dto.setLoginUsername(v.getLoginUsername());
            dto.setEncryptedPassword(v.getEncryptedPassword());
            dto.setNotes(v.getNotes());
            return dto;
        }).toList();
        System.out.println(result + "Res");
        return new ApiResponseDTO("Data fetched successfully!", result);
    }

    @Cacheable(value = "vaultDetails", key = "#id")
    public ApiResponseDTO getPasswordById(Long id) {
        System.out.println("seriev");
        Vault vault = vaultRepository.findById(id).orElse(null);

        if (vault == null) {
            return new ApiResponseDTO("Vault not found", null);
        }

        String decryptedPassword = aesService.decrypt(vault.getEncryptedPassword());

        VaultDTO dto = new VaultDTO();
        dto.setId(vault.getId());
        dto.setAppName(vault.getAppName());
        dto.setLoginUsername(vault.getLoginUsername());
        dto.setPassword(decryptedPassword);
        dto.setNotes(vault.getNotes());

        return new ApiResponseDTO("Password fetched successfully!", dto);
    }

    @CachePut(value = "vaultDetails", key = "#id")
    @CacheEvict(value = "vaultList", allEntries = true)
    public ApiResponseDTO updatePasswordById(Long id, VaultDTO dto) {
        Vault vault = vaultRepository.findById(id).orElse(null);
        if (vault == null) {
            return new ApiResponseDTO("Data not found", null);
        }
        String encryptedPassword = aesService.encrypt(dto.getPassword());
        vault.setEncryptedPassword(encryptedPassword);
        vaultRepository.save(vault);
        return new ApiResponseDTO("Password updated succesfully!");
    }

   @Caching(evict = {
        @CacheEvict(value = "vaultDetails", key = "#id"),
        @CacheEvict(value = "vaultList", allEntries = true)
})
public ApiResponseDTO deleteById(Long id) {

    Optional<Vault> optionalVault = vaultRepository.findById(id);

    if (optionalVault.isEmpty()) {
        return new ApiResponseDTO("Data not found", null);
    }

    Vault vault = optionalVault.get(); 

    vault.setDeleted(true);
 vaultRepository.save(vault);

    return new ApiResponseDTO("Deleted successfully!");
}
}

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
    @CrossOrigin(origins = "*")
    public class VaultController {

        @Autowired
        private VaultService vaultService;
private String getEmail() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated()
            || auth.getName().equals("anonymousUser")) {
        return "extension-user";
    }

    return auth.getName();
}
      @PostMapping
public ResponseEntity<ApiResponseDTO> addVault(@RequestBody VaultDTO dto) {

    String email = getEmail();

    ApiResponseDTO result = vaultService.addVault(dto, email);
    return ResponseEntity.ok(result);
}

@GetMapping
public ResponseEntity<ApiResponseDTO> getMyVaultList() {

    String email = getEmail();

    var result = vaultService.getVaultListByEmail(email);

    return ResponseEntity.ok(new ApiResponseDTO("Data fetched successfully!", result));
}

   @GetMapping("/{id}")
public ResponseEntity<ApiResponseDTO> getVaultById(@PathVariable Long id) {

    String email = getEmail();

    VaultDTO dto = vaultService.getVaultById(id, email);

    return ResponseEntity.ok(new ApiResponseDTO("Password fetched successfully!", dto));
}

       @PatchMapping("/{id}")
public ResponseEntity<ApiResponseDTO> updateVault(
        @PathVariable Long id,
        @RequestBody VaultDTO dto) {

    String email = getEmail();

    ApiResponseDTO result = vaultService.updatePasswordById(id, dto, email);
    return ResponseEntity.ok(result);
}

        @DeleteMapping("/{id}")
public ResponseEntity<ApiResponseDTO> deleteVault(@PathVariable Long id) {

    String email = getEmail();

    ApiResponseDTO result = vaultService.deleteById(id, email);
    return ResponseEntity.ok(result);
}
    }
    package com.example.PasswordManager.vault.repository;

    import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import com.example.PasswordManager.vault.model.Vault;

   @Repository
public interface VaultRepository extends JpaRepository<Vault, Long> {

    List<Vault> findAllByUserIdAndIsDeletedFalse(Long userId);

    Optional<Vault> findByIdAndUserId(Long id, Long userId);
}

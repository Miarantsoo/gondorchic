package com.dil.gondorchic.repository;

import com.dil.gondorchic.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPseudo(String pseudo);
}

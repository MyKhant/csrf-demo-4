package com.example.csrfdemo4.dao;

import com.example.csrfdemo4.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}

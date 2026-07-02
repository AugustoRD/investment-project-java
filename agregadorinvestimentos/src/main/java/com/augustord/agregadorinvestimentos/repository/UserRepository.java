package com.augustord.agregadorinvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.augustord.agregadorinvestimentos.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
}

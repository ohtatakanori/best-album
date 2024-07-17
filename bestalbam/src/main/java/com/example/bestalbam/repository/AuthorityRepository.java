package com.example.bestalbam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestalbam.model.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    Optional<Authority> findByUserId(Long userId);
}

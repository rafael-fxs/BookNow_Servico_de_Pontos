package com.booknow.pontos.domain.repository;

import com.booknow.pontos.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
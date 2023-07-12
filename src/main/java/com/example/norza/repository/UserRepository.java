package com.example.norza.repository;

import com.example.norza.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;


public interface UserRepository extends JpaRepository<User, Id> {
    User findByEmail(String email);
}

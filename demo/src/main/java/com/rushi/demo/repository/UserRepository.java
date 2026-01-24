package com.rushi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

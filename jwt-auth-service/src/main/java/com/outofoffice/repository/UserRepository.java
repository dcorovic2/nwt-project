package com.outofoffice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outofoffice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByUsername(String username);
  User findByPasswordAndUsername(String password, String username);
  User findById(Long id);

  @Transactional
  void deleteByUsername(String username);

}

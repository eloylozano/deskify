package com.deskify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    // Métodos para excluir el ID actual
    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

}

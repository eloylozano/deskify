package com.deskify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

   
    Optional<Status> findByName(String Name);

}

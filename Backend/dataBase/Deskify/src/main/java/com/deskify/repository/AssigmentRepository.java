package com.deskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Assigment;

@Repository
public interface AssigmentRepository extends JpaRepository<Assigment, Long>  {

}

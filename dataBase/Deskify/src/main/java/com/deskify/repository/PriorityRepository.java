package com.deskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long>  {

}

package com.deskify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Plan;
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>  {

    Optional<Plan> findByName(String planName);

}

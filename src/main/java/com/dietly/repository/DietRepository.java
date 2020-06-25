package com.dietly.repository;

import com.dietly.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {
}



package com.dietly.repository;

import com.dietly.model.DietCalories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietCaloriesRepository extends JpaRepository<DietCalories, Integer> {
}

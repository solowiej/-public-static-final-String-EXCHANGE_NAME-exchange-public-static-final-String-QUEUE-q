package com.dietly.repository;

import com.dietly.model.DietOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietOptionRepository extends JpaRepository<DietOption, Integer> {
}

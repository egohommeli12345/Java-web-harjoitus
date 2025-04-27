package com.java_web.vaadin.repository;

import com.java_web.vaadin.entities.NutritionalValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalValueRepository extends JpaRepository<NutritionalValue, Integer> {
}

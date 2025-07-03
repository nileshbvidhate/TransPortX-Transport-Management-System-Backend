package com.nil.transportx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nil.transportx.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}

package com.nil.transportx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nil.transportx.entity.SubDistrict;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer> {

}

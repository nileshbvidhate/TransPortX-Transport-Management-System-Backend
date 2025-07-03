package com.nil.transportx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nil.transportx.entity.TransportOffice;

@Repository
public interface TransportOfficeRepository extends JpaRepository<TransportOffice, Integer> {

}

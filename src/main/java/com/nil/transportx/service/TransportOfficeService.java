package com.nil.transportx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nil.transportx.entity.TransportOffice;
import com.nil.transportx.repository.TransportOfficeRepository;

@Service
public class TransportOfficeService {
	
	private TransportOfficeRepository officeRepo;
	
	public TransportOfficeService(TransportOfficeRepository officeRepo) {
		this.officeRepo = officeRepo;
	}

	public List<TransportOffice> getAllOffices() {
		
		 List<TransportOffice> offices = officeRepo.findAll();
		
		return offices;
	}

	public TransportOffice addOffice(TransportOffice office) {
		TransportOffice savedOffice = officeRepo.save(office);
		
		return savedOffice;
	}
	
}

package com.nil.transportx.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nil.transportx.entity.TransportOffice;
import com.nil.transportx.service.TransportOfficeService;

@RestController
public class TransportOfficeController {
	
	private TransportOfficeService officeService;
	

	public TransportOfficeController(TransportOfficeService officeService)
	{
		this.officeService = officeService;
	}
	
	@GetMapping("/office")
	public List<TransportOffice> getAllOffices()
	{
		List<TransportOffice> offices = officeService.getAllOffices();
		
		return offices;
	}
	
	@PostMapping("/office")
	public TransportOffice addOffice(@RequestBody TransportOffice office)
	{
		
		TransportOffice savedOffice = officeService.addOffice(office);
		
		return savedOffice;
	}
}

package com.nil.transportx.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nil.transportx.entity.District;
import com.nil.transportx.service.DistrictService;

@RestController
public class DistrictController {

	private final DistrictService districtService;

	public DistrictController(DistrictService districtService) {
		this.districtService = districtService;
	}

	@GetMapping("/district")
	public ResponseEntity<List<District>> getAllDistricts() {
		List<District> districts = districtService.getAllDistricts();

		if (districts != null && !districts.isEmpty()) {
			return ResponseEntity.ok(districts);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/district/{distId}")
	public ResponseEntity<District> getDistrict(@PathVariable Integer distId)
	{
		Optional<District> district = districtService.getDistrict(distId);
		if(district.isPresent()) {
			return ResponseEntity.ok(district.get());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/district")
	public ResponseEntity<?> addDistrict(@RequestBody District district)
	{
		District savedDistrict = districtService.addDistrict(district);
		if(savedDistrict != null)
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(savedDistrict);
		}
		else{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save district : Please try again");
		}
	}
	
	@PutMapping("district/{distId}")
	public ResponseEntity<?> updateDistrict(@PathVariable Integer distId, @RequestBody District newDistrict)
	{
		Optional<District> updatedDistrict = districtService.updateDistrict(distId, newDistrict);
		
		if(updatedDistrict.isPresent())
		{
			return ResponseEntity.ok(updatedDistrict.get());
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("District is not found");
		}
	}
	
	@DeleteMapping("/district/{distId}")
	public ResponseEntity<?> deleteDistrict(@PathVariable Integer distId)
	{
		boolean isDeleted = districtService.deleteDistrict(distId);
		if(isDeleted)
		{
			return ResponseEntity.noContent().build(); 
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("District not found");
		}
		
	}

}

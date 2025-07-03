package com.nil.transportx.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nil.transportx.requestdto.SubDistrictRequestDTO;
import com.nil.transportx.responsedto.SubDistrictResponseDTO;
import com.nil.transportx.service.SubDistrictService;

import jakarta.validation.Valid;

@RestController
public class SubDistrictController {

	private final SubDistrictService subDistrictService;

	public SubDistrictController(SubDistrictService subDistrictService) {
		this.subDistrictService = subDistrictService;
	}

	@GetMapping("/subdistrict")
	public ResponseEntity<List<SubDistrictResponseDTO>> getAllSubDistricts() {
		List<SubDistrictResponseDTO> subDistList = subDistrictService.getAllSubDistricts();

		return subDistList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subDistList);
	}

	@GetMapping("/subdistrict/{subDistId}")
	public ResponseEntity<SubDistrictResponseDTO> getSubDistrictById(@PathVariable Integer subDistId) {
		SubDistrictResponseDTO subDist = subDistrictService.getSubDistrictById(subDistId);

		return ResponseEntity.ok(subDist);
	}

	@PostMapping("/subdistrict")
	public ResponseEntity<SubDistrictResponseDTO> addSubDistrict(@Valid @RequestBody SubDistrictRequestDTO subDistrictRequestDTO) {
		SubDistrictResponseDTO savedSubDist = subDistrictService.addSubDistrict(subDistrictRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSubDist);
	}

	@PutMapping("subdistrict/{subDistId}")
	public ResponseEntity<SubDistrictResponseDTO> updateSubDistrict(@PathVariable Integer subDistId, @RequestBody SubDistrictRequestDTO subDistrictRequestDTO) {
		return ResponseEntity.ok(subDistrictService.updateSubDistrict(subDistId,subDistrictRequestDTO ));
	}

	@DeleteMapping("subdistrict/{subDistId}")
	public ResponseEntity<?> deleteSubDistrict(@PathVariable Integer subDistId) {
		subDistrictService.deleteSubDistrict(subDistId);
		return ResponseEntity.noContent().build();
	}
}

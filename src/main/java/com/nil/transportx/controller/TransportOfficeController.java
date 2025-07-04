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
import com.nil.transportx.requestdto.TransportOfficeRequestDTO;
import com.nil.transportx.responsedto.TransportOfficeResponseDTO;
import com.nil.transportx.service.TransportOfficeService;
import jakarta.validation.Valid;

@RestController
public class TransportOfficeController {
	
	private final TransportOfficeService officeService;
	
	public TransportOfficeController(TransportOfficeService officeService)
	{
		this.officeService = officeService;
	}
	
	@PostMapping("/office")
	public ResponseEntity<TransportOfficeResponseDTO> addOffice(@Valid @RequestBody TransportOfficeRequestDTO officeRequestDTO )
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(officeService.addOffice(officeRequestDTO));
	}
	
	@GetMapping("/offices")
	public ResponseEntity<List<TransportOfficeResponseDTO>> getAllOffices()
	{
		List<TransportOfficeResponseDTO> offices = officeService.getAllOffices();
		
		return (offices.isEmpty() ? ResponseEntity.noContent().build(): ResponseEntity.ok(offices));
	}
	
	@GetMapping("/office/{officeId}")
	public ResponseEntity<TransportOfficeResponseDTO> getOfficeById(@PathVariable Integer officeId)
	{
		return ResponseEntity.ok(officeService.getOfficeById(officeId));
	}
	
	@PutMapping("/office/{officeId}")
	public ResponseEntity<TransportOfficeResponseDTO> updateOffice(@PathVariable Integer officeId, @Valid @RequestBody TransportOfficeRequestDTO officeRequestDTO)
	{
		return ResponseEntity.ok(officeService.updateOffice(officeId, officeRequestDTO));
	}
	
	@DeleteMapping("/office/{officeId}")
	public ResponseEntity<?> deleteOffice(@PathVariable Integer officeId)
	{
		officeService.deleteOffice(officeId);
		return ResponseEntity.noContent().build();
	}
}

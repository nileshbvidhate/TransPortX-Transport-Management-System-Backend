package com.nil.transportx.requestdto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
	
	@NotBlank(message = "Street is required...")
	private String street;
	
	@NotBlank(message = "City is required...")
	private String city;
	
	@NotNull(message = "District should be selected : subDistId should not be null")
	private Integer subDistId;
}

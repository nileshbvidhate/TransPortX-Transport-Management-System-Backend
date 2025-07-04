package com.nil.transportx.requestdto;


import org.springframework.stereotype.Component;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportOfficeRequestDTO {
	
	@NotBlank(message = "Office name is required")
	private String officeName;

	@Valid
	private AddressRequestDTO addressRequestDTO;

	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Contact must be a 10-digit number")
	private String contact;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
}

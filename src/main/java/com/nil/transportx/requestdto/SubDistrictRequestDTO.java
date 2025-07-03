package com.nil.transportx.requestdto;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SubDistrictRequestDTO {
	
	@NotBlank(message = "SubDistrict Name is Required...")
	private String subDistName;
	
	@NotBlank(message = "SubDistrict Pincode is Required...")
	@Pattern(regexp = "\\d{6}", message = "Pincode must be a 6-digit number")
	private String pinCode;
	
	@NotNull(message = "Selecting the District is neccessary...ID should not be null")
	private Integer distId;
}

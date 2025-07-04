package com.nil.transportx.responsedto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
	private Integer addressId;
	private String street;
	private String city;
    private String subDistName;
    private String pincode;
    private String distName;
    private String rtoCode;
}

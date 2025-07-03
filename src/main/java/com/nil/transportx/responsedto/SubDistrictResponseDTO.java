package com.nil.transportx.responsedto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SubDistrictResponseDTO {
	private Integer subDistId;
    private String subDistName;
    private String pincode;
    private String distName;
    private String rtoCode;
}

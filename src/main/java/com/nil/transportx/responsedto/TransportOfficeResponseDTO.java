package com.nil.transportx.responsedto;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportOfficeResponseDTO {
	private Integer officeId;
	private String officeName;
	private String contact;
	private String email;
	private String street;
	private String city;
    private String subDistName;
    private String pincode;
    private String distName;
    private String rtoCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime registeredAt;
}

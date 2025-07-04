package com.nil.transportx.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nil.transportx.entity.TransportOffice;
import com.nil.transportx.responsedto.AddressResponseDTO;
import com.nil.transportx.responsedto.TransportOfficeResponseDTO;

@Component
public class TransportOfficeMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressMapper addressMapper;


	public TransportOfficeResponseDTO mapToOfficeResponseDTO(TransportOffice office) {
		
		TransportOfficeResponseDTO officeResponseDTO = modelMapper.map(office, TransportOfficeResponseDTO.class);;

		AddressResponseDTO addressResponseDTO = addressMapper.mapToAddressResponseDTO(office.getAddress());
		
		officeResponseDTO.setStreet(addressResponseDTO.getStreet());
		officeResponseDTO.setCity(addressResponseDTO.getCity());
		officeResponseDTO.setSubDistName(addressResponseDTO.getSubDistName());		
		officeResponseDTO.setPincode(addressResponseDTO.getPincode());
		officeResponseDTO.setDistName(addressResponseDTO.getDistName());
		officeResponseDTO.setRtoCode(addressResponseDTO.getRtoCode());

		return officeResponseDTO;
	}
}

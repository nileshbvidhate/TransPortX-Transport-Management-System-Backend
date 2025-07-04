package com.nil.transportx.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nil.transportx.entity.Address;
import com.nil.transportx.responsedto.AddressResponseDTO;
import jakarta.annotation.PostConstruct;

@Component
public class AddressMapper {

	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	public void init() {
		modelMapper.typeMap(Address.class, AddressResponseDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getSubDistrict().getSubDistName(), AddressResponseDTO::setSubDistName);
			mapper.map(src -> src.getSubDistrict().getPinCode(), AddressResponseDTO::setPincode);
			mapper.map(src -> src.getSubDistrict().getDistrict().getDistName(), AddressResponseDTO::setDistName);
			mapper.map(src -> src.getSubDistrict().getDistrict().getRtoCode(), AddressResponseDTO::setRtoCode);
		});
	}

	public AddressResponseDTO mapToAddressResponseDTO(Address address) {
		return modelMapper.map(address,AddressResponseDTO.class);
	}
}

package com.nil.transportx.mapper;

import org.springframework.stereotype.Component;

import com.nil.transportx.entity.SubDistrict;
import com.nil.transportx.responsedto.SubDistrictResponseDTO;

@Component
public class SubDistrictMapper {

	public SubDistrictResponseDTO mapToResponseDTO(SubDistrict subDistrict) {
		return new SubDistrictResponseDTO(subDistrict.getSubDistId(), subDistrict.getSubDistName(),
				subDistrict.getPinCode(), subDistrict.getDistrict().getDistName(),
				subDistrict.getDistrict().getRtoCode());
	}

}

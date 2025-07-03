package com.nil.transportx.service;

import java.util.List;
import com.nil.transportx.requestdto.SubDistrictRequestDTO;
import com.nil.transportx.responsedto.SubDistrictResponseDTO;

public interface SubDistrictService {

	List<SubDistrictResponseDTO> getAllSubDistricts();
	SubDistrictResponseDTO getSubDistrictById(Integer subDistId);
	SubDistrictResponseDTO addSubDistrict(SubDistrictRequestDTO subDistrictRequestDTO);
	SubDistrictResponseDTO updateSubDistrict(Integer subDistId, SubDistrictRequestDTO subDistrictRequestDTO);
	void deleteSubDistrict(Integer subDistId);
	
}

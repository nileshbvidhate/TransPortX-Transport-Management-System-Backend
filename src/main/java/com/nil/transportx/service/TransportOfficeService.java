package com.nil.transportx.service;

import java.util.List;

import com.nil.transportx.requestdto.TransportOfficeRequestDTO;
import com.nil.transportx.responsedto.TransportOfficeResponseDTO;

public interface TransportOfficeService {

	TransportOfficeResponseDTO addOffice(TransportOfficeRequestDTO officeRequestDTO);
	List<TransportOfficeResponseDTO> getAllOffices();
	TransportOfficeResponseDTO getOfficeById(Integer officeId);
	TransportOfficeResponseDTO updateOffice(Integer officeId, TransportOfficeRequestDTO officeRequestDTO);
	void deleteOffice(Integer officeId);

}

package com.nil.transportx.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import com.nil.transportx.entity.Address;
import com.nil.transportx.entity.SubDistrict;
import com.nil.transportx.entity.TransportOffice;
import com.nil.transportx.exceptions.DatabaseOperationException;
import com.nil.transportx.exceptions.InvalidDataException;
import com.nil.transportx.exceptions.ResourceNotFoundException;
import com.nil.transportx.mapper.TransportOfficeMapper;
import com.nil.transportx.repository.SubDistrictRepository;
import com.nil.transportx.repository.TransportOfficeRepository;
import com.nil.transportx.requestdto.AddressRequestDTO;
import com.nil.transportx.requestdto.TransportOfficeRequestDTO;
import com.nil.transportx.responsedto.TransportOfficeResponseDTO;

@Service
public class TransportOfficeServiceImpl implements TransportOfficeService {

	private final TransportOfficeRepository officeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubDistrictRepository subDisrtrictRepo;

	@Autowired
	private TransportOfficeMapper officeMapper;

	public TransportOfficeServiceImpl(TransportOfficeRepository officeRepo) {
		this.officeRepo = officeRepo;
	}

	@Override
	public TransportOfficeResponseDTO addOffice(TransportOfficeRequestDTO officeRequestDTO) {

		AddressRequestDTO addressRequestDTO = officeRequestDTO.getAddressRequestDTO();

		SubDistrict subDistrict = subDisrtrictRepo.findById(addressRequestDTO.getSubDistId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"SubDistrict Not found With ID : " + officeRequestDTO.getAddressRequestDTO().getSubDistId()));

		Address address = new Address();

		modelMapper.map(addressRequestDTO, address);
		address.setSubDistrict(subDistrict);

		TransportOffice office = new TransportOffice();

		modelMapper.map(officeRequestDTO, office);
		office.setAddress(address);

		try {
			TransportOffice saveOffice = officeRepo.save(office);
			return officeMapper.mapToOfficeResponseDTO(saveOffice);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataException("Invalid data or constraint violated: " + e.getRootCause().getMessage());
		} catch (TransactionSystemException e) {
			throw new InvalidDataException("Validation failed: " + e.getMostSpecificCause().getMessage());
		} catch (Exception e) {
			throw new DatabaseOperationException(
					"An unexpected error occurred while adding the office : " + e.getMessage());
		}

	}

	@Override
	public List<TransportOfficeResponseDTO> getAllOffices() {

		List<TransportOffice> allOffices = officeRepo.findAll();

		return allOffices.stream().map(officeMapper::mapToOfficeResponseDTO).collect(Collectors.toList());
	}

	@Override
	public TransportOfficeResponseDTO getOfficeById(Integer officeId) {
		TransportOffice office = officeRepo.findById(officeId)
				.orElseThrow(() -> new ResourceNotFoundException("Office not found with ID : " + officeId));

		return officeMapper.mapToOfficeResponseDTO(office);
	}

	@Override
	public TransportOfficeResponseDTO updateOffice(Integer officeId, TransportOfficeRequestDTO officeRequestDTO) {

		TransportOffice existingOffice = officeRepo.findById(officeId)
				.orElseThrow(() -> new ResourceNotFoundException("Office not found with ID : " + officeId));

		Address existingAddress = existingOffice.getAddress();
		modelMapper.map(officeRequestDTO, existingOffice);

		AddressRequestDTO addressRequestDTO = officeRequestDTO.getAddressRequestDTO();

		SubDistrict subDistrict = subDisrtrictRepo.findById(addressRequestDTO.getSubDistId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"SubDistrict Not found With ID : " + officeRequestDTO.getAddressRequestDTO().getSubDistId()));

		existingAddress.setSubDistrict(subDistrict);

		modelMapper.map(officeRequestDTO.getAddressRequestDTO(), existingAddress);

		existingOffice.setAddress(existingAddress);

		try {
			return officeMapper.mapToOfficeResponseDTO(officeRepo.save(existingOffice));
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataException("Invalid data or constraint violated: " + e.getRootCause().getMessage());
		} catch (TransactionSystemException e) {
			throw new InvalidDataException("Validation failed: " + e.getMostSpecificCause().getMessage());
		} catch (Exception e) {
			throw new DatabaseOperationException(
					"An unexpected error occurred while updating office details: " + e.getMessage());
		}

	}

	@Override
	public void deleteOffice(Integer officeId) {
		TransportOffice office = officeRepo.findById(officeId)
				.orElseThrow(() -> new ResourceNotFoundException("Office not found with ID :" + officeId));

		try {
			officeRepo.delete(office);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataException("Cannot delete office: It is referenced by other records");
		} catch (Exception e) {
			throw new DatabaseOperationException(
					"An unexpected error occurred while deleting office: " + e.getMessage());
		}
	}

}

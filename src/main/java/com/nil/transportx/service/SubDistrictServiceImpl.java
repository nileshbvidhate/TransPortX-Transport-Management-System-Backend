package com.nil.transportx.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import com.nil.transportx.entity.District;
import com.nil.transportx.entity.SubDistrict;
import com.nil.transportx.exceptions.DatabaseOperationException;
import com.nil.transportx.exceptions.InvalidDataException;
import com.nil.transportx.exceptions.ResourceNotFoundException;
import com.nil.transportx.mapper.SubDistrictMapper;
import com.nil.transportx.repository.DistrictRepository;
import com.nil.transportx.repository.SubDistrictRepository;
import com.nil.transportx.requestdto.SubDistrictRequestDTO;
import com.nil.transportx.responsedto.SubDistrictResponseDTO;

@Service
public class SubDistrictServiceImpl implements SubDistrictService {

	private final SubDistrictRepository subDistrictRepo;

	private final DistrictRepository districtRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubDistrictMapper subDistrictMapper;

	public SubDistrictServiceImpl(SubDistrictRepository subDistrictRepo, DistrictRepository districtRepo) {
		this.subDistrictRepo = subDistrictRepo;
		this.districtRepo = districtRepo;
	}

	@Override
	public List<SubDistrictResponseDTO> getAllSubDistricts() {

		List<SubDistrict> subDistList = subDistrictRepo.findAll();

		return subDistList.stream().map(subDistrictMapper::mapToResponseDTO).collect(Collectors.toList());

	}

	@Override
	public SubDistrictResponseDTO getSubDistrictById(Integer subDistId) {
		SubDistrict subDist = subDistrictRepo.findById(subDistId)
				.orElseThrow(() -> new ResourceNotFoundException("SubDistrict not found with ID: " + subDistId));

		return subDistrictMapper.mapToResponseDTO(subDist);
	}

	@Override
	public SubDistrictResponseDTO addSubDistrict(SubDistrictRequestDTO subDistrictRequestDTO) {

		District district = districtRepo.findById(subDistrictRequestDTO.getDistId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"District not found with ID: " + subDistrictRequestDTO.getDistId()));

		SubDistrict subDist = new SubDistrict();

		modelMapper.map(subDistrictRequestDTO, subDist);
		subDist.setDistrict(district);

		try {
			SubDistrict saveSubDist = subDistrictRepo.save(subDist);
			return subDistrictMapper.mapToResponseDTO(saveSubDist);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataException("Invalid data or constraint violated: " + e.getRootCause().getMessage());
		} catch (TransactionSystemException e) {
			throw new InvalidDataException("Validation failed: " + e.getMostSpecificCause().getMessage());
		} catch (Exception e) {
			throw new InvalidDataException("An unexpected error occurred while saving SubDistrict: " + e.getMessage());
		}

	}

	@Override
	public SubDistrictResponseDTO updateSubDistrict(Integer subDistId, SubDistrictRequestDTO subDistrictRequestDTO) {

		SubDistrict existingSubDistrict = subDistrictRepo.findById(subDistId)
				.orElseThrow(() -> new ResourceNotFoundException("SubDistrict not found with ID: " + subDistId));

		District district = districtRepo.findById(subDistrictRequestDTO.getDistId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"District not Found with ID :" + subDistrictRequestDTO.getDistId()));

		modelMapper.map(subDistrictRequestDTO, existingSubDistrict);
		existingSubDistrict.setDistrict(district);

		try {
			SubDistrict updatedSubDistrict = subDistrictRepo.save(existingSubDistrict);
			return subDistrictMapper.mapToResponseDTO(updatedSubDistrict);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataException("Invalid data or constraint violated: " + e.getRootCause().getMessage());
		} catch (TransactionSystemException e) {
			throw new InvalidDataException("Validation failed: " + e.getMostSpecificCause().getMessage());
		} catch (Exception e) {
			throw new InvalidDataException("An unexpected error occurred while saving SubDistrict: " + e.getMessage());
		}
	}

	@Override
	public void deleteSubDistrict(Integer subDistId) {
		SubDistrict subDistrict = subDistrictRepo.findById(subDistId)
				.orElseThrow(() -> new ResourceNotFoundException("SubDistrict not found with ID: " + subDistId));

		try {
			subDistrictRepo.delete(subDistrict);
		} catch (Exception e) {
			throw new DatabaseOperationException("Failed to delete SubDistrict with ID: " + subDistId);
		}
	}

}

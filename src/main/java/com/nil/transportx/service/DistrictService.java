package com.nil.transportx.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.nil.transportx.entity.District;
import com.nil.transportx.repository.DistrictRepository;

@Service
public class DistrictService {

	private final DistrictRepository districtRepo;

	public DistrictService(DistrictRepository districtRepo) {
		this.districtRepo = districtRepo;
	}

	public List<District> getAllDistricts() {

		List<District> districts = districtRepo.findAll();

		return districts;
	}

	public Optional<District> getDistrict(Integer distId) {

		Optional<District> district = districtRepo.findById(distId);

		return district;
	}

	public District addDistrict(District district) {

		District savedDistrict = districtRepo.save(district);

		return savedDistrict;
	}

	public Optional<District> updateDistrict(Integer distId, District newDistrict) {

		Optional<District> existedDistrict = districtRepo.findById(distId);
		// We can use .map() and functional api

		if (existedDistrict.isPresent()) {
			District existing = existedDistrict.get();

			existing.setDistName(newDistrict.getDistName());
			existing.setRtoCode(newDistrict.getRtoCode());

			District updatedDistrict = districtRepo.save(existing);

			return Optional.of(updatedDistrict);
		} else {
			return Optional.empty();
		}

//		return districtRepo.findById(distId).map(existing -> {
//		            existing.setDistName(newDistrict.getDistName());
//		            existing.setRtoCode(newDistrict.getRtoCode());
//		            return districtRepo.save(existing);
//		}

	}

	public boolean deleteDistrict(Integer distId) {
		 
		boolean bFlag = false;
		if(districtRepo.findById(distId).isPresent())
		{
			districtRepo.deleteById(distId);
			bFlag = true;
			
		}
		
		return bFlag;
	}

}

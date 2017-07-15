package com.care.patientcare.homeremedy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.patientcare.homeremedy.domain.HomeRemedy;
import com.care.patientcare.homeremedy.dto.HomeRemedyDto;
import com.care.patientcare.homeremedy.repository.HomeRemedyRepository;

/**
 * @author venkatg
 * Home Remedy service
 */
@Service
public class HomeRemedyService extends BaseService {

	@Autowired
	HomeRemedyRepository homeRemedyRepository;
	
	
	/**
	 * Service to return the list of Home Remedy based on the category
	 * @param category
	 * @return
	 */
	public List<HomeRemedyDto> getHomeRemedies(String category) {
		List<HomeRemedy> homeRemedies;
		homeRemedies = homeRemedyRepository.findAll();
		if ("TOP".equalsIgnoreCase(category))
		{
			long noOfTopCentres = 1; // TODO from Config server
			homeRemedies = homeRemedies.stream().limit(noOfTopCentres).collect(Collectors.toList());
		}
		
		// Convert domain to dto and return
		return populateHomeRemedyDto(homeRemedies);
	}	

	
	/**
     * Convert HomeRemedy domain to HomeRemedyDto 
     * @param List of homeRemedy
     * @return a list of HomeRemedy DTO
     */
    private List populateHomeRemedyDto(List homeRemedies) {
    	homeRemedies.forEach(d -> convert(d, HomeRemedyDto.class));
    	return homeRemedies;
    } 
	
	/**
	 * To get Home Remedy detail for a particular id
	 * @param id
	 * @return
	 */
	public HomeRemedyDto getDiagnosticReport(String id) {
		HomeRemedy homeRemedy = get(id);
		
		// Convert domain to dto and return
		return convert(homeRemedy, HomeRemedyDto.class);
	}	
	
	/**
	 * Service to insert Home remedy
	 * @param homeRemdyDto
	 * @return
	 */
	public HomeRemedyDto create(HomeRemedyDto homeRemedyDto) {
		HomeRemedy homeRemedy = convert(homeRemedyDto, HomeRemedy.class);
		homeRemedy = homeRemedyRepository.save (homeRemedy);
		return convert(homeRemedy, HomeRemedyDto.class);
	}
	
	/**
	 * Service to delete Home Remedy for a particular id
	 * @param id
	 * @return
	 */
	public HomeRemedyDto delete(String id) {
		HomeRemedy deletedHomeRemedy = get(id);
		homeRemedyRepository.delete(deletedHomeRemedy);
		return convert(deletedHomeRemedy, HomeRemedyDto.class);
	}
	
	/**
	 * Get data based on Id
	 * @param id
	 * @return
	 */
	public HomeRemedy get(String id) {
		return homeRemedyRepository.findById(id);
	}

	/**
     * Convert domain to DTO
     * @param List of diagnosticReports
     * @return a list of diagnosticReport Dto(s)
     */
    private List populateDiagnosticDto(List homeRemedies) {
    	homeRemedies.forEach(d -> convert(d, HomeRemedyDto.class));
    	return homeRemedies;
    } 
}

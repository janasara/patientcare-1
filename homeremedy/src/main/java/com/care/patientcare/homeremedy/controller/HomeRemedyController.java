package com.care.patientcare.homeremedy.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.patientcare.homeremedy.dto.HomeRemedyDto;
import com.care.patientcare.homeremedy.dto.StatusDto;
import com.care.patientcare.homeremedy.service.HomeRemedyService;

/**
 * @author venkatg
 * Home Remedy Service 
 */
@RestController
@RequestMapping("/patientcare/v1/homeremedies")
public class HomeRemedyController {
	@Autowired
	HomeRemedyService ds;
	
	/**
	 * To get the list of home remedies and if category is passed
	 * top n configured home remedies are returned
	 * @param category
	 * @return List of Home Remedies
	 */
	@RequestMapping(method = GET)
	public ResponseEntity<?> getHomeRemedies(@RequestParam(value = "category", required=false) String category) {
		List<HomeRemedyDto> homeRemedies = ds.getHomeRemedies(category);
		return new ResponseEntity<>(homeRemedies, OK);
	}

	
	/**
	 * To insert Home Remedy 
	 * @param homeRemedyDto
	 * @return HomeRemedyDto
	 */
	@RequestMapping(method = POST)
	public ResponseEntity<?> create(@RequestBody @Valid HomeRemedyDto homeRemedyDto) {
        return new ResponseEntity<>(ds.create(homeRemedyDto), CREATED);
    }

	/**
	 * To delete Home Remedy for a particular id
	 * @param id
	 * @return deleted homeRemedyDto
	 */
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(ds.delete(id), OK);
    }
	
	/**
	 * Test Service without DB connection
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/testservice")
	public ResponseEntity<?> testService() throws JSONException {
		StatusDto statusDto = new StatusDto();
		statusDto.setStatus(true);
		return new ResponseEntity<>(statusDto, OK);
	}
}

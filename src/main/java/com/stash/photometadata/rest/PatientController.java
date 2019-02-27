package com.stash.photometadata.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stash.photometadata.exception.InvalidRequestException;
import com.stash.photometadata.exception.PatientNotFoundException;
import com.stash.photometadata.repository.mongo.domain.Patient;
import com.stash.photometadata.repository.mongo.domain.PatientMongoRepository;
import com.stash.photometadata.service.PatientService;

/**
 * 
 * @author aruna
 * TODO
 * 1. See how you can handle dates (Done)
 * 2. implement pagination on get query
 * 3. Exception handling?(404, 500, 400)
 * 4. Filtering on query: ex: by age, by gender
 * 5. Create Address resource and join patient with billing and residential address and return whole object
 * 6. Advanced:
 *    Take patient and address objects as input with dto and convert them into patient and address mongo domain objects and save them together with transactions
 * 7. Once this is done, we can create domain objects and repository for aws dynamodb.
 * 8. After successfully saving we can emit event to kinesis stream.
 * 9. Write unit test cases and integration test cases
 * 10. Creating dockerfile and deploying it to local docker container
 * 11. deploying same docker container to aws
 * 12. Create lambda to consume events from kinesis and process them
 * 13. Explore cloudwatch logs and monitoring
 * 
 */
@RestController
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientMongoRepository patientRepository;
	
	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value = "/patients/v1", method = RequestMethod.POST  )
	public ResponseEntity<Object> savePatient(@RequestBody Patient patient) {
		//LocalDateTime createdTime = LocalDateTime.now(ZoneOffset.UTC);
		
		patient.setPatientId(UUID.randomUUID().toString());
		
		Patient savedPatient = patientService.savePatient(patient);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPatient.getPatientId()).toUri();

		return ResponseEntity.created(location).build();
		
	}
	
	@RequestMapping(value = "/patients/v1", method = RequestMethod.GET  )
    public List<Patient> getPatients() {
		List<Patient> patients = patientService.getPatients();
		if(patients == null)
			throw new PatientNotFoundException("Patients not found.");
		
		return patients;
    }
	
	@RequestMapping(value = "/patients/v1/{patientId}", method = RequestMethod.GET  )
    public Patient getPatientById(@PathVariable("patientId") final String patientId) {
		if(patientId.length() < 10) {
			throw new InvalidRequestException("PatientId is invalid");
		}
		try {
			Optional<Patient> patientOpt =  patientService.getPatientById(patientId);
			if(patientOpt.isPresent()) {
				return patientOpt.get();
			}
		} catch(Exception ex) {
			logger.error("Server Error:",ex);
			throw new RuntimeException("Error fetching patient...");
		}
		
		logger.info("Patient not found.......blah...");
		throw new PatientNotFoundException("Patient not found.");
		
		
    }
	
	@RequestMapping(value = "/patients/v1/{patientId}", method = RequestMethod.DELETE  )
    public void deletePatient(@PathParam("patientId") final String patientId) {
		patientRepository.deleteById(patientId);
		
    }
}

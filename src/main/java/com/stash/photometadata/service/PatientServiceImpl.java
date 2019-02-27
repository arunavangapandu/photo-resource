package com.stash.photometadata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stash.photometadata.repository.mongo.domain.Patient;
import com.stash.photometadata.repository.mongo.domain.PatientMongoRepository;
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientMongoRepository patientRepository;
	
	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
		//return patient.getPatientId();
	}

	@Override
	public List<Patient> getPatients() {
		
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> getPatientById(String patientId) {
		
		return patientRepository.findById(patientId);
	}

	@Override
	public void  deletePatient(String patientId) {
		patientRepository.deleteById(patientId);
		
	}

}

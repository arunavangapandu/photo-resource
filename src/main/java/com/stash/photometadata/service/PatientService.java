package com.stash.photometadata.service;

import java.util.List;
import java.util.Optional;

import com.stash.photometadata.repository.mongo.domain.Patient;

public interface PatientService {
	public Patient savePatient(Patient patient);
	public List<Patient> getPatients();
	public Optional<Patient> getPatientById(final String patientId);
	public void deletePatient(final String patientId);
}

package com.minorproject.hospitalmanagementsystem.service;
import com.minorproject.hospitalmanagementsystem.dto.PatientDTO;
import com.minorproject.hospitalmanagementsystem.entity.Patient;
import com.minorproject.hospitalmanagementsystem.entity.Role;
import com.minorproject.hospitalmanagementsystem.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        if (patientDTO.getEmail() == null || patientDTO.getPassword() == null ||
                patientDTO.getFullName() == null || patientDTO.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Email, password, full name, and phone number are required");
        }

        Patient patient = new Patient();
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(patientDTO.getPassword()); // Ensure password is set
        patient.setFullName(patientDTO.getFullName());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setRole(Role.PATIENT);

        Patient savedPatient = patientRepository.save(patient);
        return new PatientDTO(savedPatient);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setFullName(patientDTO.getFullName());
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setEmail(patientDTO.getEmail());

            Patient updatedPatient = patientRepository.save(patient);
            return new PatientDTO(updatedPatient);
        } else {
            throw new RuntimeException("Patient not found with ID: " + id);
        }
    }


    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public PatientDTO getPatientByEmail(String email) {
        Optional<Patient> patient = Optional.ofNullable(patientRepository.findByEmail(email));
        return patient.map(PatientDTO::new).orElse(null);
    }
}

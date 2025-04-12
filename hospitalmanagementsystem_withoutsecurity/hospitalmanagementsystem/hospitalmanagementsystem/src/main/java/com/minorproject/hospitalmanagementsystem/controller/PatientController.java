package com.minorproject.hospitalmanagementsystem.controller;

import com.minorproject.hospitalmanagementsystem.dto.PatientDTO;
import com.minorproject.hospitalmanagementsystem.entity.Patient;
import com.minorproject.hospitalmanagementsystem.service.AppointmentService;
import com.minorproject.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id).orElseThrow();
        return ResponseEntity.ok(new PatientDTO(patient));
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatient() {
        List<Patient> patients = patientService.getAllPatient(); // Assuming this returns List<Patient>
        List<PatientDTO> patientDTOs = patients.stream()
                .map(patient -> new PatientDTO(patient)) // Assuming you have a constructor in PatientDTO that takes a Patient
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientDTOs);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatient = patientService.createPatient(patientDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Patient created successfully");
        response.put("patient", savedPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Patient updated successfully");
            response.put("patient", updatedPatient);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePatient(@PathVariable Long id) {
        boolean isDeleted = patientService.deletePatient(id);
        if (isDeleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Patient deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Patient not found"));
        }
    }

    @GetMapping("/email")
    public ResponseEntity<Map<String, Object>> getPatientByEmail(@RequestParam String email) {
        PatientDTO patientDTO = patientService.getPatientByEmail(email);
        if (patientDTO != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Patient found");
            response.put("patient", patientDTO);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Patient not found with email: " + email));
        }
    }

}
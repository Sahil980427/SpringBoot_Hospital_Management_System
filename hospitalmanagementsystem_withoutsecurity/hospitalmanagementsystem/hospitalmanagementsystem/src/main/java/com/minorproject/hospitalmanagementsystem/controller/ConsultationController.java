package com.minorproject.hospitalmanagementsystem.controller;

import com.minorproject.hospitalmanagementsystem.dto.ConsultationDTO;
import com.minorproject.hospitalmanagementsystem.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO createdConsultation = consultationService.createConsultation(consultationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultation);
    }

    @PutMapping("/{consultationId}/prescription/{prescriptionId}")
    public ResponseEntity<ConsultationDTO> addPrescriptionToConsultation(
            @PathVariable Long consultationId, @PathVariable Long prescriptionId) {
        ConsultationDTO updatedConsultation = consultationService.addPrescriptionToConsultation(consultationId, prescriptionId);
        return ResponseEntity.ok(updatedConsultation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        ConsultationDTO consultationDTO = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() {
        List<ConsultationDTO> consultations = consultationService.getAllConsultations();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ConsultationDTO>> getConsultationsByDoctorId(@PathVariable Long doctorId) {
        List<ConsultationDTO> consultations = consultationService.getConsultationsByDoctorId(doctorId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationDTO>> getConsultationsByPatientId(@PathVariable Long patientId) {
        List<ConsultationDTO> consultations = consultationService.getConsultationsByPatientId(patientId);
        if (consultations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }
        return ResponseEntity.ok(consultations);
    }
}

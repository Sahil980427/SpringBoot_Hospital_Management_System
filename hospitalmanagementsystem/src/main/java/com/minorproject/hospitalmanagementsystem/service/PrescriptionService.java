package com.minorproject.hospitalmanagementsystem.service;

import com.minorproject.hospitalmanagementsystem.dto.PrescriptionDTO;
import com.minorproject.hospitalmanagementsystem.entity.Consultation;
import com.minorproject.hospitalmanagementsystem.entity.Doctor;
import com.minorproject.hospitalmanagementsystem.entity.Patient;
import com.minorproject.hospitalmanagementsystem.entity.Prescription;
import com.minorproject.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.minorproject.hospitalmanagementsystem.repo.ConsultationRepository;
import com.minorproject.hospitalmanagementsystem.repo.DoctorRepository;
import com.minorproject.hospitalmanagementsystem.repo.PatientRepository;
import com.minorproject.hospitalmanagementsystem.repo.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ConsultationRepository consultationRepository;

    @Transactional
    public PrescriptionDTO issuePrescription(PrescriptionDTO prescriptionDTO) {
        Patient patient = patientRepository.findById(prescriptionDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + prescriptionDTO.getPatientId()));
        Doctor doctor = doctorRepository.findById(prescriptionDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + prescriptionDTO.getDoctorId()));
        Consultation consultation = null;
        if (prescriptionDTO.getConsultationId() != null) {
            consultation = consultationRepository.findById(prescriptionDTO.getConsultationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + prescriptionDTO.getConsultationId()));
        }
        Prescription prescription = new Prescription();
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setMedicationDetails(prescriptionDTO.getMedicationDetails());
        prescription.setIssuedAt(LocalDateTime.now());

        if (consultation != null) {
            prescription.setConsultation(consultation);
        }
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        if (consultation != null) {
            consultation.setPrescription(savedPrescription);
            consultationRepository.save(consultation);
        }

        return new PrescriptionDTO(savedPrescription);
    }

    public PrescriptionDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        return mapToDTO(prescription);
    }


    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        prescription.setMedicationDetails(prescriptionDTO.getMedicationDetails());
        prescription.setIssuedAt(LocalDateTime.now());

        Prescription updatedPrescription = prescriptionRepository.save(prescription);
        return mapToDTO(updatedPrescription);
    }


    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        prescriptionRepository.delete(prescription);
    }

    private PrescriptionDTO mapToDTO(Prescription prescription) {
        return new PrescriptionDTO(
                prescription.getId(),
                prescription.getPatient().getId(),
                prescription.getDoctor().getId(),
                prescription.getMedicationDetails(),
                prescription.getIssuedAt(),
                prescription.getConsultation() != null ? prescription.getConsultation().getId() : null
        );
    }

}


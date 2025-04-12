package com.minorproject.hospitalmanagementsystem.service;

import com.minorproject.hospitalmanagementsystem.dto.ConsultationDTO;
import com.minorproject.hospitalmanagementsystem.entity.Consultation;
import com.minorproject.hospitalmanagementsystem.entity.Doctor;
import com.minorproject.hospitalmanagementsystem.entity.Patient;
import com.minorproject.hospitalmanagementsystem.entity.Prescription;
import com.minorproject.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.minorproject.hospitalmanagementsystem.repo.ConsultationRepository;
import com.minorproject.hospitalmanagementsystem.repo.DoctorRepository;
import com.minorproject.hospitalmanagementsystem.repo.PatientRepository;
import com.minorproject.hospitalmanagementsystem.repo.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public ConsultationDTO createConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation();

        consultation.setConsultationDate(LocalDateTime.now());
        consultation.setDiagnosis(consultationDTO.getDiagnosis());
        consultation.setNotes(consultationDTO.getNotes());
        Patient patient = patientRepository.findById(consultationDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        consultation.setPatient(patient);
        Doctor doctor = doctorRepository.findById(consultationDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        consultation.setDoctor(doctor);
        Consultation savedConsultation = consultationRepository.save(consultation);
        return mapToDTO(savedConsultation);
    }


    public ConsultationDTO addPrescriptionToConsultation(Long consultationId, Long prescriptionId) {
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        consultation.setPrescription(prescription);
        Consultation updatedConsultation = consultationRepository.save(consultation);

        return mapToDTO(updatedConsultation);
    }

    public ConsultationDTO getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));
        return mapToDTO(consultation);
    }

    public List<ConsultationDTO> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultations.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<ConsultationDTO> getConsultationsByDoctorId(Long doctorId) {
        List<Consultation> consultations = consultationRepository.findByDoctorId(doctorId);
        return consultations.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ConsultationDTO mapToDTO(Consultation consultation) {
        return new ConsultationDTO(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getConsultationDate(),
                consultation.getDiagnosis(),
                consultation.getNotes(),
                (consultation.getPrescription() != null) ? consultation.getPrescription().getId() : null
        );
    }

    public List<ConsultationDTO> getConsultationsByPatientId(Long patientId) {
        List<Consultation> consultations = consultationRepository.findByPatientId(patientId);
        return consultations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}

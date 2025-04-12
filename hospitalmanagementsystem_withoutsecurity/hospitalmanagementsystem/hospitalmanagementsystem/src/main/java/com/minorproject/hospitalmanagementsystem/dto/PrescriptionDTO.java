package com.minorproject.hospitalmanagementsystem.dto;

import com.minorproject.hospitalmanagementsystem.entity.Prescription;

import java.time.LocalDateTime;

public class PrescriptionDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String medicationDetails;
    private LocalDateTime issuedAt;
    private Long consultationId;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.patientId = prescription.getPatient().getId();

        this.doctorId = prescription.getDoctor().getId();

        this.medicationDetails = prescription.getMedicationDetails();
        this.issuedAt = prescription.getIssuedAt();
        this.consultationId = (prescription.getConsultation() != null) ? prescription.getConsultation().getId() : null;
    }

    public PrescriptionDTO(Long id, Long patientId, Long doctorId, String medicationDetails, LocalDateTime issuedAt, Long consultationId) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicationDetails = medicationDetails;
        this.issuedAt = issuedAt;
        this.consultationId = consultationId;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }


    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }


    public String getMedicationDetails() {
        return medicationDetails;
    }

    public void setMedicationDetails(String medicationDetails) {
        this.medicationDetails = medicationDetails;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
}

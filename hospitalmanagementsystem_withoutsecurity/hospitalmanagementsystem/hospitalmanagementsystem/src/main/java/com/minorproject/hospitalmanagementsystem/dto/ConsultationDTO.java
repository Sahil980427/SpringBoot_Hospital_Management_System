package com.minorproject.hospitalmanagementsystem.dto;

import com.minorproject.hospitalmanagementsystem.entity.Consultation;

import java.time.LocalDateTime;

public class ConsultationDTO {
    private Long id;
    private Long patientId;

    private Long doctorId;

    private LocalDateTime consultationDate;
    private String diagnosis;
    private String notes;
    private Long prescriptionId;
    public ConsultationDTO() {
    }



//    public ConsultationDTO(Consultation consultation) {
//        this.id = consultation.getId();
//        this.patientId = consultation.getPatient().getId();
//        this.doctorId = consultation.getDoctor().getId();
//        this.consultationDate = consultation.getConsultationDate();
//        this.diagnosis = consultation.getDiagnosis();
//        this.notes = consultation.getNotes();
//
//        // ✅ If consultation has a linked prescription, set prescriptionId
//        if (consultation.getPrescription() != null) {
//            this.prescriptionId = consultation.getPrescription().getId();
//        }
//    }


    public ConsultationDTO(Long id, Long patientId, Long doctorId, LocalDateTime consultationDate, String diagnosis, String notes, Long prescriptionId) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationDate = consultationDate;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.prescriptionId = prescriptionId;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

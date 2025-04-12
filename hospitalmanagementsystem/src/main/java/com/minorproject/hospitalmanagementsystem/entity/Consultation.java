package com.minorproject.hospitalmanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime consultationDate;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToOne
    @JoinColumn(name = "prescription_id", unique = true)
    private Prescription prescription;

    @PrePersist
    public void prePersist() {
        this.consultationDate = LocalDateTime.now();
    }

    public Consultation(Doctor doctor, Patient patient, Prescription prescription, LocalDateTime now, String diagnosis, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.consultationDate = now;
        this.notes = notes;
    }


    public Consultation() {
    }

    public Consultation(Long id, Patient patient, Doctor doctor, LocalDateTime consultationDate, String diagnosis, String notes, Prescription prescription) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.consultationDate = consultationDate;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.prescription = prescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", consultationDate=" + consultationDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", notes='" + notes + '\'' +
                ", prescription=" + prescription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(consultationDate, that.consultationDate) && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(notes, that.notes) && Objects.equals(prescription, that.prescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, consultationDate, diagnosis, notes, prescription);
    }
}


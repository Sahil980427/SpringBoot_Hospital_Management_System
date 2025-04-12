package com.minorproject.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "prescriptions")
public class Prescription {

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
    private String medicationDetails;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @OneToOne(mappedBy = "prescription", cascade = CascadeType.ALL)
    private Consultation consultation;

    @PrePersist
    public void prePersist() {
        this.issuedAt = LocalDateTime.now();
    }
    public Prescription() {
    }

    public Prescription(Long id, Patient patient, Doctor doctor, String medicationDetails, LocalDateTime issuedAt, Consultation consultation) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medicationDetails = medicationDetails;
        this.issuedAt = issuedAt;
        this.consultation = consultation;
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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", medicationDetails='" + medicationDetails + '\'' +
                ", issuedAt=" + issuedAt +
                ", consultation=" + consultation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(medicationDetails, that.medicationDetails) && Objects.equals(issuedAt, that.issuedAt) && Objects.equals(consultation, that.consultation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, medicationDetails, issuedAt, consultation);
    }
}
package com.minorproject.hospitalmanagementsystem.dto;

import com.minorproject.hospitalmanagementsystem.entity.Appointment;
import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String status; // SCHEDULED, CANCELED, COMPLETED

    public AppointmentDTO() {
    }

    public AppointmentDTO(Appointment appointment) {
        this.patientId = appointment.getPatient().getId();
        this.doctorId = appointment.getDoctor().getId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.status = appointment.getStatus().name(); // Convert Enum to String
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

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

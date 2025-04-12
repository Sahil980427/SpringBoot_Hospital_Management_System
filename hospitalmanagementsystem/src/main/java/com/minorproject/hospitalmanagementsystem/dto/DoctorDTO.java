package com.minorproject.hospitalmanagementsystem.dto;

import com.minorproject.hospitalmanagementsystem.entity.Appointment;
import com.minorproject.hospitalmanagementsystem.entity.Doctor;
import com.minorproject.hospitalmanagementsystem.entity.Prescription;
import com.minorproject.hospitalmanagementsystem.entity.Role;
import jakarta.persistence.*;

import java.util.List;

public class DoctorDTO {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String specialization;
    private String phoneNumber;
    private Role role;

    public DoctorDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.email = doctor.getEmail();
        this.fullName = doctor.getFullName();
        this.specialization = doctor.getSpecialization();
        this.phoneNumber = doctor.getPhoneNumber();
        this.role = doctor.getRole();
    }

    public DoctorDTO() {
    }

    public DoctorDTO(Long id, String email, String password, String fullName, String specialization, String phoneNumber, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

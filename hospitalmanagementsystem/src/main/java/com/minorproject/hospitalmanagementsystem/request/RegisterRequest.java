package com.minorproject.hospitalmanagementsystem.request;

import com.minorproject.hospitalmanagementsystem.entity.Role;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;      // Only for PATIENT and DOCTOR
    private String specialization;   // Only for DOCTOR
    private Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String fullName, String phoneNumber, String specialization, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

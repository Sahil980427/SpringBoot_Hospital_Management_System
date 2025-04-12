package com.minorproject.hospitalmanagementsystem.dto;

import com.minorproject.hospitalmanagementsystem.entity.Admin;
import com.minorproject.hospitalmanagementsystem.entity.Role;
import jakarta.persistence.*;

public class AdminDTO {
    private Long id;
    private String email;
    private String fullName;
    private Role role;

    public AdminDTO() {
    }

    public AdminDTO(Long id, String email, String fullName, Role role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.fullName = admin.getFullName();
        this.role = admin.getRole();
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

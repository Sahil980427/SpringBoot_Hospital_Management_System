package com.minorproject.hospitalmanagementsystem.controller;

import com.minorproject.hospitalmanagementsystem.dto.AdminDTO;
import com.minorproject.hospitalmanagementsystem.dto.DoctorDTO;
import com.minorproject.hospitalmanagementsystem.entity.Admin;
import com.minorproject.hospitalmanagementsystem.repo.AdminRepository;
import com.minorproject.hospitalmanagementsystem.service.AdminService;
import com.minorproject.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmin() {
        List<Admin> admins = adminService.getAllAdmin();
        List<AdminDTO> adminDTOs = admins.stream()
                .map(admin -> new AdminDTO(admin))
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminDTOs);
    }

    @GetMapping("/email")
    public ResponseEntity<Admin> getAdminByEmail(@RequestParam String email) {
        Admin admin = adminService.getAdminByEmail(email);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        Admin admin = adminService.updateAdmin(id, updatedAdmin);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Map<String, String>> deleteDoctor(@PathVariable Long id) {
        boolean isDeleted = doctorService.deleteDoctor(id);

        if (isDeleted) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Doctor deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Doctor not found"));
        }
    }

    @PostMapping("/doctor")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO savedDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }
}

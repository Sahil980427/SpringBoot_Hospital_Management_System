package com.minorproject.hospitalmanagementsystem.controller;

import com.minorproject.hospitalmanagementsystem.dto.DoctorDTO;
import com.minorproject.hospitalmanagementsystem.entity.Doctor;
import com.minorproject.hospitalmanagementsystem.service.AppointmentService;
import com.minorproject.hospitalmanagementsystem.service.DoctorService;
import com.minorproject.hospitalmanagementsystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctor() {
        List<Doctor> doctors = doctorService.getAllDoctor(); // Assuming this returns List<Doctor>
        List<DoctorDTO> doctorDTOs = doctors.stream()
                .map(doctor -> new DoctorDTO(doctor)) // Assuming you have a constructor in DoctorDTO that takes a Doctor
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctorDTOs);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id).orElseThrow();
        return ResponseEntity.ok(new DoctorDTO(doctor));
    }

    @GetMapping("/email")
    public ResponseEntity<DoctorDTO> getDoctorByEmail(@RequestParam String email) {
        Doctor doctor = doctorService.getDoctorByEmail(email);
        return doctor != null ? ResponseEntity.ok(new DoctorDTO(doctor)) : ResponseEntity.notFound().build();
    }



    @PutMapping("/doctors/{id}/profile")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }


}

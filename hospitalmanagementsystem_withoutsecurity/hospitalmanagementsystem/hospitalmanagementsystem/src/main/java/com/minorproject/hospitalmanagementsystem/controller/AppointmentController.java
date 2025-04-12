package com.minorproject.hospitalmanagementsystem.controller;

import com.minorproject.hospitalmanagementsystem.dto.AppointmentDTO;
import com.minorproject.hospitalmanagementsystem.entity.Appointment;
import com.minorproject.hospitalmanagementsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId);
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(AppointmentDTO::new)  // Assuming AppointmentDTO has a constructor that accepts Appointment
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping(value = "/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(patientId);
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(AppointmentDTO::new)  // Assuming AppointmentDTO has a constructor that accepts Appointment
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOs);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments(); // Assuming this returns List<Doctor>
        List<AppointmentDTO> appointmentDTOS = appointments.stream()
                .map(appointment -> new AppointmentDTO(appointment)) // Assuming you have a constructor in DoctorDTO that takes a Doctor
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentDTOS);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO savedAppointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        String response = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeAppointment(@PathVariable Long id) {
        String response = appointmentService.completeAppointment(id);
        return ResponseEntity.ok(response);
    }







    @GetMapping("/upcoming/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingAppointmentsForDoctor(@PathVariable Long doctorId) {
        List<AppointmentDTO> appointments = appointmentService.getUpcomingAppointmentsForDoctor(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/upcoming/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingAppointmentsForPatient(@PathVariable Long patientId) {
        List<AppointmentDTO> appointments = appointmentService.getUpcomingAppointmentsForPatient(patientId);
        return ResponseEntity.ok(appointments);
    }

//    @GetMapping(value = "/appointments/{id}")
//    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForPatient(@PathVariable Long id) {
//        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(id);
//        List<AppointmentDTO> appointmentDTOs = appointments.stream()
//                .map(appointment -> new AppointmentDTO(appointment)) // Assuming you have a constructor in DoctorDTO that takes a Doctor
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(appointmentDTOs);
//    }
}

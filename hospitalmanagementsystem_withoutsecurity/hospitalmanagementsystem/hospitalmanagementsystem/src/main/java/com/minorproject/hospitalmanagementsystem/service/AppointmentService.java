package com.minorproject.hospitalmanagementsystem.service;
import com.minorproject.hospitalmanagementsystem.dto.AppointmentDTO;
import com.minorproject.hospitalmanagementsystem.entity.Appointment;
import com.minorproject.hospitalmanagementsystem.entity.Doctor;
import com.minorproject.hospitalmanagementsystem.entity.Patient;
import com.minorproject.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.minorproject.hospitalmanagementsystem.repo.AppointmentRepository;
import com.minorproject.hospitalmanagementsystem.repo.DoctorRepository;
import com.minorproject.hospitalmanagementsystem.repo.PatientRepository;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStatus(Appointment.Status.valueOf(appointmentDTO.getStatus()));

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return new AppointmentDTO(savedAppointment);
    }

    @Transactional
    public String cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

        if (appointment.getStatus() == Appointment.Status.CANCELED) {
            return "Appointment is already canceled.";
        }

        if (appointment.getStatus() == Appointment.Status.COMPLETED) {
            return "Completed appointments cannot be canceled.";
        }

        appointment.setStatus(Appointment.Status.CANCELED);
        appointmentRepository.save(appointment);

        return "Appointment canceled successfully.";
    }


    @Transactional
    public String completeAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

        if (appointment.getStatus() == Appointment.Status.COMPLETED) {
            return "Appointment is already completed.";
        }

        if (appointment.getStatus() == Appointment.Status.CANCELED) {
            return "Canceled appointments cannot be completed.";
        }

        appointment.setStatus(Appointment.Status.COMPLETED);
        appointmentRepository.save(appointment);

        return "Appointment marked as completed.";
    }

    public List<AppointmentDTO> getUpcomingAppointmentsForDoctor(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDateAfter(doctorId, LocalDateTime.now());
        return appointments.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    public List<AppointmentDTO> getUpcomingAppointmentsForPatient(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndAppointmentDateAfter(patientId, LocalDateTime.now());
        return appointments.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }
}

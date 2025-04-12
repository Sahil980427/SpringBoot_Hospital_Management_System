package com.minorproject.hospitalmanagementsystem.repo;

import com.minorproject.hospitalmanagementsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentDateAfter(Long doctorId, LocalDateTime now);
    List<Appointment> findByPatientIdAndAppointmentDateAfter(Long patientId, LocalDateTime now);
}

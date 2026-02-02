package dev.fabiosimones.ms_beautique_query.services;

import dev.fabiosimones.ms_beautique_query.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> listAllAppointmentsByCustomerId(Long customerId);
    List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedureId(Long beautyProcedureId);
}

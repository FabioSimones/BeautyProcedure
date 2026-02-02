package dev.fabiosimones.ms_beautique_query.services.impl;

import dev.fabiosimones.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import dev.fabiosimones.ms_beautique_query.repositories.AppointmentsRepository;
import dev.fabiosimones.ms_beautique_query.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentsRepository appointmentsRepository;
    @Override
    public List<FullAppointmentDTO> listAllAppointments() {
        try{
            return appointmentsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing appointments.");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsByCustomerId(Long customerId) {
        try{
            return appointmentsRepository.listAppointmentsByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Error listing appointments by customers.");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedureId(Long beautyProcedureId) {
        try{
            return appointmentsRepository.listAppointmentsByBeautyProcedureId(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException("Error listing appointments by beauty procedures.");
        }
    }
}

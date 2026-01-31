package dev.fabiosimones.ms_sync.services;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;

public interface AppointmentService {
    void saveAppointment(AppointmentsDTO appointmentsDTO);
    void updateAppointmentCustomer(CustomerDTO customerDTO);
    void updateAppointmentBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO);
}

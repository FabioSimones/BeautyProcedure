package dev.fabiosimones.ms_sync.services;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;

public interface SyncService {
    void syncCustomer(CustomerDTO customerDTO);
    void syncAppointment(AppointmentsDTO appointmentsDTO);
    void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO);
}

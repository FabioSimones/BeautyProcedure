package dev.fabiosimones.ms_sync.services.impl;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_sync.services.AppointmentService;
import dev.fabiosimones.ms_sync.services.BeautyProcedureService;
import dev.fabiosimones.ms_sync.services.CustomerService;
import dev.fabiosimones.ms_sync.services.SyncService;
import dev.fabiosimones.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class SyncServiceImpl implements SyncService {

    private final CustomerService customerService;
    private final BeautyProcedureService beautyProcedureService;
    private final AppointmentService appointmentService;

    @Override
    public void syncCustomer(CustomerDTO customerDTO) {
        try {
            SyncLogger.info("Saving customer: " + customerDTO.getId());
            customerService.savedCustomer(customerDTO);

            SyncLogger.info("Updating appointment customer: " + customerDTO.getId());
            appointmentService.updateAppointmentCustomer(customerDTO);
        } catch (Exception e) {
            SyncLogger.error("Error syncing customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
            throw e; // <-- CRÍTICO
        }
    }

    @Override
    public void syncAppointment(AppointmentsDTO appointmentsDTO) {
        try {
            SyncLogger.info("Saving appointment: " + appointmentsDTO.getId());
            appointmentService.saveAppointment(appointmentsDTO);
        }catch (Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Saving beauty procedure: " + beautyProcedureDTO.getId());
            beautyProcedureService.savedBeautyProcedure(beautyProcedureDTO);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            appointmentService.updateAppointmentBeautyProcedure(beautyProcedureDTO);
        }catch (Exception e){
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}

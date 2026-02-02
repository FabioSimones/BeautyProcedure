package dev.fabiosimones.ms_sync.services.impl;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_sync.repositories.AppointmentRepository;
import dev.fabiosimones.ms_sync.services.AppointmentService;
import dev.fabiosimones.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(AppointmentsDTO appointmentsDTO) {
        try{
            SyncLogger.info("Saving appointment: " + appointmentsDTO.getId());
            appointmentRepository.save(appointmentsDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customerDTO) {
        try {
            SyncLogger.info("Updating appointment customer: " + customerDTO.getId());
            Query query = new Query(Criteria.where("customer.id").is(customerDTO.getId()));
            Update update = new Update().set("customer", customerDTO);
            mongoTemplate.updateMulti(query, update, AppointmentsDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDTO.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedureDTO.getName())
                    .set("beautyProcedure.description", beautyProcedureDTO.getDescription());
            mongoTemplate.updateMulti(query, update, AppointmentsDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}

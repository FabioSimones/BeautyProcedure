package dev.fabiosimones.ms_beautique_query.repositories;

import dev.fabiosimones.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import dev.fabiosimones.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AppointmentsRepository extends MongoRepository<FullAppointmentDTO, Long> {

    @Query("{'customerId': ?0}")
    List<FullAppointmentDTO> listAppointmentsByCustomerId(Long customerId);

    @Query("{'beautyProcedureId': ?0}")
    List<FullAppointmentDTO> listAppointmentsByBeautyProcedureId(Long beautyProcedureId);

}

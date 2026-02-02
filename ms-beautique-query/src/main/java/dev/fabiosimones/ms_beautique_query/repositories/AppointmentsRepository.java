package dev.fabiosimones.ms_beautique_query.repositories;

import dev.fabiosimones.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentsRepository extends MongoRepository<FullAppointmentDTO, Long> {
}

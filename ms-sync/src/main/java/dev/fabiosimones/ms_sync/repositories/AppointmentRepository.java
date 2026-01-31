package dev.fabiosimones.ms_sync.repositories;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<AppointmentsDTO, Long> {
}

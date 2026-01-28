package com.devfabiosimones.beutique.repositories;

import com.devfabiosimones.beutique.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentsEntity, Long> {
}

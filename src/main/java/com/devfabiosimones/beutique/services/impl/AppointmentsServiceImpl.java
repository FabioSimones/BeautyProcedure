package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.AppointmentDTO;
import com.devfabiosimones.beutique.entities.AppointmentsEntity;
import com.devfabiosimones.beutique.repositories.AppointmentRepository;
import com.devfabiosimones.beutique.services.AppointmentsService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil =
            new ConverterUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(newAppointmentEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Optional<AppointmentsEntity> currentAppointment = appointmentRepository.findById(appointmentDTO.getId());

        if(currentAppointment.isEmpty()){
            throw new RuntimeException("Appointment not found.");
        }

        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        appointmentsEntity.setCreatedAt(currentAppointment.get().getCreatedAt());
        appointmentsEntity.setUpdatedAt(LocalDateTime.now());
        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);

        return converterUtil.convertToTarget(updatedAppointmentEntity);
    }

    @Override
    public void deleteById(Long id) {
        AppointmentsEntity appointmentsEntity = appointmentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Appointment not found."));
        appointmentRepository.delete(appointmentsEntity);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        return null;
    }
}

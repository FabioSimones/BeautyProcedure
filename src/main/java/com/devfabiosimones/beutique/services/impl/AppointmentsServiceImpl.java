package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.AppointmentDTO;
import com.devfabiosimones.beutique.entities.AppointmentsEntity;
import com.devfabiosimones.beutique.repositories.AppointmentRepository;
import com.devfabiosimones.beutique.services.AppointmentsService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        return null;
    }
}

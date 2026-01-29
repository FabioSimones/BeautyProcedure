package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.AppointmentDTO;
import com.devfabiosimones.beutique.dtos.BeautyProcedureDTO;
import com.devfabiosimones.beutique.dtos.CustomerDTO;
import com.devfabiosimones.beutique.dtos.FullAppointmentDTO;
import com.devfabiosimones.beutique.entities.AppointmentsEntity;
import com.devfabiosimones.beutique.entities.BeautyProceduresEntity;
import com.devfabiosimones.beutique.entities.CustomerEntity;
import com.devfabiosimones.beutique.repositories.AppointmentRepository;
import com.devfabiosimones.beutique.repositories.BeautyProcedureRepository;
import com.devfabiosimones.beutique.repositories.CustomerRepository;
import com.devfabiosimones.beutique.services.AppointmentsService;
import com.devfabiosimones.beutique.services.BrokerService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentsServiceImpl implements AppointmentsService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil =
            new ConverterUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(newAppointmentEntity);
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

        sendAppointmentToQueue(updatedAppointmentEntity);

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
        CustomerEntity customerEntity = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(appointmentDTO.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsOpen(false);

        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);

        sendAppointmentToQueue(updatedAppointmentEntity);

        return buildAppointmentsDTO(updatedAppointmentEntity);
    }

    private AppointmentsEntity findAppointmentById(Long id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found."));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id){
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found."));
    }

    private CustomerEntity findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found."));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity appointmentsEntity){
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }

    private void sendAppointmentToQueue(AppointmentsEntity appointmentsEntity){
        CustomerDTO customerDTO = appointmentsEntity.getCustomer()
                != null ? modelMapper.map(appointmentsEntity.getCustomer(), CustomerDTO.class): null;

        BeautyProcedureDTO beautyProcedureDTO = appointmentsEntity.getBeautyProcedure()
                != null ? modelMapper.map(appointmentsEntity.getBeautyProcedure(), BeautyProcedureDTO.class): null;

        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();

        brokerService.send("appointments", fullAppointmentDTO);
    }
}

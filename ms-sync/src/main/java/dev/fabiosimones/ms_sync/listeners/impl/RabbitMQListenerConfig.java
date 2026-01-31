package dev.fabiosimones.ms_sync.listeners.impl;

import dev.fabiosimones.ms_sync.dtos.appointments.AppointmentsDTO;
import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_sync.listeners.ListenerConfig;
import dev.fabiosimones.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableRabbit
@AllArgsConstructor
public class RabbitMQListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "customerQueue")
    @Override
    public void listenToConsumerQueue(String message) {
        try {
            CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            //Sync data here
            SyncLogger.info("Message received from queue customerQueue: " + customer.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen customer queue: " + e.getMessage());
        }

    }

    @RabbitListener(queues = "appointmentQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            AppointmentsDTO appointments = objectMapper.readValue(message, AppointmentsDTO.class);
            //Sync data here
            SyncLogger.info("Message received from queue appointmentsQueue: " + appointments.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen appointments queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProcedureQueue")
    @Override
    public void listenToBeautyProcedureQueue(String message) {
        try {
            BeautyProcedureDTO beautyProcedure = objectMapper.readValue(message, BeautyProcedureDTO.class);
            //Sync data here
            SyncLogger.info("Message received from queue beautyProcedureQueue: " + beautyProcedure.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen beauty procedure queue: " + e.getMessage());
        }
    }
}

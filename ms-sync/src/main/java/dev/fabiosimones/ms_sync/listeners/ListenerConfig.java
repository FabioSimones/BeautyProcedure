package dev.fabiosimones.ms_sync.listeners;

public interface ListenerConfig {
    void listenToConsumerQueue(String message);
    void listenToAppointmentQueue(String message);
    void listenToBeautyProcedureQueue(String message);
}

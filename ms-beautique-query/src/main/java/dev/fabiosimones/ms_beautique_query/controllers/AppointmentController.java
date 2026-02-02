package dev.fabiosimones.ms_beautique_query.controllers;

import dev.fabiosimones.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import dev.fabiosimones.ms_beautique_query.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointments(){
        return ResponseEntity.ok(appointmentService.listAllAppointments());
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentsByCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(appointmentService.listAllAppointmentsByCustomerId(customerId));
    }

    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentByBeautyProcedure(@PathVariable Long beautyProcedureId){
        return ResponseEntity.ok(appointmentService.listAllAppointmentsByBeautyProcedureId(beautyProcedureId));
    }
}

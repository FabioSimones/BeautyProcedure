package com.devfabiosimones.beutique.controllers;

import com.devfabiosimones.beutique.dtos.AppointmentDTO;
import com.devfabiosimones.beutique.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping
    ResponseEntity<AppointmentDTO> create (@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentsService.create(appointmentDTO));
    }

    @PatchMapping
    ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentsService.update(appointmentDTO));
    }
}

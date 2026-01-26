package com.devfabiosimones.beutique.controllers;

import com.devfabiosimones.beutique.dtos.BeautyProcedureDTO;
import com.devfabiosimones.beutique.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @PostMapping
    public ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO){
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        beautyProcedureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<BeautyProcedureDTO> update(@RequestBody BeautyProcedureDTO beautyProcedureDTO){
        return ResponseEntity.ok(beautyProcedureService.update(beautyProcedureDTO));
    }
}

package dev.fabiosimones.ms_beautique_query.controllers;

import dev.fabiosimones.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import dev.fabiosimones.ms_beautique_query.services.BeautyProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedure")
@AllArgsConstructor
public class BeautyProcedureController {

    private final BeautyProcedureService beautyProcedureService;

    @GetMapping
    ResponseEntity<List<BeautyProcedureDTO>> listAllBeautyProcedures(){
        return ResponseEntity.ok(beautyProcedureService.listAllBeautyProcedure());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<BeautyProcedureDTO>> listByNameIgnoreCase(@PathVariable String name){
        return ResponseEntity.ok(beautyProcedureService.listByNameIgnoreCase(name));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<BeautyProcedureDTO>> listByDescriptionIgnoreCase(@PathVariable String description){
        return ResponseEntity.ok(beautyProcedureService.listByDescriptionIgnoreCase(description));
    }
}

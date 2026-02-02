package dev.fabiosimones.ms_beautique_query.services.impl;

import dev.fabiosimones.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import dev.fabiosimones.ms_beautique_query.repositories.BeautyProcedureRepository;
import dev.fabiosimones.ms_beautique_query.services.BeautyProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BeautyProcedureImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedure() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedure.");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try{
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listing beauty procedure by name.");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try{
            return beautyProcedureRepository.findByDescriptionIgnoreCase(description);
        } catch (Exception e) {
            throw new RuntimeException("Error listing beauty procedure by name.");
        }
    }
}

package dev.fabiosimones.ms_sync.services.impl;

import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import dev.fabiosimones.ms_sync.repositories.BeautyProcedureRepository;
import dev.fabiosimones.ms_sync.services.BeautyProcedureService;
import dev.fabiosimones.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void savedBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO) {
        try{
            SyncLogger.info("Saving beauty procedure: " + beautyProcedureDTO.getId());
            beautyProcedureRepository.save(beautyProcedureDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}

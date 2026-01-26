package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.BeautyProcedureDTO;
import com.devfabiosimones.beutique.entities.BeautyProceduresEntity;
import com.devfabiosimones.beutique.repositories.BeautyProcedureRepository;
import com.devfabiosimones.beutique.services.BeautyProcedureService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil =
            new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        return converterUtil.convertToTarget(newBeautyProceduresEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> beautyProceduresEntity = beautyProcedureRepository.findById(id);
        if (beautyProceduresEntity.isEmpty()){
            throw new RuntimeException("Beauty procedure not found.");
        }
        beautyProcedureRepository.deleteById(id);
    }
}

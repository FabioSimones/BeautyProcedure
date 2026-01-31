package dev.fabiosimones.ms_sync.repositories;

import dev.fabiosimones.ms_sync.dtos.beautyprocedure.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}

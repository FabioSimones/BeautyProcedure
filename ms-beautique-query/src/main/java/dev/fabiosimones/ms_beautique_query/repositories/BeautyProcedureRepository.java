package dev.fabiosimones.ms_beautique_query.repositories;

import dev.fabiosimones.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}

package com.devfabiosimones.beutique.repositories;

import com.devfabiosimones.beutique.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity, Long> {
}

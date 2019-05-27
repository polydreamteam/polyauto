package com.polyauto.repositories;

import com.polyauto.entities.CarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsEntityRepository extends JpaRepository<CarsEntity, Integer>
{
    List<CarsEntity> findAvailableCars();
    CarsEntity isCarAvalaible(int cardId);
}

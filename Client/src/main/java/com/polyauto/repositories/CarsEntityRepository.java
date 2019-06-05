package com.polyauto.repositories;

import com.polyauto.entities.CarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsEntityRepository extends JpaRepository<CarsEntity, Integer>
{
    List<CarsEntity> findAvailableCars();
    List<CarsEntity> findNotAvailableCars();
    List<CarsEntity> findWithStatusAndModel(Byte status, int model);
    CarsEntity isCarAvalaible(int cardId);
}

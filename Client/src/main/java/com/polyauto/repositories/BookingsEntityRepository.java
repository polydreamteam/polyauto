package com.polyauto.repositories;

import com.polyauto.entities.BookingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsEntityRepository extends JpaRepository<BookingsEntity, Integer>
{
    BookingsEntity findByIdBooking(int id);
    List<BookingsEntity> findByIdUser(int idUser);
    List<BookingsEntity> findUsersOpenedBooking(int idUser);
}

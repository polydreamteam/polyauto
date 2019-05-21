package com.polyauto.repositories;

import com.polyauto.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersEntityRepository extends JpaRepository<UsersEntity, Integer>
{
    UsersEntity findByLogin(String login);
}

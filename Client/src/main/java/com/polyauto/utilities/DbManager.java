package com.polyauto.utilities;

import com.polyauto.entities.BookingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Service
public class DbManager {
    @Autowired
    private EntityManager emA;

    private static EntityManager em;

    @PostConstruct
    public void init()
    {
        em = emA;
    }


    public static void saveBooking(BookingsEntity entity) throws Exception
    {
        em.persist(entity);
    }
}

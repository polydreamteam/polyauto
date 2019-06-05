package dao;

import exceptions.MonException;
import metier.BookingsEntity;

import javax.persistence.EntityTransaction;

public class Service extends EntityService {

    public BookingsEntity bookingById(int id) throws MonException, Exception {
        BookingsEntity booking = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            booking = entitymanager.find(BookingsEntity.class, id);
            entitymanager.close();
        } catch (RuntimeException e) {
            throw(new MonException("Erreur de lecture", e.getMessage()));
        }
        return booking;
    }
}

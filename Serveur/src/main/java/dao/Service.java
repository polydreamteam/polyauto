package dao;

import meserreurs.MonException;
import metier.BookingsEntity;
import metier.CarsEntity;

import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

public class Service extends EntityService {

    public void insertObject(Object object) throws MonException, Exception {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entityManager.persist(object);
            transac.commit();
            entityManager.close();
        } catch (
        EntityNotFoundException h) {
            new MonException("Erreur d'insertion", h.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur :"+ e.getMessage());
            new MonException("Erreur d'insertion", e.getMessage());
        }
    }
}

package dao;

import com.polyauto.entities.Bookings;
import meserreurs.MonException;

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
            h.printStackTrace();
            throw (new MonException("Erreur d'insertion", h.getMessage()));
        } catch (Exception e) {
            System.out.println("Erreur :"+ e.getMessage());
            e.printStackTrace();
            throw(new MonException("Erreur d'insertion", e.getMessage()));
        }
    }

    public void updateObject(Object object) throws MonException, Exception {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entityManager.merge(object);
            transac.commit();
            entityManager.close();
        } catch (
                EntityNotFoundException h) {
            throw(new MonException("Erreur d'insertion", h.getMessage()));
        } catch (Exception e) {
            System.out.println("Erreur :"+ e.getMessage());
            throw(new MonException("Erreur d'insertion", e.getMessage()));
        }
    }


}

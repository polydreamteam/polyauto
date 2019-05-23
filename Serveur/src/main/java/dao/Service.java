package dao;

import meserreurs.MonException;
import metier.*;

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
            throw (new MonException("Erreur d'insertion", h.getMessage()));
        } catch (Exception e) {
            System.out.println("Erreur :"+ e.getMessage());
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

    public void insertCar(Cars car) throws Exception {
        // on construit un objet Entity
        CarsEntity carEntity = new CarsEntity();
        // on tansfère les données reçues dans l'objet Entity
        carEntity.setIdCar(car.getIdCar());
        carEntity.setLat(car.getLat());
        carEntity.setLon(car.getLon());
        carEntity.setModel(car.getModel());
        carEntity.setStatus(car.getStatus());
        insertObject(carEntity);
    }

    public void insertBooking(Bookings booking) throws Exception {
        // on construit un objet Entity
        BookingsEntity bookingEntity = new BookingsEntity();
        // on tansfère les données reçues dans l'objet Entity
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setDateUp(booking.getDateUp());
        bookingEntity.setDateDown(booking.getDateDown());
        bookingEntity.setIdBooking(booking.getIdBooking());
        insertObject(bookingEntity);
    }

    public void insertUser(Users user) throws Exception {
        // on construit un objet Entity
        UsersEntity userEntity = new UsersEntity();
        userEntity.setFirstname(user.getFirstName());
        userEntity.setLastname(user.getLastName());
        userEntity.setIdUser(user.getIdUser());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setNote(user.getNote());
        insertObject(userEntity);
    }


}

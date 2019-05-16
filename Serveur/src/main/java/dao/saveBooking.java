package dao;

import meserreurs.MonException;
import metier.BookingsEntity;

import javax.persistence.*;

public class saveBooking {
    private EntityManagerFactory factory;
    private  EntityManager entityManager;

    public   void insertBooking(BookingsEntity aBooking) throws Exception, MonException {

        try {

                 // On instancie l'entity Manager
                factory = Persistence.createEntityManagerFactory("PInscription");

                entityManager  = factory.createEntityManager();
               System.out.println("Création  EM !");
               // On démarre une transaction
                entityManager.getTransaction().begin();
                entityManager.persist(aBooking);
                entityManager.flush();
                // on valide la transacition
                entityManager.getTransaction().commit();
                entityManager.close();

        } catch (EntityNotFoundException h) {
            new MonException("Erreur d'insertion", h.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur :"+ e.getMessage());
            new MonException("Erreur d'insertion", e.getMessage());
        }
    }
}


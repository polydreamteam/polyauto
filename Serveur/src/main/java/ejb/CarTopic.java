package ejb;

import dao.Service;
import meserreurs.MonException;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import metier.Cars;
import metier.CarsEntity;

/**
 * Message-Driven Bean implementation class for: CarTopic
 */
// On se connecte à la file d'attente InscriptionTopic
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:jboss/exported/topic/CarTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")},
        mappedName = "CarTopic")
public class CarTopic implements MessageListener {

    @Resource
    private MessageDrivenContext context;

    /*
     * Default constructor.
     */
    public CarTopic() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message)  {
        // TODO Auto-generated method stub
        // On gère le message récupéré dans le topic

        try {
            // On transforme le message en demande d'inscription
            ObjectMessage objectMessage = (ObjectMessage) message;
            Cars car = (Cars) objectMessage.getObject();

            if (message != null) {
                // On insère cette demande d'inscription dans la base de données
                // on s'assure que l'écriture ne se fera qu'une fois.
                message = null;

                try {

                    // on construit un objet Entity
                    CarsEntity carEntity = new CarsEntity();
                    // on tansfère les données reçues dans l'objet Entity
                    carEntity.setIdCar(car.getIdCar());
                    carEntity.setLat(car.getLat());
                    carEntity.setLon(car.getLon());
                    carEntity.setModel(car.getModel());
                    carEntity.setStatus(car.getStatus());
                    Service aServ = new Service();
                    aServ.insertObject(carEntity);
                } catch (NamingException er) {
                    System.out.println("Message Naming  :" + er.getMessage());
                    EcritureErreur(er.getMessage());
                } catch (MonException e) {
                    EcritureErreur(e.getMessage());
                    System.out.println("Message MonException :" + e.getMessage());
                } catch (Exception ex) {
                    System.out.println("Message Excep :" + ex.getMessage());
                    EcritureErreur(ex.getMessage());
                }
            }

        }   catch (JMSException  e) {
            e.printStackTrace();
            System.err.println("JMSException in onMessage(): " + e.getMessage());
            EcritureErreur(e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Erreur Cast  :"+ ex.getMessage());
            EcritureErreur(ex.getMessage());
        }
    }

    /**
     * Permet d'enregistrer une erreur dans un fichier log
     *
     * @param message Le message d'erreur
     */
    public void EcritureErreur(String message) {
        BufferedWriter wr;
        String nomf = "erreurs.log";
        Date madate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm");
        try {
            // On écrit à la fin du fichier
            wr = new BufferedWriter(new FileWriter(nomf, true));
            wr.newLine();
            wr.write(sdf.format(madate));
            wr.newLine();
            wr.write(message);
            wr.close();
        } catch (FileNotFoundException ef) {
            ;
        } catch (IOException eio) {
            ;
        }
    }
}

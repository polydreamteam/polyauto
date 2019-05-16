package ejb;

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

import dao.saveBooking;
import metier.Bookings;
import metier.BookingsEntity;

/**
 * Message-Driven Bean implementation class for: BookingTopic
 */
// On se connecte à la file d'attente InscriptionTopic
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:jboss/exported/topic/BookingTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")},
        mappedName = "BookingTopic")
public class BookingTopic implements MessageListener {

    @Resource
    private MessageDrivenContext context;

    /*
     * Default constructor.
     */
    public BookingTopic() {
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
            Bookings booking = (Bookings) objectMessage.getObject();

            if (message != null) {
                // On insère cette demande d'inscription dans la base de données
                // on s'assure que l'écriture ne se fera qu'une fois.
                message = null;

                try {

                    // on construit un objet Entity
                    BookingsEntity bookingEntity = new BookingsEntity();
                    // on tansfère les données reçues dans l'objet Entity
                    bookingEntity.setIdBooking(booking.getIdBooking());
                    bookingEntity.setDateDown(booking.getDateDown());
                    bookingEntity.setDateUp(booking.getDateUp());
                    bookingEntity.setStatus(booking.getStatus());
                    saveBooking uneE = new saveBooking();

                    uneE.insertBooking(bookingEntity);
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

package ejb;

import dao.BookingsService;
import dao.CarsService;
import dao.Service;
import dao.UsersService;
import com.polyauto.dto.ObjectMessageSend;
import meserreurs.MonException;
import com.polyauto.entities.*;

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


/**
 * Message-Driven Bean implementation class for: PolyAutoTopic
 */
// On se connecte à la file d'attente InscriptionTopic
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:jboss/exported/topic/PolyAutoTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")},
        mappedName = "PolyAutoTopic")
public class PolyAutoTopic implements MessageListener {

    @Resource
    private MessageDrivenContext context;

    /*
     * Default constructor.
     */
    public PolyAutoTopic() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message)  {
        // TODO Auto-generated method stub
        // On gère le message récupéré dans le topic
        if (!(message instanceof ObjectMessage)){
            return ;
        }

        try {
            // On transforme le message en demande d'inscription
            ObjectMessage objectMessage = (ObjectMessage) message;

            if (message != null) {
                // On insère cette demande d'inscription dans la base de données
                // on s'assure que l'écriture ne se fera qu'une fois.
                message = null;

                try {
                    ObjectMessageSend objectMessageSend = (ObjectMessageSend) objectMessage.getObject();
                    Object object = objectMessageSend.getObject();

                    //Obtenir le bon service
                    Service aServ;
                    if (object instanceof Bookings) {
                        aServ = new BookingsService();
                    } else if (object instanceof Users) {
                        aServ = new UsersService();
                    } else if (object instanceof Cars) {
                        aServ = new CarsService();
                    }
                    else{
                        throw new MonException();
                    }

                    //Appeler la bonne fonction
                    switch(objectMessageSend.getAction()){
                        case ObjectMessageSend.INSERT:
                            aServ.insertObject(object);
                            break;

                        case ObjectMessageSend.UPDATE:
                            aServ.updateObject(object);
                            break;

                        default:
                            break;
                    }

                } catch (NamingException er) {
                    System.out.println("Message Naming  :" + er.getMessage());
                    er.printStackTrace();
                    //EcritureErreur(er.getMessage());
                } catch (MonException e) {
                    //EcritureErreur(e.getMessage());
                    e.printStackTrace();
                    System.out.println("Message MonException :" + e.getMessage());
                } catch (JMSException  e) {
                    e.printStackTrace();
                    System.err.println("JMSException in onMessage(): " + e.getMessage());
                    //EcritureErreur(e.getMessage());
                    context.setRollbackOnly();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Message Excep :" + ex.getMessage());
                    //EcritureErreur(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println("Erreur Cast  :"+ ex.getMessage());
            ex.printStackTrace();
            //EcritureErreur(ex.getMessage());
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

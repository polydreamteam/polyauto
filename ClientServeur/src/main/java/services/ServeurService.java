package services;

import dto.ObjectMessageSend;
import exceptions.MonException;


import javax.annotation.Resource;
import javax.jms.*;

public class ServeurService {

    @Resource(lookup = "java:jboss/exported/topic/PolyAutoTopic")
    private Topic topic;

    //On accède à l'EJB
    @Resource(mappedName = "java:/ConnectionFactory")
    private TopicConnectionFactory cf;

    // Session établie avec le serveur
    private TopicSession session = null;

    // Le client utilise un Producteur de messsage pour envoyer une demande de
    // formation
    private TopicPublisher producer;

    public ServeurService(){

    }

    /**
     * Permet de demander de faire un insert
     * @param object Objet que l'on souhaite ajouter
     * @return  true si ca a fonctionné, else sinon
     * @throws Exception
     */
    public boolean insert(Object object)throws Exception{
        ObjectMessageSend objectMessageSend = new ObjectMessageSend(ObjectMessageSend.INSERT, object);
        return publier(objectMessageSend);
    }

    /**
     * Permet de demander de faire un update
     * @param object Objet que l'on souhaite mettre à jour
     * @return  true si ca a fonctionné, else sinon
     * @throws Exception
     */
    public boolean update(Object object) throws Exception{
        ObjectMessageSend objectMessageSend = new ObjectMessageSend(ObjectMessageSend.UPDATE, object);
        return publier(objectMessageSend);
    }

    /**
     * Permet de publier une demande d'inscription dans le topic
     *
     * @param object La demande d'inscription � publier
     * @return
     * @throws Exception
     */
    public boolean publier(ObjectMessageSend object) throws Exception {

        boolean ok = true;
        TopicConnection connection = null;

        try {

            // On crée la connexion JMS , session, producteur et message;
            /*
             * connection = connectionFactory.createConnection(
             * System.getProperty("username", DEFAULT_USERNAME),
             * System.getProperty("password", DEFAULT_PASSWORD));
             */


            // Création Connection et Session JMS
            connection = cf.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            // On crée le producteur utilisé pour envoyer un message
            producer = session.createPublisher(topic);
            // On lance la connection
            connection.start();
            ObjectMessage message = session.createObjectMessage();
            message.setObject(object);
            // On publie le message
            producer.publish(message);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException j) {
            new MonException(j.getMessage());
            ok = false;
        } catch (Exception e) {
            new MonException(e.getMessage());
            ok = false;
        }
        return ok;
    }

}

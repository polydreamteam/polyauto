package com.polyauto.service;


import com.polyauto.entities.Bookings;

import javax.annotation.Resource;
import javax.jms.*;

public class SendBooking {

    // Session établie avec le serveur
    private TopicSession session = null;


    @Resource(lookup = "java:jboss/exported/topic/PolyAutoTopic")
    private Topic topic;
    // On accède à l'EJB
    @Resource(mappedName = "java:/ConnectionFactory")
    private TopicConnectionFactory cf;

    // Le client utilise un Producteur de messsage pour envoyer une demande de
    // formation
    private TopicPublisher producer;

    /**
     * Permet de publier une reservation dans le topic
     *
     * @param aBooking La reservation à publier
     * @return
     * @throws Exception
     */
    public boolean  PublishBooking(Bookings aBooking) throws Exception {

        boolean ok = true;
        TopicConnection connection = null;

        try {


            // Création Connection et Session JMS
            connection = cf.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            // On crée le producteur utilisé pour envoyer un message
            producer = session.createPublisher(topic);
            // On lance la connection
            connection.start();
            ObjectMessage message = session.createObjectMessage();
            message.setObject(aBooking);
            // On publie le message
            producer.publish(message);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException j) {
            throw j;
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

}

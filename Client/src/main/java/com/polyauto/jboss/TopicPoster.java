package com.polyauto.jboss;

import com.polyauto.dto.ObjectMessageSend;
import com.polyauto.entities.Bookings;
import com.polyauto.entities.BookingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Classe pour la communication avec JMS
 */
@Service
public class TopicPoster
{
    private static TopicConnectionFactory tcf;
    private static TopicConnection conn;
    private static Topic topic;

    /**
     * Initialisation de la connexion JMS
     * @throws JMSException
     * @throws NamingException
     */
    public static void init() throws JMSException, NamingException {

        try {
            InitialContext iniCtx = new InitialContext();

            Object tmp = iniCtx.lookup("ConnectionFactory");

            tcf = (TopicConnectionFactory) tmp;
            conn = tcf.createTopicConnection("jmsuser", "jmsepul98!");
            topic = (Topic) iniCtx.lookup("java:jboss/exported/topic/PolyAutoTopic");

        } catch (JMSException e) {
            throw e;
        } catch (NamingException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Publie un objet sur le topic
     * @param object
     * @throws Exception
     */
    public static void publish(ObjectMessageSend object) throws Exception
    {
        try {

            TopicSession session = conn.createTopicSession(false,TopicSession.AUTO_ACKNOWLEDGE);

            TopicPublisher producer = session.createPublisher(topic);

            conn.start();

            ObjectMessage message = session.createObjectMessage();
            message.setObject(object);
            producer.publish(message);

            producer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
            throw e;
        }

    }
}

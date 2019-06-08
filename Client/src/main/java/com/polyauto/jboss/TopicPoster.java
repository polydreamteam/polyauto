package com.polyauto.jboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

@Service
public class TopicPoster
{
    private static TopicConnectionFactory tcf;
    private static TopicConnection conn;
    private static Topic topic;

    public static void init() throws JMSException, NamingException {

        try {
            InitialContext iniCtx = new InitialContext();

            Object tmp = iniCtx.lookup("ConnectionFactory");

            tcf = (TopicConnectionFactory) tmp;
            conn = tcf.createTopicConnection("jmsuser", "jmsepul98!");
            topic = (Topic) iniCtx.lookup("java:jboss/exported/topic/PolyA  utoTopic");
            // Send the specified number of messages
        } catch (JMSException e) {
            throw e;
        } catch (NamingException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void publish(Serializable object) throws Exception
    {
        try {
            // Send the specified number of messages
            TopicSession session = conn.createTopicSession(false,TopicSession.AUTO_ACKNOWLEDGE);
            // On crée le producteur utilisé pour envoyer un message
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

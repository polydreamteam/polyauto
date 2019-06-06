package com.polyauto.jboss;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicPoster
{
    private static final String DEFAULT_CONNECTION_FACTORY = "ConnectionFactory";
    private static final String DEFAULT_DESTINATION = "java:jboss/exported/topic/DemandeInscriptionJmsTopic";

    private static final String DEFAULT_USERNAME = "jmsuser";
    private static final String DEFAULT_PASSWORD = "jmsepul98!";

    private static TopicConnectionFactory tcf;
    private static TopicConnection conn;
    private static Topic topic;

    @SuppressWarnings("finally")
    public static void init() throws JMSException, NamingException {

        try {
            InitialContext iniCtx = new InitialContext();
            Object tmp = iniCtx.lookup("ConnectionFactory");

            tcf = (TopicConnectionFactory) tmp;
            conn = tcf.createTopicConnection("jmsuser", "jmsepul98!");
            topic = (Topic) iniCtx.lookup("java:jboss/exported/topic/PolyAutoTopic");
            // Send the specified number of messages
        } catch (JMSException e) {
            throw e;
        } catch (NamingException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void publish() throws Exception
    {
        try {
            TopicSession session = conn.createTopicSession(false,
                    TopicSession.AUTO_ACKNOWLEDGE);
            // On crée le producteur utilisé pour envoyer un message
            TopicPublisher producer = session.createPublisher(topic);
            conn.start();
            ObjectMessage message = session.createObjectMessage();
            message.setObject(Integer.parseInt("1"));
            producer.publish(message);
            producer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
            throw e;
        }

    }
}

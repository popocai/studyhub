/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer messageProducer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);

        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("FQ");
        messageProducer = session.createProducer(destination);

        sendMessage(session, messageProducer);

        session.commit();

        connection.close();
    }

    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        TextMessage textMessage = session.createTextMessage("I'm a message");
        System.out.println("Send Message");
        messageProducer.send(textMessage);

    }
}

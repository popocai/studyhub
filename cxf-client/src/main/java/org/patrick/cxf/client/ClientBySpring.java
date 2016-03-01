/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.patrick.cxf.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientBySpring {
    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("application-context.xml");
        SmsClient smsClient = (SmsClient) cxt.getBean("smsClient");
        MessageFromDispatcher message = new MessageFromDispatcher();
        message.setContent("Test");
        List<String> list = new ArrayList<>();
        list.add("18610001000");
        message.setDestinations(list);
        message.setSender("Patrick");

        Response response = smsClient.sendSms(message);
        System.out.println(response.getStatus());
    }
}

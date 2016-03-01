/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package demo.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import demo.entity.MessageFromDispatcher;

public class TestWebClient {
    public static void main(String[] args) {
        String baseAddress = "http://localhost:8088";
        MessageFromDispatcher message = new MessageFromDispatcher();
        message.setContent("Test");
        List<String> list = new ArrayList<>();
        list.add("18610001000");
        message.setDestinations(list);
        message.setSender("Patrick");

        List<Object> providers = new ArrayList<Object>();
        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

        Response response = WebClient.create(baseAddress, providers).path("/sms-adapter/rest/sendSms")
                .header("Content-Type", MediaType.APPLICATION_JSON).post(message, Response.class);

        System.out.println(response.getStatus());
        System.out.println(response.getEntity());
    }

}

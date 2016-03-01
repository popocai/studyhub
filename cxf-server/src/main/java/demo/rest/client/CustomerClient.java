package demo.rest.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class CustomerClient {
    public static void main(String[] args) throws Exception {
        go("http://localhost:9999/cxf/cs/customer/1/info");
        // go("http://localhost:9999/cxf/jaxrs/customer/search?name=abc");
    }

    private static void go(String url) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        int statusCode = client.executeMethod(method);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + method.getStatusLine());
        }
        byte[] responseBody = method.getResponseBody();
        System.out.println(new String(responseBody));
    }
}

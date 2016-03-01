package demo.ws;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class HelloWorldServer {
	public static void main(String[] args) throws Exception {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(HelloWorldImpl.class);
		factory.setAddress("http://localhost:9999/cxf/HelloWorld");
		factory.create();
		System.out.println("Server start...");
	}
}

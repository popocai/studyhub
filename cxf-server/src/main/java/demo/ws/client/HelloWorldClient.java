package demo.ws.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import demo.ws.HelloWorld;

public class HelloWorldClient {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9999/cxf/HelloWorld");
		HelloWorld helloworld = (HelloWorld) factory.create();
		System.out.println(helloworld.sayHi("kongxx"));
		System.exit(0);
	}
}

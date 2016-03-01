package demo.ws.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.ws.HelloWorld;

public class SpringClient {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		HelloWorld helloworld = (HelloWorld) context.getBean("helloworldClient");
		System.out.println(helloworld.sayHi("kongxx"));
	}
}

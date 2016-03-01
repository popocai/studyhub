package demo.ws;


public class HelloWorldImpl implements HelloWorld {
	public String sayHi(String name) {
		String msg = "Hello " + name + "!";
		return msg;
	}
}

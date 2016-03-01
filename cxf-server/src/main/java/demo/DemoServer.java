package demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class DemoServer {
	public static void main(String[] args) {
        String jetty_home = "cxf";// 这个就是你的项目发布时候的名字
        try {

            WebAppContext webapp = new WebAppContext();
            webapp.setContextPath("/" + jetty_home);// 上下文路径 比如说/jettytest
            webapp.setResourceBase("src/main/webapp");// 你的资源文件所在的路径 一般都在这下面

            Server server = new Server(9999);
            server.setHandler(webapp);
			server.start();
			server.join();
            System.out.println("Started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

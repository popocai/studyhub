package org.patrick.learn.ignite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class TestTime
 */
public class TestTime extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Ignite            ignite;

    private IgniteCache<String, String> cache;

    /**
     * Default constructor.
     */
    public TestTime() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-ignite.xml");
        ignite = context.getBean(Ignite.class);
        cache = ignite.getOrCreateCache("tscGeoFenceCache");
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        String k = request.getParameter("k");
        String v = request.getParameter("v");
        if ("add".equals(cmd)) {
            cache.putIfAbsent(k, v);
        }

        if ("q".equals(cmd)) {
            System.out.println(cache.get(k));
        }
    }

}

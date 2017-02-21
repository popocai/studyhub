package org.patrick.learn.ignite.sql;

import java.io.IOException;
import java.util.List;

import javax.cache.Cache.Entry;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class SqlQuery
 */
public class SqlQuery extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    private ApplicationContext appContext       = new ClassPathXmlApplicationContext(
new String[] {
            "classpath:applicationContext-bean.xml", "applicationContext-data.xml", "applicationContext-ignite.xml" });

    private CspSignalDao       cspSignalDao;

    private Ignite             ignite;

    public SqlQuery() {
        super();
        cspSignalDao = appContext.getBean(CspSignalDao.class);
        ignite = (Ignite) appContext.getBean("ignite");

        IgniteCache<String, CspSignal> cache = ignite.getOrCreateCache("sql");
        cache.clear();
    }

    private List<CspSignal> list = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        list = cspSignalDao.queryAll();
        System.out.println("signal size:" + list.size());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        IgniteCache<String, CspSignal> cache = ignite.getOrCreateCache("sql");

        if ("save".equals(cmd)) {
            for (CspSignal signal : list) {
                cache.put(signal.getId() + "", signal);
            }
            System.out.println("Saved");
        } else if ("query".equals(cmd)) {
            System.out.println(cache.get(request.getParameter("id")));
        } else if ("sql".equals(cmd)) {
            String field = request.getParameter("f");
            String model = request.getParameter("m");

            // SqlFieldsQuery query = new SqlFieldsQuery("select CspSignal from CspSignal s "
            // + "where s.vdsField = ? "
            // + "and s.vehicleModelCode = ?");
            //
            //
            // query.setArgs(field, model);

            org.apache.ignite.cache.query.SqlQuery<String, CspSignal> sql = new org.apache.ignite.cache.query.SqlQuery(
                    CspSignal.class, "vehicleModelCode = ?");
            // Find only persons earning more than 1,000.
            try (QueryCursor<javax.cache.Cache.Entry<String, CspSignal>> cursor = cache.query(sql.setArgs(model))) {
                for (Entry<String, CspSignal> e : cursor) {
                    System.out.println(e.getValue().toString());
                }
            }

        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

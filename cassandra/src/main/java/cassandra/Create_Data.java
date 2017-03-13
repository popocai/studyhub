/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Create_Data {
    public static void main(String args[]) {

        // queries
        String query1 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone,  emp_sal)"
                + " VALUES(1,'ram', 'Hyderabad', 9848022338, 50000);";

        String query2 = "UPDATE emp SET emp_city='Delhi',emp_sal=50000 WHERE emp_id = 2;";

        String query3 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone, emp_sal)"
                + " VALUES(3,'rahman', 'Chennai', 9848022330, 45000);";

        // Creating Cluster object
        Cluster cluster = Cluster.builder().addContactPoint("vmc7").build();

        // Creating Session object
        Session session = cluster.connect("myks");

        // Executing the query
        session.execute(query1);

        session.execute(query2);

        session.execute(query3);

        System.out.println("Data created");

        session.close();

        cluster.close();

    }
}

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

public class Create_KeySpace {
    public static void main(String args[]) {

        // Query
        String query = "CREATE KEYSPACE tp WITH replication " + "= {'class':'SimpleStrategy', 'replication_factor':1};";

        // creating Cluster object
        Cluster cluster = Cluster.builder().addContactPoint("vmc7").withPort(9042).build();

        // Creating Session object
        Session session = cluster.connect();

        // Executing the query
        session.execute(query);

        // using the KeySpace
        session.execute("USE tp");
        System.out.println("Keyspace created");
    }
}

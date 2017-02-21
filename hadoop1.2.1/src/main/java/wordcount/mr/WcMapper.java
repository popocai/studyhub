/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package wordcount.mr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    static String FILE_PATH = "/patrick/test/wc/mr.log";

    @Override
    /**
     * key : line no
     *
     */
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        System.out.println("-------------------------------------");
        System.out.println("key     = " + key);
        System.out.println("value   = " + value);


        StringTokenizer st = new StringTokenizer(value.toString());

        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            context.write(new Text(word), new IntWritable(1));
        }
    }
    
}

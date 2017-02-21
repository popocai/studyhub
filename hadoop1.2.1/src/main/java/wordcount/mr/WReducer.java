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

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> iterable, Context context)
            throws IOException, InterruptedException {

        System.out.println("---- Reducer ------");

        int sum = 0;
        for (IntWritable i : iterable) {
            sum = sum + i.get();
        }
        System.out.println("key = " + key + ", sum = " + sum);

        context.write(key, new IntWritable(sum));
    }

}

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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobRunner {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "vmcentos1:9001");

        Job job = new Job(conf);
        job.setJarByClass(JobRunner.class);
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);

        FileInputFormat.addInputPath(job, new Path("/patrick/test/wc"));
        FileOutputFormat.setOutputPath(job, new Path("/patrick/test/wc-output"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}

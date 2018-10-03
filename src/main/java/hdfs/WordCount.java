package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.net.URI;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: WordCount
 * @time: 2018/7/25 11:12
 * @desc:
 */
public class WordCount {

    public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words=line.split("\t");
            for(String word:words){
                System.out.println(word);
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    public static  class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int count=0;
            for(IntWritable value:values){
                count+=value.get();
            }
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            run();
    }
    public static void run() throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf=new Configuration();

        conf.set("mapred.job.tracker", "192.168.86.128:9000");
        Path output=new Path("hdfs://192.168.86.128:9000/tly/out");
        FileSystem fs = FileSystem.get(URI.create(output.toString()), conf);
        if (fs.exists(output)) {
            fs.delete(output);
        }

        Job wordCountJob = Job.getInstance(conf,"wordcount");

        wordCountJob.setJarByClass(WordCount.class);
        wordCountJob.setMapperClass(WordCountMapper.class);
        wordCountJob.setReducerClass(WordCountReducer.class);




        wordCountJob.setOutputKeyClass(Text.class);
        wordCountJob.setOutputValueClass(IntWritable.class);

        wordCountJob.setInputFormatClass(TextInputFormat.class);
        wordCountJob.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(wordCountJob, "C:\\tly\\in.txt.txt");
        FileOutputFormat.setOutputPath(wordCountJob, output);
        System.exit(wordCountJob.waitForCompletion(true)?0:1);
    }
}
package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;


/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: HdfsTest
 * @time: 2018/7/19 17:35
 * @desc:
 */
public class HdfsTest {
    public static final String HDFS_PATH="hdfs://192.168.86.128:9000/";
    public static Configuration conf;
    public static FileSystem fs;

    @Before
    public void setUp() throws Exception{
        conf=new Configuration();
        fs=FileSystem.get(new URI(HDFS_PATH),conf);
    }

    @Test
    public void isDirectory(){
        Path path=new Path("/tly/tly");
        try {
            boolean result = fs.isDirectory(path);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mkdir(){
        Path path=new Path("hdfs://192.168.86.128:9000/tly/word2");
        try {
            fs.create(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
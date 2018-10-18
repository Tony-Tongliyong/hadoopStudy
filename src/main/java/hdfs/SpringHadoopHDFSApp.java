package hdfs;

import javafx.application.Application;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: SpringHadoopHDFSApp
 * @time: 2018/8/1 19:00
 * @desc:
 */
public class SpringHadoopHDFSApp {
    private ApplicationContext ctx;
    private FileSystem fs;

    @Test
    public  void getBeforHour() throws ParseException {
        String dateTime="2018-08-08-20:00:00";
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            date = sdf.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR) - 1);
            String str=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(calendar.getTime()).toString();
            System.out.println(str);
    }

    @Before
    public void setup(){
        ctx=new ClassPathXmlApplicationContext("beans.xml");
        fs=(FileSystem)ctx.getBean("fileSystem");
    }

    @Test
    public void testMkdirs() throws IOException {
            Path path=new Path("/springhadoop/tly");
            fs.mkdirs(path);
            String[] str=new String[0];
            System.out.println(str.length);
    }

    @Test
    public void test01(){
        String[] str={"a1"};
        List<String[]> array = new ArrayList<String[]>();
        array.add(str);
        System.out.println(array.toArray().toString());
        for(String[] st:array){
            if(Arrays.asList(st).contains("a1")){
                    System.out.println("yes");
                }
        }
        System.out.println((int)'1');
    }

    @Test
    public void test03(){
        Pattern pattern1 = Pattern.compile("[F|J]");
        Matcher matcher1 = pattern1.matcher("F");
        if(matcher1.matches()){
            System.out.println("yes");
        }
    }
    @Test
    public void test04(){
       String a="123,456,789";

        if(Arrays.asList(a.split(",")).contains("123")){
            System.out.println("yes");
            System.out.println(new Date());
        }
    }
    @Test
    public void test02(){
        String b="abcdefghijklmn";
        String str=b.substring(0,b.length());
        System.out.println(str);
        Pattern patternEng = Pattern.compile("[0-9]{4}-[0-9]{3,4}");
        Matcher matcher1 = patternEng.matcher("0571-1234");
        if(matcher1.matches()){
            System.out.println("yes");
        }
    }
    @After
    public void teardown() throws IOException {
        ctx=null;
        fs.close();
    }
}
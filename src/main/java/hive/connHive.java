package hive;


import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: hiveConn
 * @time: 2018/8/28 17:06
 * @desc:
 */
public class connHive {

    private static Properties jdbcProp = new Properties();

    static {
        try {
            InputStream jdbcIn = connHive.class
                    .getClassLoader().getResourceAsStream("conf/jdbc.properties");
            jdbcProp.load(jdbcIn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String driverClass=jdbcProp.getProperty("hive.driverClassName");

    private static String url=jdbcProp.getProperty("hive.url");

    private static String user=jdbcProp.getProperty("hive.user");

    private static String pwd=jdbcProp.getProperty("hive.pwd");

    @Test
    public void conn(){
        Connection conn = null;
        try{
            Class.forName(driverClass);
            conn=DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "desc user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
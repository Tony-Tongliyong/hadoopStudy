package hbase;

import common.ConfigurationManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.security.User;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: ConnHbase
 * @time: 2018/9/3 14:56
 * @desc:
 */
public class ConnHbase {

    public static Configuration configuration;
    private static String krb5File = null;
    private static String userName = null;
    private static String userKeytabFile = null;

    /**
     * 创建hbase表
     */
    @Test
    public void createTable(){
        String tableName ="tlytlh";
        System.out.println("start create table ......");
        System.out.println(configuration.toString());
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            // 如果存在要创建的表，那么先删除，再创建
            if (hBaseAdmin.tableExists(tableName)) {
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println(tableName + " is exist,detele....");
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            tableDescriptor.addFamily(new HColumnDescriptor("column2"));
            tableDescriptor.addFamily(new HColumnDescriptor("column3"));
            hBaseAdmin.createTable(tableDescriptor);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table ......");

    }

    /**
     * 获取所有表
     */
    @Test
    public void getAllTables(){
        List<String> tables = null;
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (admin != null) {
            try {
                HTableDescriptor[] allTable = admin.listTables();
                if (allTable.length > 0)
                    tables = new ArrayList<String>();
                for (HTableDescriptor hTableDescriptor : allTable) {
                    tables.add(hTableDescriptor.getNameAsString());
                    System.out.println(hTableDescriptor.getNameAsString());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(tables.toArray().toString());
    }

    /**
     * 配置登录hbase信息
     * @throws IOException
     */
    @Before
    public  void login() throws IOException {
        //hbase登录信息
        configuration = HBaseConfiguration.create();
        configuration.addResource("conf/hbase.site.xml");
        //判断登录是否有kerberos验证
        if (User.isHBaseSecurityEnabled(configuration)) {
            userName = ConfigurationManager.getProperty(KEYTAB_KERBEROS_USER_NAME);
            userKeytabFile = ConfigurationManager.getProperty(KEYTAB_KERBEROS_USER_KEYTAB_FILE);
            krb5File = ConfigurationManager.getProperty(KEYTAB_KERBEROS_KRB5_FILE);
            authKrb5( krb5File, userName,userKeytabFile);
        }
    }

    public static void authKrb5(String krb5File, String userName, String userKeytabFile){
        //设置jvm启动时krb5的读取路径参数
        System.setProperty("java.security.krb5.conf", krb5File);
        //配置kerberos认证
        Configuration conf = new Configuration();
        conf.setBoolean("hadoop.security.authorization", true);
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation.loginUserFromKeytab(userName, userKeytabFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Succeeded in authenticating through Kerberos!");
    }

}
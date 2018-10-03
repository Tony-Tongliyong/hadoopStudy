package hive;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: data_clean
 * @time: 2018/8/28 16:22
 * @desc:
 */
public class data_clean {

    static String[] str=new String[5];

    public static void createSql() {

        StringBuffer sql = new StringBuffer();
        sql.append("select clean(");
        for(int i = 0;i < str.length; i++) {
            sql.append(str[i]);
            sql.append(",");
        }
        sql.append(")");
        System.out.println(sql);
    }

    public static void main (String[] args){
        createSql();
    }

}
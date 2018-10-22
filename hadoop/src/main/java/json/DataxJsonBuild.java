package json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtil;

import java.io.*;
import java.util.List;
import java.util.Map;


/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: DataxJsonBuild
 * @time: 2018/9/29 13:39
 * @desc:
 */
public class DataxJsonBuild {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(DataxJsonBuild.class);

    static DataxHBaseBean hbaseWriterBean = null;
    static DataxHdfsBean  hdfsReaderBean = null;

    public static void run(String jsonName,String path){
        String reader = reader(jsonName);
        String jsonResult = process(reader);
        write(path,jsonResult);
    }

    private static void write(String fileName,String json){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String process(String reader){
        JSONObject json = JSONObject.parseObject(reader);
        JSONObject dataxJsonContentObject = json.getJSONObject("job").getJSONArray("content").getJSONObject(0);

        JSONObject dataxJsonReader = dataxJsonContentObject.getJSONObject("reader");
        JSONObject dataxJsonWriter = dataxJsonContentObject.getJSONObject("writer");

        JSONObject dataxJsonReaderResult = dataxHdfsReader(dataxJsonReader);
        JSONObject dataxJsonWriterResult = dataxHBaseWriter(dataxJsonWriter);

        json.getJSONObject("job").getJSONArray("content").getJSONObject(0).put("reader", dataxJsonReaderResult);

        json.getJSONObject("job").getJSONArray("content").getJSONObject(0).put("writer", dataxJsonWriterResult);

        return JsonUtil.formatJson(json.toString());
    }

    private static String reader(String JsonName) {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("json/%s.json", JsonName));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;
        //存储
        StringBuilder stringBuffer = new StringBuilder();
        try {
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private static JSONObject dataxHdfsReader(JSONObject dataxJsonReader){
        JSONObject hdfsParameter = dataxJsonReader.getJSONObject("parameter");

        hdfsParameter.put("path",hdfsReaderBean.getPath() == null ? hdfsParameter.get("path"):hdfsReaderBean.getPath());
        hdfsParameter.put("defaultFs",hdfsReaderBean.getDefaultFS() == null ? hdfsParameter.get("defaultFs"):hdfsReaderBean.getDefaultFS());
        hdfsParameter.put("fileType",hdfsReaderBean.getFileType() == null ? hdfsParameter.get("fileType"):hdfsReaderBean.getFileType());
        hdfsParameter.put("fieldDelimiter",hdfsReaderBean.getFieldDelimiter() == null ? hdfsParameter.get("fieldDelimiter"):hdfsReaderBean.getFieldDelimiter());
        hdfsParameter.put("haveKerberos",hdfsReaderBean.getHaveKerberos() == null ? hdfsParameter.get("haveKerberos"):hdfsReaderBean.getHaveKerberos());
        hdfsParameter.put("kerberosPrincipal",hdfsReaderBean.getKerberosPrincipal() == null ? hdfsParameter.get("kerberosPrincipal"):hdfsReaderBean.getKerberosPrincipal());
        hdfsParameter.put("kerberosKeytabFilePath",hdfsReaderBean.getKerberosKeytabFilePath() == null ?hdfsParameter.get("kerberosKeytabFilePath"):hdfsReaderBean.getKerberosKeytabFilePath());
        hdfsParameter.put("encoding",hdfsReaderBean.getEncoding() == null ? hdfsParameter.get("encoding"):hdfsReaderBean.getEncoding());
        hdfsParameter.put("hadoopConfig",hdfsReaderBean.getHadoopConfig() == null ? hdfsParameter.get("hadoopConfig"):hdfsReaderBean.getHadoopConfig());

        Map<Integer,String> maps = hdfsReaderBean.getColumn();
        JSONArray column = new JSONArray();
        int i = 0 ;
        for(Integer key:maps.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index",key);
            jsonObject.put("type","String");
            column.set(i,jsonObject);
            i++;
        }
        hdfsParameter.put("column",column);
        dataxJsonReader.put("parameter",hdfsParameter);
        return dataxJsonReader;
    }

    private static JSONObject dataxHBaseWriter(JSONObject dataxJsonWriter){
        JSONObject hbaseParameter = dataxJsonWriter.getJSONObject("parameter");


        hbaseParameter.put("mode",hbaseWriterBean.getMode() == null ? hbaseParameter.get("mode"):hbaseWriterBean.getMode());
        hbaseParameter.put("encoding",hbaseWriterBean.getEncoding() == null ? hbaseParameter.get("encoding"):hbaseWriterBean.getEncoding());
        hbaseParameter.put("table",hbaseWriterBean.getTable() == null ? hbaseParameter.get("table"):hbaseWriterBean.getTable());

        JSONObject hbaseConfig = hbaseParameter.getJSONObject("hbaseConfig");
        hbaseConfig.put("hbase.zookeeper.quorum",hbaseWriterBean.getHbaseZookeeperQuorum() == null ? hbaseConfig.get("hbase.zookeeper.quorum"):hbaseWriterBean.getHbaseZookeeperQuorum());

        JSONArray column = new JSONArray();
        Map<Integer,String> columns = hbaseWriterBean.getColumn();
        int i = 0 ;
        for(Integer key:columns.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index",key);
            jsonObject.put("name",columns.get(key));
            jsonObject.put("type","String");
            column.set(i,jsonObject);
            i++;
        }
        hbaseParameter.put("column",column);

        JSONArray rowKeyColumn = new JSONArray();
        List rowKeyColumns = hbaseWriterBean.getRowKeyColumn();
        int j = 0 ;
        for(Object id : rowKeyColumns){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index",Integer.parseInt(id.toString()));
            jsonObject.put("type","String");
            rowKeyColumn.set(j,jsonObject);
            j++;
        }
        hbaseParameter.put("rowkeyColumn",rowKeyColumn);
        dataxJsonWriter.put("parameter",hbaseParameter);

        return dataxJsonWriter;
    }

    public static DataxHBaseBean getHbaseWriterBean() {
        return hbaseWriterBean;
    }

    public static void setHbaseWriterBean(DataxHBaseBean hbaseWriterBean) {
        DataxJsonBuild.hbaseWriterBean = hbaseWriterBean;
    }

    public static DataxHdfsBean getHdfsReaderBean() {
        return hdfsReaderBean;
    }

    public static void setHdfsReaderBean(DataxHdfsBean hdfsReaderBean) {
        DataxJsonBuild.hdfsReaderBean = hdfsReaderBean;
    }


    public static void jsonBuildByTable(String dept,String table){
//        DataxHdfsBean dataxHdfsBean = new DataxHdfsBean();
//        dataxHdfsBean.setColumn(Common.getHdfsColumnsByTable(table));
//        dataxHdfsBean.setPath("");
//        dataxHdfsBean.setFileType("text");
//
//        DataxHBaseBean dataxHBaseBean = new DataxHBaseBean();
//        dataxHBaseBean.setColumn(Common.getHbaseColumnsByTable(table));
//        dataxHBaseBean.setRowKeyColumn(Common.getHbaseKeyColumnsByTable(table));
//        dataxHBaseBean.setHbaseZookeeperQuorum(ConfigurationManager.getProperty(HBASE_ZOOKEEPER_QUORUM));
//        dataxHBaseBean.setTable(table);
//
//        DataxJsonBuild.setHbaseWriterBean(dataxHBaseBean);
//        DataxJsonBuild.setHdfsReaderBean(dataxHdfsBean);
//
//        String jsonName = "HdfsToHbase";
//        DataxJsonBuild.run(jsonName,Common.getJsonResultPath(dept,table));
//        logger.info(dept+"."+table+"表json文件生成完毕");
    }



    public static void main(String[] args) {

//        String table = "ajj_hj_sajj_aqcx_info";
//        hbaseWriterBean = new DataxHBaseBean();
//        hdfsReaderBean  = new DataxHdfsBean();
//
//        hbaseWriterBean.setColumn(Common.getHbaseColumnsByTable(table));
//        System.out.println(hbaseWriterBean.getColumn());
//        hbaseWriterBean.setHbaseConfig("");
//        hbaseWriterBean.setRowKeyColumn(Common.getHbaseKeyColumnsByTable(table));
//        System.out.println(hbaseWriterBean.getRowKeyColumn());
//        hdfsReaderBean.setColumn(Common.getHdfsColumnsByTable(table));
//        hdfsReaderBean.setPath("path");

        run("HdfsToHbase","C:\\tly\\ajj_hj_sajj_aqcx_info.json");
    }
}
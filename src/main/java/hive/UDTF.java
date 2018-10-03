package hive;

import com.alibaba.fastjson.JSONArray;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: ExplodeMap
 * @time: 2018/8/15 17:31
 * @desc:
 */

public class UDTF extends GenericUDTF {

    static String str = "{\"pjjg\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"}],\"pjsj\":[{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"C0404\",\"expression\":\"12345\"}],\"qyid\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"}],\"pjdw\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"}],\"shxydc\":[{\"ruleCode\":\"WLA0202\",\"expression\":\"none\"},{\"ruleCode\":\"WLB0107\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0403\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"qymc\":[{\"ruleCode\":\"WLA0202\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"zzjgdm\":[{\"ruleCode\":\"WLA0202\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0402\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"pjwh\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"C0402\",\"expression\":\"none\"}],\"pjyj\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"}]}\n";

    @Override
    public void close() throws HiveException {

    }

    @Override
    public StructObjectInspector initialize(ObjectInspector[] args)
            throws UDFArgumentException {

        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldNames.add("columnName");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldNames.add("ruleType");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);

    }

    public String[] processInputRecord(String ruleType, String columnName, String columnValue, String ruleExpress) {

        Map<String, String> dataMap = new HashMap<String, String>();
        Map<String, Objects> allData = new HashMap<String, Objects>();
        String[] str = {columnName, dataMap.toString()};
        return str;
    }

    @Override
    public void process(Object[] record) throws HiveException {

        HashMap<String, List<HashMap>> columnNameRuleMaps = new HashMap<>();
        String[] columnValue = new String[record.length];
        for(int i = 0;i < record.length;i++){
            columnValue[i] = record[i].toString();
        }
        HashMap hashMap = JSONArray.parseObject(str, HashMap.class);
        for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>) hashMap.entrySet()) {
            String key = entry.getKey();
            List<HashMap> hashMaps = JSONArray.parseArray(entry.getValue().toString(), HashMap.class);
            columnNameRuleMaps.put(key, hashMaps);
        }

        for (HashMap.Entry<String, List<HashMap>> entry : columnNameRuleMaps.entrySet()) {
            String key = entry.getKey();
            List<HashMap> value = entry.getValue();
            for (HashMap<String, String> ruleMap : value) {
                String ruleCode = ruleMap.get(Constants.RULE_CODE);
                String expression = ruleMap.get(Constants.EXPRESSION);
//              forward(result(ruleCode,key,columnValue,expression).toString());
            }
        }
    }

    public static Map<String, String> result(String ruleCode, String ruleType, String columnName, String ruleExpress) {

        try {
            Class<?> rulesClass = Class.forName("hive.Rules");
            Object ruleObject = rulesClass.newInstance();
            Method declaredMethod = rulesClass.getDeclaredMethod(ruleType, String.class, String.class, String.class, Map.class);
            Map<String, String> invoke = (Map<String, String>) declaredMethod.invoke(ruleObject, columnName, "330182199709013739", ruleExpress, new HashMap<String,Objects>());
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
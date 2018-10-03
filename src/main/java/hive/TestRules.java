package hive;


import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: javinjunfeng
 * @Date: 2018/8/20 上午10:27
 * @Version 1.0
 */
public class TestRules {

    static  String str = "{\"pjjg\":[{\"ruleCode\":\"WLC0101\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0201\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0301\"," +
            "\"expression\":\"none\"}]," +
            "\"pjsj\":[{\"ruleCode\":\"WLC0201\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"C0404\"," +
            "\"expression\":\"12345\"}]," +
            "\"qyid\":[{\"ruleCode\":\"WLC0101\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0201\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0301\"," +
            "\"expression\":\"none\"}]," +
            "\"pjdw\":[{\"ruleCode\":\"WLC0101\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0201\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0301\"," +
            "\"expression\":\"none\"}]," +
            "\"shxydc\":[{\"ruleCode\":\"WLA0202\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLB0107\"," +
            "\"expression\":\"none\"}," +
            "{\"ruleCode\":\"WLC0101\"," +
            "\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0403\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"qymc\":[{\"ruleCode\":\"WLA0202\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"zzjgdm\":[{\"ruleCode\":\"WLA0202\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0301\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0402\",\"expression\":\"none\"},{\"ruleCode\":\"LJB0101\",\"expression\":\"none\"}],\"pjwh\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"},{\"ruleCode\":\"C0402\",\"expression\":\"none\"}],\"pjyj\":[{\"ruleCode\":\"WLC0101\",\"expression\":\"none\"},{\"ruleCode\":\"WLC0201\",\"expression\":\"none\"}]}";

    /**
     * json字符串转化为MapList对象
     * @return
     */
    @Test
    public void jsonStrToMapList(){
        HashMap<String, List<HashMap>> columnNameRuleMaps = new HashMap<>();
        HashMap hashMap = JSONArray.parseObject(str, HashMap.class);
        for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>) hashMap.entrySet()) {
            String key = entry.getKey();
            List<HashMap> hashMaps = JSONArray.parseArray(entry.getValue().toString(), HashMap.class);
            columnNameRuleMaps.put(key,hashMaps);
        }

        for (HashMap.Entry<String,List<HashMap>> entry : columnNameRuleMaps.entrySet()){
            String key = entry.getKey();
            System.out.println();
            System.out.println(key);
            List<HashMap> value = entry.getValue();
            for (HashMap<String,String> ruleMap : value){
                String ruleCode = ruleMap.get(Constants.RULE_CODE);
                String expression = ruleMap.get(Constants.EXPRESSION);
                System.out.println(ruleCode);
                System.out.println(expression);
            }
        }
    }


    @Test
    public void testRule(){

        System.out.println(str);
        try {
            Class<?> rulesClass = Class.forName("hive.Rules");
            Object ruleObject = rulesClass.newInstance();
            Method declaredMethod = rulesClass.getDeclaredMethod("WLC0408", String.class, String.class, String.class, Map.class);
            Map<String, String> invoke = (Map<String, String>) declaredMethod.invoke(ruleObject, "pjyj", "330182199709013739", "none", new HashMap<>());
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

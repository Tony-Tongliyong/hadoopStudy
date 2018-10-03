package hive;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: javinjunfeng
 * @Date: 2018/8/20 上午9:42
 * @Version 1.0
 */
public class Rules  {

    public static Pattern pattern = Pattern.compile("^\\n$");

    public static Map<String,String> dataMap = new HashMap<String, String>();

    /**
     * 具体每个规则实现方法
     * @param columeName 传入的字段名
     * @param columnValue 传入字段的值
     * @param ruleExpress 传入规则的表达式
     * @param ruleLevel 传入规则的违反强弱程度
     * @param allData 返回结果：若未违反规则返回null，若违反规则则返回规则详情（Map=>{"ruleName"->"exampleRule","ruleLevel"->"1"}）
     * @return
     */
    public static Map<String,String> exampleRule(String columeName, String columnValue, String ruleExpress, Integer ruleLevel, Map<String,Objects> allData){

        return null;
    }

    /**
     * WLA0101 用于判定数据是否存在
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLA0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if ("".equals(removedSpaceValue)) {
            dataMap.put("ruleName","WLA0101");
            return dataMap;
        } else if (matcher.matches()) {
            dataMap.put("ruleName","WLA0101");
            return dataMap;
        }
        return null;
    }

    /**
     * WLA0201 校验个人用户关联字段
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLA0201(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (matcher.matches()) {
            dataMap.put("ruleName","WLA0201");
            return dataMap;
        } else if ("".equals(columnValue)) {
            dataMap.put("ruleName","WLA0201");
            return dataMap;
        }
        return null;
    }

    /**
     * WLA0202 校验企业用户关联字段
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLA0202(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (matcher.matches()) {
            dataMap.put("ruleName","WLA0202");
            return dataMap;
        } else if ("".equals(columnValue)) {
            dataMap.put("ruleName","WLA0202");
            return dataMap;
        } else {
            return null;
        }
    }

    /**
     * WLB0101 主要对各类字段信息长度进行判定,默认是1个字符
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                try {
                    if (removedSpaceValue.length() < Integer.valueOf(ruleExpress)) {
                        dataMap.put("ruleName","WLB0101");
                        return dataMap;
                    }
                } catch (Exception e) {
                    if (removedSpaceValue.length() < 2) {
                        dataMap.put("ruleName","WLB0101");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLB0102 如果电话号码长度不在11,12,13位则判定错误，对联系电话长度进行判定
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0102(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 11 && colValuelen != 13 && colValuelen != 12) {
                    dataMap.put("ruleName","WLB0102");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0103 主要对手机号的长度进行判定，手机号码长度位11位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0103(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                if(columnValue.length() != 11){
                    dataMap.put("ruleName","WLB0103");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0104 主要对固定电话长度进行判定，固话长度为4位区号-7/8位号码（12或13位），暂时决定联系方式长度判定区间为12-13位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0104(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                if(columnValue.length() != 12 && columnValue.length() != 13 ){
                    dataMap.put("ruleName","WLB0104");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0105 用于判定身份证长度,15，18位
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0105(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int len = removedSpaceValue.length();
                if (len != 18 && len != 15) {
                    dataMap.put("ruleName","WLB0105");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0106 对组织机构代码长度进行判定,不等于9的错误，去掉-等于9
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0106(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.replace("-", "").length();
                if (colValuelen != 9) {
                    dataMap.put("ruleName","WLB0106");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0107 对社会信用代码长度进行判定,不等于18的错误，其他的返回null
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0107(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 18) {
                    dataMap.put("ruleName","WLB0107");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0108 对邮政编码长度不满足六位数字的进行判定
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0108(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnValue != "") {
                int len = removedSpaceValue.length();
                if (len != 6 ) {
                    dataMap.put("ruleName","WLB0108");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0109 主要对工商注册号的长度进行判定，检验长度设置为15位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0109(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 15) {
                    dataMap.put("ruleName","WLB0109");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0110 对行政区划长度进行判定,不等于(6,9,12)位数字的错误
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0110(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen == 6 || colValuelen == 9 || colValuelen == 12) {
                    return null;
                } else {
                    dataMap.put("ruleName","WLB0110");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0111 对残疾人证件长度进行判定,不满足20或22位的错误
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0111(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 20 && colValuelen != 22) {
                    dataMap.put("ruleName","WLB0111");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0112 主要对纳税人识别号的长度进行判定，检验长度设置为15位、18位、19位、20位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0112(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 15 && colValuelen != 18 && colValuelen != 19 && colValuelen != 20) {
                    dataMap.put("ruleName","WLB0112");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0113 主要对社会保障号的长度进行判定，检验长度设置为18位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0113(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 18) {
                    dataMap.put("ruleName","WLB0113");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0114 主要对高等院校代码的长度进行判定，检验长度设置为4位或5位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0114(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 4 && colValuelen != 5) {
                    dataMap.put("ruleName","WLB0114");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0115 主要对高等院校专业代码的长度进行判定，检验长度设置为6位。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0115(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int colValuelen = removedSpaceValue.length();
                if (colValuelen != 6) {
                    dataMap.put("ruleName","WLB0115");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0201 数值异常
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入参数格式为(例："param<3")
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0201(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                engine.put("param",removedSpaceValue);
                Boolean eval;
                try {
                    eval = (Boolean)engine.eval(ruleExpress);
                    if(eval){
                        return null;
                    }else{
                        dataMap.put("ruleName","WLB0201");
                        return dataMap;
                    }
                } catch (ScriptException e) {
                    dataMap.put("ruleName","WLB0201");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLB0202 字段超出给定范围
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入参数格式为(例： "xi,xb,xc")
     * @param allData
     * @return
     */
    public static Map<String,String> WLB0202(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                if (!Arrays.asList(ruleExpress.split(",")).contains(removedSpaceValue)) {
                    dataMap.put("ruleName","WLB0202");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0101 校验非法人和非自然人名称字符含有空格
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                if (columnValue.indexOf(" ")!=-1) {
                    dataMap.put("ruleName","WLC0101");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0102 校验法人名称含有空格（主要校验法人名称是否含有空格，若为英文名称含有空格不特殊处理，若为非全英文名称含有空格需要去除）
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0102(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("^[a-zA-Z]+$");
                String removedSpaceValue = columnValue.replace(" ", "");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if (matcher1.matches()) {
                    return null;
                } else {
                    if (columnValue.indexOf(" ")!=-1) {
                        dataMap.put("ruleName","WLC0102");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0103 对姓名格式进行校验（主要校验姓名是否含有空格，若为英文名称含有空格不特殊处理，若为非全英文名称含有空格需要去除）
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0103(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("^[a-zA-Z]+$");
                String removedSpaceValue = columnValue.replace(" ", "");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if (matcher1.matches()) {
                    return null;
                } else {
                    if (columnValue.indexOf(" ")!=-1) {
                        dataMap.put("ruleName","WLC0103");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0104 主要对字段首尾进行检验，是否含有空格，若含有空格，则不规范。
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0104(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                if (columnValue.indexOf(" ") == 0) {
                    dataMap.put("ruleName","WLC0104");
                    return dataMap;
                }else {
                    if(columnValue.lastIndexOf(" ") == columnValue.length()-1){
                        dataMap.put("ruleName","WLC0104");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * /WLC0201 含有\n和\t
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0201(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern pattern1 = Pattern.compile("[\\n\\t]");
                Matcher matcher1 = pattern1.matcher(columnValue);
                if (matcher1.find()) {
                    dataMap.put("ruleName","WLC0201");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0301 判断数据是否存在特殊字符，非中文英文数字都算异常字符，#是处理地址的
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0301(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternParam = Pattern.compile("[\\$\\%\\^&\\<\\>\\~`！\\+\\=\\|？……。\\.\\_、\\\\▲；‘’“”/,，]");
                Matcher matcher3 = patternParam.matcher(removedSpaceValue);
                if (matcher3.find()) {
                    dataMap.put("ruleName","WLC0301");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0302 校验数据含有小写字母，若有，返回dataMap，反之返回null
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0302(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("[a-z]");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if (matcher1.find()) {
                    dataMap.put("ruleName","WLC0302");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0303 校验数据含有特定字符,若有,返回dataMap,反之，返回null
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入特定字符串
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0303(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("["+ruleExpress+"]");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if (matcher1.find()) {
                    dataMap.put("ruleName","WLC0303");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0304 校验数据含有特定字符以外的字符，若有，返回dataMap，反之返回null
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0304(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("[^"+ruleExpress+"]");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if(matcher1.find()){
                    dataMap.put("ruleName","WLC0304");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0401只校验18位的身份证编码是否规范，其他的不校验
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0401(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int areaNum = Integer.valueOf(removedSpaceValue.substring(0,2));
                int[] string1 = {11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91};
                if(!isInArray(string1,areaNum)){
                    dataMap.put("ruleName","WLC0401");
                    return dataMap;
                }else {
                    if (removedSpaceValue.length() == 15){
                        //15位身份证号判定
                        int yearNum =  Integer.valueOf(removedSpaceValue.substring(6,8));
                        String patternBornStr = "";
                        boolean flag=(yearNum+1900) % 4 == 0 || (yearNum+1900) % 100 == 0 && (yearNum+1900) % 4 == 0;
                        if(flag){
                            patternBornStr = "[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";
                        }else{
                            patternBornStr = "[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";
                        }
                        Pattern patternBorn = Pattern.compile(patternBornStr);
                        Matcher matcher1 = patternBorn.matcher(removedSpaceValue);
                        if (matcher1.matches()){
                            return null;
                        }else {
                            dataMap.put("ruleName","WLC0401");
                            return dataMap;
                        }
                    }else if (removedSpaceValue.length() == 18){
                        int yearNum =  Integer.valueOf(removedSpaceValue.substring(6,10));
                        String patternBornStr = "";
                        boolean flag=yearNum % 4 == 0 || yearNum % 100 == 0 && yearNum%4 == 0;
                        if(flag){
                            patternBornStr = "[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";
                        }else {
                            patternBornStr = "[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";
                        }
                        Pattern patternBorn = Pattern.compile(patternBornStr);
                        Matcher matcher1 = patternBorn.matcher(removedSpaceValue);
                        if (matcher1.matches()){
                            int S = (Integer.valueOf(removedSpaceValue.substring(0,1)) + Integer.valueOf(removedSpaceValue.substring(10,11))) * 7 + (Integer.valueOf(removedSpaceValue.substring(1,2)) + Integer.valueOf(removedSpaceValue.substring(11,12))) * 9 + (Integer.valueOf(removedSpaceValue.substring(2,3)) + Integer.valueOf(removedSpaceValue.substring(12,13))) * 10 + (Integer.valueOf(removedSpaceValue.substring(3,4)) + Integer.valueOf(removedSpaceValue.substring(13,14))) * 5 + (Integer.valueOf(removedSpaceValue.substring(4,5)) + Integer.valueOf(removedSpaceValue.substring(14,15))) * 8 + (Integer.valueOf(removedSpaceValue.substring(5,6)) + Integer.valueOf(removedSpaceValue.substring(15,16))) * 4 + (Integer.valueOf(removedSpaceValue.substring(6,7)) + Integer.valueOf(removedSpaceValue.substring(16,17))) * 2 + Integer.valueOf(removedSpaceValue.substring(7,8)) * 1 + Integer.valueOf(removedSpaceValue.substring(8,9)) * 6 + Integer.valueOf(removedSpaceValue.substring(9,10)) * 3;
                            int Y = S % 11;
                            String JYM = "10X98765432";
                            //判断校验位
                            int M =Integer.valueOf(JYM.substring(Y,Y+1));
                            //#检测ID的校验位
                            if (M == Integer.valueOf(removedSpaceValue.substring(17,18))){
                                return null;
                            }else {
                                dataMap.put("ruleName","WLC0401");
                                return dataMap;
                            }
                        }else {
                            dataMap.put("ruleName","WLC0401");
                            return dataMap;
                        }
                    }else {
                        dataMap.put("ruleName","WLC0401");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 判断数值是否在集合中
     * @param arr
     * @param num
     * @return
     */
    public static boolean isInArray(int[] arr, int num) {
        if (null == arr) {
            return false;
        }
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * WLC0402 校验组织机构代码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0402(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if(!matcher.matches()){
            if(columnName!= ""){
                Pattern pattern1 = Pattern.compile("[0-9A-Z]{8}-[0-9A-Z]$");
                Matcher matcher1 = pattern1.matcher(removedSpaceValue);
                if(matcher1.matches()){
                    String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    int[] c = new int[8];
                    int temp;
                    for(int i = 0;i < 8;i++){
                        temp = columnValue.charAt(i);
                        c[i] = str.indexOf(temp);
                    }
                    int[] w = {3,7,9,10,5,8,4,2};
                    int cw = 0;
                    for(int i = 0;i < 8;i++){
                        cw+= c[i] * w[i];
                    }
                    int res = 11 - cw % 11;
                    String str1 = "0123456789X";
                    if(str1.charAt(res) == columnValue.charAt(9)){
                        return null;
                    }else{
                        dataMap.put("ruleName","WLC0402");
                        return dataMap;
                    }
                } else{
                    dataMap.put("ruleName","WLC0402");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0403 校验统一社会信用代码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0403(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if(!matcher.matches()){
            if(columnName != "") {
                Pattern pattern1=Pattern.compile("^[0-9ANY]{1}[123459]{1}[0-9]{6}[0-9A-Z]{8}[0-9X]{1}[^IOZSV]{1}$");
                Matcher matcher1=pattern1.matcher(removedSpaceValue);
                String str="0123456789ABCDEFGHJKLMNPQRTUWXY";
                if(matcher1.matches()){
                    //每个字符值
                    int[] c = new int[18];
                    char temp;
                    for(int i=0;i < columnValue.length();i++){
                        temp = columnValue.charAt(i);
                        c[i] = str.indexOf(temp);
                    }
                    //每个字符值对应的加权因子
                    int[] w = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
                    //字符值乘加权因子，并相加
                    int cw = 0;
                    for(int i = 0;i < 17;i++){
                        cw+= c[i] * w[i];
                    }
                    //计算第十八位校验码的值
                    int res = 31 - cw % 31;
                    //判断计算结果与原数据值是否相同
                    if(res == c[17]){
                        return null;
                    }else{
                        dataMap.put("ruleName","WLC0403");
                        return dataMap;
                    }
                }else{
                    dataMap.put("ruleName","WLC0403");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0404 校验邮政编码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0404(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if(!matcher.matches()){
            if(columnName!= ""){
                Pattern pattern1 = Pattern.compile("^[0-9]{6}$");
                Matcher matcher1 = pattern1.matcher(removedSpaceValue);
                if(matcher1.matches()){
                    return null;
                }else{
                    dataMap.put("ruleName","WLC0404");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0405 校验工商注册号编码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0405(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if(!matcher.matches()){
            if(columnName != "") {
                Pattern pattern1 = Pattern.compile("[0-9]{15}");
                Matcher matcher1 = pattern1.matcher(removedSpaceValue);
                if(matcher1.matches()){
                    int[] c=new int[15];
                    char temp;
                    for(int i = 0;i < removedSpaceValue.length();i++){
                        temp = removedSpaceValue.charAt(i);
                        c[i] = temp - 48;
                    }
                    if(operation(10,c,0) % 10 == 1){
                        return null;
                    }else{
                        dataMap.put("ruleName","WLC0405");
                        return dataMap;
                    }
                }else{
                    dataMap.put("ruleName","WLC0405");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * 工商注册号编码运算公式
     * @param p p参数
     * @param c 传入数组
     * @param j j参数
     * @return
     */
    public static int operation(int p,int[] c,int j){
        int result;
        result = p % 11 + c[j];
        if(j == 14){
            return result;
        }
        else {
            //如果余数为0则用10代替
            p = ((result % 10 == 0)?10:(result % 10)) * 2;
            j++;
            return operation(p, c, j);
        }
    }

    /**
     * WLC0406 校验行政区划代码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入参数（例："12,23"）
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0406(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if(!matcher.matches()){
            if(columnName != ""){
                int len=columnValue.length();
                if(len == 6 && XZRules(removedSpaceValue,ruleExpress.split(","))) {
                    return null;
                }
                if(len == 9 && XZRules(removedSpaceValue.substring(0,6),ruleExpress.split(","))){
                    if(Integer.parseInt(removedSpaceValue.substring(6,9)) <= 399){
                        return null;
                    }else{
                        dataMap.put("ruleName","WLC0406");
                        return dataMap;
                    }
                }
                if(len == 12 && XZRules(removedSpaceValue.substring(0,6),ruleExpress.split(","))){
                    if(Integer.parseInt(removedSpaceValue.substring(6,9)) <= 599){
                        int i=Integer.parseInt(removedSpaceValue.substring(9,12));
                        if(i <= 599 && i != 498 && i != 598){
                            return null;
                        }else{
                            dataMap.put("ruleName","WLC0406");
                            return dataMap;
                        }
                    }else{
                        dataMap.put("ruleName","WLC0406");
                        return dataMap;
                    }
                }
                else{
                    dataMap.put("ruleName","WLC0406");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * 前六位行政区代码规则
     * @param columnValue
     * @param ruleExpress
     * @return
     */
    public static boolean XZRules(String columnValue,String[] ruleExpress){
        String[] XZArea = {"11","12","13","14","15","21","22","23","31","32","33","34","35","36","37","41","42","43","44","45","46"
                ,"50","51","52","53","54","61","62","63","64","65","71","81","82"};
        String str=columnValue.substring(0,2);
        int str1=Integer.parseInt(columnValue.substring(2,4));
        if(Arrays.asList(ruleExpress).contains(str)){
            if(str1 <= 70 || str1 == 90){
                return true;
            }
            return  false;
        }else{
            if(Arrays.asList(XZArea).contains(str)){
                if(str1 <= 70 || str1 == 90){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * WLC0407 校验残疾人证件代码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0407(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                int len = removedSpaceValue.length();
                if(len == 20){
                    return WLC0407_20(columnName,removedSpaceValue,ruleExpress, allData);
                }else{
                    if(len == 22){
                        Map<String,String> map = WLC0407_20(columnName,removedSpaceValue,ruleExpress, allData);
                        String lastTWo = removedSpaceValue.substring(20,22);
                        Pattern pattern1 = Pattern.compile("[B][1-9]");
                        Matcher matcher1 = pattern1.matcher(lastTWo);
                        if(map == null){
                            if(matcher1.matches()) {
                                return null;
                            }else{
                                dataMap.put("ruleName","WLC0407");
                                return dataMap;
                            }
                        }
                        return map;
                    }
                    dataMap.put("ruleName","WLC0407");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0407传入前20位数字验证
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0407_20(String columnName,String columnValue,String ruleExpress,Map<String,Objects> allData){
        String ID=columnValue.substring(0,18);
        //校验身份证是否正确
        Map<String,String> map = WLC0401(columnName, ID,ruleExpress,  allData);
        if(map == null) {
            String str = columnValue.substring(18, 20);
            Pattern pattern1 = Pattern.compile("[1-7][1-4]");
            Matcher matcher1 = pattern1.matcher(str);
            if (!matcher1.matches()) {
                dataMap.put("ruleName","WLC0407");
                return dataMap;
            } else{
                return null;
            }
        } else {
            dataMap.remove("ruleName");
            dataMap.put("ruleName","WLC0407");
            return dataMap;
        }
    }
    /**
     * WLC0408 校验纳税人识别号编码规则
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0408(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        Map<String,String> map = null;
        if(!matcher.matches()){
            if(columnName != "") {
                int len = removedSpaceValue.length();
                //1.纳税人为18位统一社会信用代码
                if (len == 18) {
                    return WLC0408_18(columnName, removedSpaceValue, ruleExpress, allData);
                } else {
                    //2、纳税人为身份证+2位顺序码
                    if (len == 20 || len == 17) {
                        String ID = removedSpaceValue.substring(0, len - 2);
                        map = WLC0401(columnName, ID, ruleExpress,  allData);
                        if (map != null) {
                            return WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
                        }
                    } else {
                        //3.纳税人为L+身份证（或统一社会信用代码）
                        if (len == 19 && removedSpaceValue.charAt(0) == 'L') {
                            return WLC0408_19(columnName, removedSpaceValue, ruleExpress,  allData);
                        } else {
                            //4.由“F” “操作员所在税务机关的6位行政区划码” “3位纳税人居民身份所在国家或地区代码” “5位顺序码”组成。
                            //以军官证、士兵证为有效身份证明的自然人，其纳税人识别号由“J” 行政区划码 8位顺序码。
                            Pattern pattern1 = Pattern.compile("^[F|J][0-9]{14}$");
                            Matcher matcher1 = pattern1.matcher(removedSpaceValue);
                            if (!matcher1.matches()) {
                                try {
                                    //15位验证码，验证是否符合身份证
                                    map = WLC0401(columnName, columnValue, ruleExpress,  allData);
                                } catch (Exception e) {
                                    return WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
                                }
                                if (map != null) {
                                    return WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0408 传入值为18位返回结果集
     * @param columnName
     * @param removedSpaceValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0408_18(String columnName, String removedSpaceValue, String ruleExpress,Map<String,Objects> allData){
        Map<String,String> map=WLC0403(columnName, removedSpaceValue, ruleExpress,  allData);
        if (map != null) {
            try {
                map = WLC0401(columnName, removedSpaceValue, ruleExpress,  allData);
            } catch (Exception e) {
                map.put("ruleName","WLC0408");
            }
            if (map != null) {
                char c = removedSpaceValue.charAt(0);
                //中国护照自然人
                if (c == 'C') {
                    if (!("156").equals(removedSpaceValue.substring(5, 8))) {
                        WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
                    }
                } else {
                    //W外国护照自然人，H（香港），M（澳门），T（台湾）
                    Pattern pattern1 = Pattern.compile("^[WHMT][0-9]{17}");
                    Matcher matcher1 = pattern1.matcher(removedSpaceValue);
                    if (!matcher1.matches()) {
                        WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0408传入值为19位返回结果集
     * @param columnName
     * @param removedSpaceValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0408_19(String columnName, String removedSpaceValue, String ruleExpress,Map<String,Objects> allData) {
        Map<String,String> map;
        String str = removedSpaceValue.substring(1, 19);
        try {
            //测试是否符合身份证规则
            map=  WLC0401(columnName, str, ruleExpress,  allData);
            if (map != null) {
                return WLC0408Result(columnName, removedSpaceValue, ruleExpress,  allData);
            }
        } catch (Exception e) {
            //测试是否符合统一社会信用代码规则
            map = WLC0403(columnName, str, ruleExpress,  allData);
            if (map != null) {
                return WLC0408Result(columnName, removedSpaceValue, ruleExpress, allData);
            }
        }
        return null;
    }

    /**
     * WLC0408 返回值
     * @param columnName
     * @param removedSpaceValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0408Result(String columnName, String removedSpaceValue, String ruleExpress,Map<String,Objects> allData) {
        //验证身份证后需要删除datamap中WLC0401
        dataMap.remove("ruleName");
        dataMap.put("ruleName","WLC0408");
        return dataMap;
    }


    /**
     * WLC0501 判断邮箱是否为*@*.*格式,满足邮箱格式则返回FALSE
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0501(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("^[0-9a-zA-Z_.]{0,19}@[0-9a-zA-Z]{1,13}\\.[com,cn,net]{1,3}|^[0-9a-zA-Z_.]{0,19}@[0-9a-zA-Z]{1,13}\\.[com,cn,net]{1,3}\\.[com,cn,net]{1,3}$");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if(!matcher1.matches()){
                    dataMap.put("ruleName","WLC0501");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0502 校验下发文号格式,文号中括号格式应为〔〕,存在〔〕就返回null，否则就是错误
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0502(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("([^\\[\\]\\【\\】（）\\(\\)\\〔\\〕]*[\\[\\【（\\(\\〔][^\\[\\]\\【\\】（）\\(\\)\\〔\\〕]+[\\]\\】）\\)\\〕][^\\[\\]\\【\\】（）\\(\\)\\〔\\〕]*)");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if(!matcher1.matches()){
                    dataMap.put("ruleName","WLC0502");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0503校验联系电话格式，对联系方式格式判断（手机号码、固定号码格式校验）（固定电话格式（区号-（7或8）号码），手机号码编码规则校验）
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0503(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("\\d{3,4}-\\d{7,8}");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                Pattern patternEng1 = Pattern.compile("^1\\d{10}");
                Matcher matcher2 = patternEng1.matcher(removedSpaceValue);
                if(matcher1.matches()){
                    return null;
                }else if(matcher2.matches()){
                    dataMap.put("ruleName","WLC0503");
                    return dataMap;
                }
                else{
                    dataMap.put("ruleName","WLC0503");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * WLC0504 校验手机号格式
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入参数为（例："133,149"）
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0504(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        String[] MAC=new String[]{"133","149","153","173","177","180","181","189","199","130","131",
                "132","145","155","156","166","171","175","176","185","186","134","135",
                "136","137","138","139","147","150","151","152","157","158","159","178",
                "182","183","184","187","188","198"};
        String[] MACs;
        try {
            MACs = ("".equals(ruleExpress)) ? MAC : (ruleExpress.split(","));
        }catch (Exception e){
            dataMap.put("ruleName","WLC0504");
            return dataMap;
        }
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("^1[0-9]{10}$");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                if(matcher1.matches()){
                    String mac = removedSpaceValue.substring(0, 3);
                    if (Arrays.asList(MACs).contains(mac)) {
                        return null;
                    }else {
                        dataMap.put("ruleName","WLC0504");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0505 校验联系电话格式，对联系方式格式判断（手机号码、固定号码格式校验）（固定电话格式（区号-（7或8）号码），手机号码编码规则校验）
     * @param columnName
     * @param columnValue
     * @param ruleExpress 传入参数为（例："0571,0572"）
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0505(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        String[] areaNumArray={"0571","0572","0573","0574","0575","0576","0577","0578","0579","0570","0580"};
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern patternEng = Pattern.compile("^[0]\\d{2,3}-\\d{7,8}$");
                Matcher matcher1 = patternEng.matcher(removedSpaceValue);
                String areaNum=removedSpaceValue.substring(0,4);
                if(matcher1.matches()){
                    //判断传入参数是否包含区域号
                    try {
                        if (Arrays.asList(ruleExpress.split(",")).contains(areaNum)) {
                            return null;
                        } else {
                            if (!Arrays.asList(areaNumArray).contains(areaNum)) {
                                dataMap.put("ruleName","WLC0505");
                                return dataMap;
                            }
                        }
                    }catch (Exception e){
                        dataMap.put("ruleName","WLC0505");
                        return dataMap;
                    }
                }else{
                    dataMap.put("ruleName","WLC0505");
                    return dataMap;
                }
            }
        }
        return null;
    }

    /**
     * 验证日期是否正确
     * @param dateString
     * @return
     */
    public static boolean validateDate(String dateString){
        //得到年月日
        String[] array = dateString.split("-");
        int year = Integer.valueOf(array[0]);
        int month = Integer.valueOf(array[1]);
        int day = Integer.valueOf(array[2]);
        if(month<1 || month>12){	return false;}
        int[] monthLengths = new int[]{ 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(isLeapYear(year)){
            monthLengths[2] = 29;
        }else{
            monthLengths[2] = 28;
        }
        int monthLength = monthLengths[month];
        if(day<1 || day>monthLength){
            return false;
        }
        return true;
    }
    /** 是否是闰年 */
    private static boolean isLeapYear(int year){
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ;
    }

    /**
     * 验证时间是否准确
     * @param timeString
     * @return
     */
    public static boolean validateTime(String timeString){
        String[] array = timeString.split(":");
        int hour = Integer.parseInt(array[0]);
        int minute = Integer.parseInt(array[1]);
        int second = Integer.parseInt(array[2]);
        if(hour > 23 || minute > 59 || second > 59){
            return false;
        }
        return true;
    }
    /**
     * WLC0506 时间格式不规范
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0506(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Pattern pattern2 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern pattern3 = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(columnValue);
        String removedSpaceValue = columnValue.replace(" ", "");
        if (!matcher.matches()) {
            if (columnName != "") {
                //默认格式为：yyyy-MM-dd hh:mm:ss
                Matcher matcher1 = pattern1.matcher(columnValue);
                Matcher matcher2 = pattern2.matcher(removedSpaceValue);
                Matcher matcher3 = pattern3.matcher(removedSpaceValue);
                if (matcher1.matches()) {
                    if (!validateDate(columnValue.substring(0, 10)) || !validateTime(columnValue.substring(11,19))) {
                        dataMap.put("ruleName","WLC0506");
                        return dataMap;
                    }
                }
                //默认格式为：yyyy-MM-dd
                else {
                    if (matcher2.matches() ) {
                        if (!validateDate(removedSpaceValue)) {
                            dataMap.put("ruleName","WLC0506");
                            return dataMap;
                        }
                    } else {
                        //默认格式为hh:mm:ss
                        if (matcher3.matches() && validateTime(removedSpaceValue)) {
                            return null;
                        } else {
                            dataMap.put("ruleName","WLC0506");
                            return dataMap;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLC0507 校验URL格式
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0507(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                //protocol类型
                String[] array = {"file","ftp","gopher","http","https",
                        "mailto","MMS","ed2k","Flashget","thunder"};
                Map<String,Integer> arrayMap=new HashMap<>(10);
                String protocol;
                try {
                    protocol= removedSpaceValue.substring(0, removedSpaceValue.indexOf(":"));
                }catch (Exception e){
                    dataMap.put("ruleName","WLC0507");
                    return dataMap;
                }
                if(Arrays.asList(array).contains(protocol)){
                    //protocol对应 / 数量
                    for(int i=0;i<array.length;i++){
                        if(i == 5) {
                            arrayMap.put(array[i], 0);
                        }
                        if(i == 0) {
                            arrayMap.put(array[i], 3);
                        }
                        else{
                            arrayMap.put(array[i],2);
                        }
                    }
                    int num=arrayMap.get(protocol);
                    StringBuffer str=new StringBuffer();
                    str.append("^[/]{");
                    str.append(num);
                    str.append("}\\S+[:[0-9]+]?[/\\S+]*[/;[a-zA-Z0-9]+]?[?\\S+]*[#[a-zA-Z]*]?");
                    Pattern pattern1 = Pattern.compile(str.toString());
                    Matcher matcher1 = pattern1.matcher(removedSpaceValue.substring(removedSpaceValue.indexOf(":")+1,removedSpaceValue.length()));
                    if(!matcher1.matches()){
                        dataMap.put("ruleName","WLC0507");
                        return dataMap;
                    }
                }else{
                    dataMap.put("ruleName","WLC0507");
                    return dataMap;
                }
            }
        }
        return null;

    }

    /**
     * 校验数值类字段格式
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    public static Map<String,String> WLC0508(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {

        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(columnValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Pattern pattern1 = Pattern.compile("^[0-9]*$");
                Matcher matcher1 = pattern1.matcher(removedSpaceValue);{
                    if(!matcher1.matches()){
                        dataMap.put("ruleName","WLC0508");
                        return dataMap;
                    }
                }
            }
        }
        return null;
    }

    /**
     * WLD0101 校验主键重复
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验主键重复
    public static Map<String,String> WLD0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * WLD0201 校验记录重复
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验记录重复
    public static Map<String,String> WLD0201(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * LJA0101 日期A小于日期B
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 日期A小于日期B
    public static Map<String,String> LJA0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }
    /**
     * LJA0102 日期A大于日期B
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 日期A大于日期B
    public static Map<String,String> LJA0102(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * LJA0103 日期A等于日期B
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 日期A等于日期B
    public static Map<String,String> LJA0103(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * LJA0104 日期A小于当前时间
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 日期A小于当前时间
    public static Map<String,String> LJA0104(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {
                Date now = new Date();

            }
        }
        return null;
    }

    /**
     * LJA0201 校验身份证与性别
     * @param columnNameA
     * @param columnValueA
     * @param columnNameB
     * @param columnValueB
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验身份证与性别
    public static Map<String,String> LJA0201(String columnNameA, String columnValueA,String columnNameB,String columnValueB ,String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValueA = columnValueA.replace(" ", "");
        Matcher matcherA = pattern.matcher(removedSpaceValueA);
        String removedSpaceValueB = columnValueB.replace(" ", "");
        Matcher matcherB = pattern.matcher(removedSpaceValueB);
        if (!matcherA.matches() && !matcherB.matches() ) {
            if (columnNameA != "" && columnNameB != "") {
                String num=removedSpaceValueA.substring(16,17);
                String gender;
                int num1=Integer.parseInt(num);
                if(num1 % 2 == 0){
                    gender="女";
                }else{
                    gender="男";
                }
                if(gender.equals(removedSpaceValueB)){
                    return null;
                }else{
                    dataMap.put("JLA0201","0");
                    return dataMap;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * LJA0202 校验身份证与出生日期
     * @param columnNameA
     * @param columnValueA
     * @param columnNameB
     * @param columnValueB
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验身份证与出生日期
    public static Map<String,String> LJA0202(String columnNameA, String columnValueA,String columnNameB,String columnValueB ,String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValueA = columnValueA.replace(" ", "");
        Pattern patternA = Pattern.compile("^\\n$");
        Matcher matcherA = patternA.matcher(removedSpaceValueA);
        String removedSpaceValueB = columnValueB.replace(" ", "");
        Pattern patternB = Pattern.compile("^\\n$");
        Matcher matcherB = patternB.matcher(removedSpaceValueB);
        if (!matcherA.matches() && !matcherB.matches() ) {
            if (columnNameA != "" && columnNameB != "") {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat(columnValueB);
                Date dateA;
                Date dateB;
                try {
                    dateA=simpleDateFormat.parse(columnValueA.substring(8,16));
                    dateB=simpleDateFormat.parse(columnValueB);
                } catch(ParseException e) {
                    dataMap.put("JLA0202","0");
                    return dataMap;
                }
                if(dateA == dateB){
                    return null;
                }else{
                    dataMap.put("JLA0202","0");
                    return dataMap;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * LJA0203 校验统一社会信用代码与组织机构代码
     * @param columnNameA
     * @param columnValueA
     * @param columnNameB
     * @param columnValueB
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验统一社会信用代码与组织机构代码
    public static Map<String,String> LJA0203(String columnNameA, String columnValueA,String columnNameB,String columnValueB ,String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValueA = columnValueA.replace(" ", "");
        Matcher matcherA = pattern.matcher(removedSpaceValueA);
        String removedSpaceValueB = columnValueB.replace(" ", "");
        Matcher matcherB = pattern.matcher(removedSpaceValueB);
        if (!matcherA.matches() && !matcherB.matches() ) {
            if (columnNameA != "" && columnNameB != "") {
                if(columnValueA.substring(8,16).equals(columnValueB.substring(0,8))){
                    return null;
                }else{
                    dataMap.put("JLA0203","0");
                    return dataMap;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * LJA0204 数值字段A小于数值字段B
     * @param columnNameA
     * @param columnValueA
     * @param columnNameB
     * @param columnValueB
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 数值字段A小于数值字段B
    public static Map<String,String> LJA0204(String columnNameA, String columnValueA,String columnNameB,String columnValueB ,String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValueA = columnValueA.replace(" ", "");
        Matcher matcherA = pattern.matcher(removedSpaceValueA);
        String removedSpaceValueB = columnValueB.replace(" ", "");
        Matcher matcherB = pattern.matcher(removedSpaceValueB);
        if (!matcherA.matches() && !matcherB.matches() ) {
            if (columnNameA != "" && columnNameB != "") {
                if(Long.parseLong(columnValueA) < Long.parseLong(columnValueB) ){
                    return null;
                }else{
                    dataMap.put("JLA0204","0");
                    return dataMap;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * LJA0205 数值字段A大于数值字段B
     * @param columnNameA
     * @param columnValueA
     * @param columnNameB
     * @param columnValueB
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 数值字段A大于数值字段B
    public static Map<String,String> LJA0205(String columnNameA, String columnValueA,String columnNameB,String columnValueB ,String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValueA = columnValueA.replace(" ", "");
        Matcher matcherA = pattern.matcher(removedSpaceValueA);
        String removedSpaceValueB = columnValueB.replace(" ", "");
        Matcher matcherB = pattern.matcher(removedSpaceValueB);
        if (!matcherA.matches() && !matcherB.matches() ) {
            if (columnNameA != "" && columnNameB != "") {
                if(Long.parseLong(columnValueA) > Long.parseLong(columnValueB) ){
                    return null;
                }else{
                    dataMap.put("JLA0205","0");
                    return dataMap;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * LJA0206 校验字段A与字段B的一致性
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验字段A与字段B的一致性
    public static Map<String,String> LJA0206(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Pattern pattern = Pattern.compile("^\\n$");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * LJB0101 校验个人数据关联性
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验个人数据关联性
    public static Map<String,String> LJB0101(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /**
     * LJB0102 校验企业数据关联性
     * @param columnName
     * @param columnValue
     * @param ruleExpress
     * @param allData
     * @return
     */
    //TODO 校验企业数据关联性
    public static Map<String,String> LJB0102(String columnName, String columnValue, String ruleExpress,Map<String,Objects> allData) {
        String removedSpaceValue = columnValue.replace(" ", "");
        Matcher matcher = pattern.matcher(removedSpaceValue);
        if (!matcher.matches()) {
            if (columnName != "") {

            }
        }
        return null;
    }

    /*"11":"北京","12":"天津","13":"河北","14":"山西","15":"内蒙古","21":"辽宁","22":"吉林","23":"黑龙江","31":"上海","32":"江苏",
      "33":"浙江","34":"安徽","35":"福建","36":"江西","37":"山东","41":"河南","42":"湖北","43":"湖南","44":"广东","45":"广西",
      "46":"海南","50":"重庆","51":"四川","52":"贵州","53":"云南","54":"西藏","61":"陕西","62":"甘肃","63":"青海","64":"宁夏",
      "65":"新疆","71":"台湾","81":"香港","82":"澳门","91":"国外"*/

    public  Map<String,String> cleanStr(String ruleType,String colName, String colValue, String ruleParam,Map<String,Objects> allData) {
        if ("WLA0101".equals(ruleType)){
            return WLA0101(colName,colValue,ruleParam,allData);
        }else if ("WLA0201".equals(ruleType)){
            return WLA0201(colName,colValue,ruleParam,allData);
        }else if ("WLA0202".equals(ruleType)){
            return WLA0202(colName,colValue,ruleParam,allData);
        }else if ("WLB0101".equals(ruleType)){
            return WLB0101(colName,colValue,ruleParam,allData);
        }else if ("WLB0102".equals(ruleType)){
            return WLB0102(colName,colValue,ruleParam,allData);
        }else if ("WLB0103".equals(ruleType)){
            return WLB0103(colName,colValue,ruleParam,allData);
        }else if ("WLB0104".equals(ruleType)){
            return WLB0104(colName,colValue,ruleParam,allData);
        }else if ("WLB0105".equals(ruleType)){
            return WLB0105(colName,colValue,ruleParam,allData);
        }else if ("WLB0106".equals(ruleType)){
            return WLB0106(colName,colValue,ruleParam,allData);
        }else if ("WLB0107".equals(ruleType)){
            return WLB0107(colName,colValue,ruleParam,allData);
        }else if ("WLB0108".equals(ruleType)){
            return WLB0108(colName,colValue,ruleParam,allData);
        }else if ("WLB0109".equals(ruleType)){
            return WLB0109(colName,colValue,ruleParam,allData);
        }else if ("WLB0110".equals(ruleType)){
            return WLB0110(colName,colValue,ruleParam,allData);
        }else if ("WLB0111".equals(ruleType)){
            return WLB0111(colName,colValue,ruleParam,allData);
        }else if ("WLB0112".equals(ruleType)){
            return WLB0112(colName,colValue,ruleParam,allData);
        }else if ("WLB0113".equals(ruleType)){
            return WLB0113(colName,colValue,ruleParam,allData);
        }else if ("WLB0114".equals(ruleType)){
            return WLB0114(colName,colValue,ruleParam,allData);
        }else if ("WLB0115".equals(ruleType)){
            return WLB0115(colName,colValue,ruleParam,allData);
        }else if ("WLB0201".equals(ruleType)){
            return WLB0201(colName,colValue,ruleParam,allData);
        }else if ("WLB0202".equals(ruleType)){
            return WLB0202(colName,colValue,ruleParam,allData);
        }else if ("WLC0101".equals(ruleType)){
            return WLC0101(colName,colValue,ruleParam,allData);
        }else if ("WLC0102".equals(ruleType)){
            return WLC0102(colName,colValue,ruleParam,allData);
        }else if ("WLC0103".equals(ruleType)){
            return WLC0103(colName,colValue,ruleParam,allData);
        }else if ("WLC0104".equals(ruleType)){
            return WLC0104(colName,colValue,ruleParam,allData);
        }else if ("WLC0201".equals(ruleType)){
            return WLC0201(colName,colValue,ruleParam,allData);
        }else if ("WLC0301".equals(ruleType)){
            return WLC0301(colName,colValue,ruleParam,allData);
        }else if ("WLC0302".equals(ruleType)){
            return WLC0301(colName,colValue,ruleParam,allData);
        }else if ("WLC0303".equals(ruleType)){
            return WLC0301(colName,colValue,ruleParam,allData);
        }else if ("WLC0304".equals(ruleType)){
            return WLC0301(colName,colValue,ruleParam,allData);
        }else if ("WLC0401".equals(ruleType)){
            return WLC0401(colName,colValue,ruleParam,allData);
        }else if ("WLC0402".equals(ruleType)){
            return WLC0402(colName,colValue,ruleParam,allData);
        }else if ("WLC0403".equals(ruleType)){
            return WLC0403(colName,colValue,ruleParam,allData);
        }else if ("WLC0404".equals(ruleType)){
            return WLC0404(colName,colValue,ruleParam,allData);
        }else if ("WLC0405".equals(ruleType)){
            return WLC0405(colName,colValue,ruleParam,allData);
        }else if ("WLC0406".equals(ruleType)){
            return WLC0406(colName,colValue,ruleParam,allData);
        }else if ("WLC0407".equals(ruleType)){
            return WLC0407(colName,colValue,ruleParam,allData);
        }else if ("WLC0408".equals(ruleType)){
            return WLC0408(colName,colValue,ruleParam,allData);
        }else if ("WLC0501".equals(ruleType)){
            return WLC0501(colName,colValue,ruleParam,allData);
        }else if ("WLC0502".equals(ruleType)){
            return WLC0502(colName,colValue,ruleParam,allData);
        }else if ("WLC0503".equals(ruleType)){
            return WLC0503(colName,colValue,ruleParam,allData);
        }else if ("WLC0504".equals(ruleType)){
            return WLC0504(colName,colValue,ruleParam,allData);
        }else if ("WLC0505".equals(ruleType)){
            return WLC0505(colName,colValue,ruleParam,allData);
        }else if ("WLC0506".equals(ruleType)){
            return WLC0506(colName,colValue,ruleParam,allData);
        }else if ("WLC0507".equals(ruleType)){
            return WLC0507(colName,colValue,ruleParam,allData);
        }else if ("WLC0508".equals(ruleType)){
            return WLC0508(colName,colValue,ruleParam,allData);
        }else {
            return null;
        }
    }

}

package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author javinjunfeng
 * Date 2018/6/6
 * Company yscredit
 */
public class JsonUtil {
    /**
     * 格式化json
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\'){
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }


    /**
     * 添加space
     *
     * @param sb 字符串
     * @param indent indent
     * @author YS
     *
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    /**
     * 把格式化的json紧凑
     * @param content
     * @return
     */
    public static String compactJson(String content) {
        String regEx="[\t\n]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m.replaceAll("").trim();
    }
}

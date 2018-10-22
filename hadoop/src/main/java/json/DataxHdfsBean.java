package json;

import java.util.Map;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: DataxHdfsBean
 * @time: 2018/9/29 14:08
 * @desc:
 */
public class DataxHdfsBean {

    private String path;
    private String defaultFS;
    private String haveKerberos;
    private String kerberosKeytabFilePath;
    private String kerberosPrincipal;
    private Map<Integer,String> column;
    private String fileType;
    private String fieldDelimiter;
    private String encoding;
    private String hadoopConfig;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDefaultFS() {
        return defaultFS;
    }

    public void setDefaultFS(String defaultFS) {
        this.defaultFS = defaultFS;
    }

    public String getHaveKerberos() {
        return haveKerberos;
    }

    public void setHaveKerberos(String haveKerberos) {
        this.haveKerberos = haveKerberos;
    }

    public String getKerberosKeytabFilePath() {
        return kerberosKeytabFilePath;
    }

    public void setKerberosKeytabFilePath(String kerberosKeytabFilePath) {
        this.kerberosKeytabFilePath = kerberosKeytabFilePath;
    }

    public String getKerberosPrincipal() {
        return kerberosPrincipal;
    }

    public void setKerberosPrincipal(String kerberosPrincipal) {
        this.kerberosPrincipal = kerberosPrincipal;
    }


    public Map<Integer, String> getColumn() {
        return column;
    }

    public void setColumn(Map<Integer, String> column) {
        this.column = column;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getHadoopConfig() {
        return hadoopConfig;
    }

    public void setHadoopConfig(String hadoopConfig) {
        this.hadoopConfig = hadoopConfig;
    }
}
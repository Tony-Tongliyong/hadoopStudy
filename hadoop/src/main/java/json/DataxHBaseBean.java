package json;

import java.util.List;
import java.util.Map;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: DataxHBaseBean
 * @time: 2018/9/29 14:13
 * @desc:
 */
public class DataxHBaseBean {

    private String hbaseConfig;
    private String table;
    private List rowKeyColumn;
    private Map column;
    private String mode;
    private String encoding;
    private String hbaseZookeeperQuorum;

    public String getHbaseConfig() {
        return hbaseConfig;
    }

    public void setHbaseConfig(String hbaseConfig) {
        this.hbaseConfig = hbaseConfig;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List getRowKeyColumn() {
        return rowKeyColumn;
    }

    public void setRowKeyColumn(List rowKeyColumn) {
        this.rowKeyColumn = rowKeyColumn;
    }

    public Map getColumn() {
        return column;
    }

    public void setColumn(Map column) {
        this.column = column;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getHbaseZookeeperQuorum() {
        return hbaseZookeeperQuorum;
    }

    public void setHbaseZookeeperQuorum(String hbaseZookeeperQuorum) {
        this.hbaseZookeeperQuorum = hbaseZookeeperQuorum;
    }
}
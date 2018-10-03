package hive;

/**
 * @Author: javinjunfeng
 * @Date: 2018/8/15 上午9:40
 * @Version 1.0
 */
public interface Constants {
    /**
     * 归集库相关的常量
     */
    String PETADATA_KEY_INFO = "PRI";
    String PETADATA_TONGID = "tongID";
    String PETADATA_TABLE_FLAG = "_hj";
    String PETADATA_DATABASE = "guiji";
    String PETADATA_TABLE_NANE_DELIMITER = "_hj";
    String PETADATA_DATABASE_HYYY = "hyyy";
    String PETADATA_DATABASE_HY = "hy";


    /**
     * mysql元数据常量
     */

    String FIELD = "Field";
    String TYPE = "Type";
    String KEY = "Key";
    String COMMENT = "Comment";

    /**
     * table_update_log 枚举常量
     */
    String UPDATE_LOG_ADD = "add";
    String UPDATE_LOG_DEL= "delete";
    String UPDATE_LOG_KEY_UPDATE= "key_update";
    String UPDATE_LOG_COLUMN_UPDATE= "column_update";
    String UPDATE_LOG_TYPE_UPDATE= "type_update";
    String UPDATE_LOG_COMMENT_UPDATE= "comment_update";

    /**
     * table_config_log 枚举常量
     */

    String CONFIG_LOG_ADD = "清洗配置新增";
    String CONFIG_LOG_DEL = "清洗配置删除";
    String CONFIG_LOG_UPDARE = "清洗配置更新";

    String RULE_CODE = "ruleCode";
    String EXPRESSION = "expression";
}

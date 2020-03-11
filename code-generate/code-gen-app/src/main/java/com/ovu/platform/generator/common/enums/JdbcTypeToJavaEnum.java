package com.ovu.platform.generator.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author flpeng
 */
public enum JdbcTypeToJavaEnum {
    INT("int", "Integer", "java.lang.Integer", "INTEGER"),
    TINYINT("tinyint", "Integer", "java.lang.Integer", "INTEGER"),
    INTEGER("integer", "Integer", "java.lang.Integer", "INTEGER"),
    BIGINT("bigint", "Long", "java.lang.Long", "BIGINT"),
    DECIMAL("decimal", "BigDecimal", "java.math.BigDecimal", "DECIMAL"),
    VARCHAR("varchar", "String", "java.lang.String", "VARCHAR"),
    DATETIME("datetime", "Date", "java.util.Date", "TIMESTAMP"),
    DATE("date", "Date", "java.util.Date", "DATE"),
    TIMESTAMP("timestamp", "Date", "java.util.Date", "TIMESTAMP"),
	JSON("json", "String", "java.lang.String", "VARCHAR"),
    TEXT("text", "String", "java.lang.String", "VARCHAR"),
    LONGTEXT("longtext", "String", "java.lang.String", "LONGVARCHAR");
	

    public String columnType;
    public String fieldType;
    public String fieldPackageName;
    public String jdbcType;

    private static Map<String, JdbcTypeToJavaEnum> jdbcToJavaMap = new HashMap<String, JdbcTypeToJavaEnum>();

    static {
        for (JdbcTypeToJavaEnum type : JdbcTypeToJavaEnum.values()) {
            jdbcToJavaMap.put(type.columnType, type);
        }
    }

    JdbcTypeToJavaEnum(String columnType, String fieldType, String fieldPackageName, String jdbcType) {
        this.columnType = columnType;
        this.fieldType = fieldType;
        this.fieldPackageName = fieldPackageName;
        this.jdbcType = jdbcType;
    }

    public static JdbcTypeToJavaEnum get(String columnType) {
        return jdbcToJavaMap.get(columnType);
    }
}

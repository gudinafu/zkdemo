package com.ovu.platform.generator.common.bo;

import lombok.Data;

public class TableFieldInfo {
    /**
     * 数据库字段名称
     */
    private String columnName;

    /**
     * 数据库字段类型
     */
    private String columnType;

    /**
     * 数据库字段注释
     */
    private String columnComment;

    /**
     * 允许空值
     */
    private Object isNullable = "YES";

    /**
     * 数据库字段键类型
     **/
    private Object columnKey;

    /**
     * 是否需要传给后台
     */
    private Boolean editShow = new Boolean(true);

    /**
     * 是否在列表显示
     **/
    private boolean columnShow = true;

    /**
     * 查询 1:模糊 2：精确
     **/
    private String columnQuery;

    /**
     * 对应的java属性类型
     */
    private String fieldType;

    /**
     * 对应的java属性名
     */
    private String fieldName;

    /**
     * 对应的jdbcType
     */
    private String jdbcType;

    /**
     * 对应的java属性包名
     */
    private String filedPackageName;

    /**
     * 是否是时间
     */
    private Boolean isDate = false;

    /**
     * 实体是否需要加上时间注解
     */
    private Boolean needDateAnnotation = false;
    
    public String getColumnName() {
        return columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    public String getColumnType() {
        return columnType;
    }
    
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    
    public String getColumnComment() {
        return columnComment;
    }
    
    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
    
    public Object getIsNullable() {
        return isNullable;
    }
    
    public void setIsNullable(Object isNullable) {
        this.isNullable = isNullable;
    }
    
    public Object getColumnKey() {
        return columnKey;
    }
    
    public void setColumnKey(Object columnKey) {
        this.columnKey = columnKey;
    }
    
    public Boolean getEditShow() {
        return editShow;
    }
    
    public void setEditShow(Boolean editShow) {
        this.editShow = editShow;
    }
    
    public boolean getColumnShow() {
        return columnShow;
    }
    
    public void setColumnShow(boolean columnShow) {
        this.columnShow = columnShow;
    }
    
    public String getColumnQuery() {
        return columnQuery;
    }
    
    public void setColumnQuery(String columnQuery) {
        this.columnQuery = columnQuery;
    }
    
    public String getFieldType() {
        return fieldType;
    }
    
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getJdbcType() {
        return jdbcType;
    }
    
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
    
    public String getFiledPackageName() {
        return filedPackageName;
    }
    
    public void setFiledPackageName(String filedPackageName) {
        this.filedPackageName = filedPackageName;
    }
    
    public Boolean getIsDate() {
        return isDate;
    }
    
    public void setIsDate(Boolean isDate) {
        this.isDate = isDate;
    }
    
    public Boolean getNeedDateAnnotation() {
        return needDateAnnotation;
    }
    
    public void setNeedDateAnnotation(Boolean needDateAnnotation) {
        this.needDateAnnotation = needDateAnnotation;
    }
    
}

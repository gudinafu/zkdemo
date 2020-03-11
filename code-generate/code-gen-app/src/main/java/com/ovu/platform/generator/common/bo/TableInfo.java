package com.ovu.platform.generator.common.bo;

import lombok.Data;

import java.util.List;

public class TableInfo {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 类名
     */
    private String className;

    /**
     * 类名驼峰
     */
    private String changeClassName;

    /**
     * 主键
     */
    private TableFieldInfo primaryKey;

    /**
     * 字段list
     */
    private List<TableFieldInfo> fieldList;

    /**
     * 查询条件list
     */
    private List<TableFieldInfo> queryFieldList;

    /**
     * 需要用户录入的属性
     */
    private List<TableFieldInfo> editFiledList;

    /**
     * 是否需要分页
     */
    private boolean paging = true;

    /**
     * 是否软删除
     */
    private Boolean deleted = false;

    /**
     * 是否有日期字段
     */
    private Boolean hasDate = true;

    /**
     * 是否有decimal字段
     */
    private Boolean hasBigDecimal = true;

    /**
     * 列表是否有查询条件
     */
    private Boolean hasQuery = true;

    /**
     * 实体是否需要加上时间注解
     */
    private Boolean needDateAnnotation = true;

    /**
     * 生成代码配置信息
     */
    private GenConfig genConfig;
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getChangeClassName() {
        return changeClassName;
    }
    
    public void setChangeClassName(String changeClassName) {
        this.changeClassName = changeClassName;
    }
    
    public TableFieldInfo getPrimaryKey() {
        return primaryKey;
    }
    
    public void setPrimaryKey(TableFieldInfo primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    public List<TableFieldInfo> getFieldList() {
        return fieldList;
    }
    
    public void setFieldList(List<TableFieldInfo> fieldList) {
        this.fieldList = fieldList;
    }
    
    public List<TableFieldInfo> getQueryFieldList() {
        return queryFieldList;
    }
    
    public void setQueryFieldList(List<TableFieldInfo> queryFieldList) {
        this.queryFieldList = queryFieldList;
    }
    
    public List<TableFieldInfo> getEditFiledList() {
        return editFiledList;
    }
    
    public void setEditFiledList(List<TableFieldInfo> editFiledList) {
        this.editFiledList = editFiledList;
    }
    
    public boolean getPaging() {
        return paging;
    }
    
    public void setPaging(boolean paging) {
        this.paging = paging;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    public Boolean getHasDate() {
        return hasDate;
    }
    
    public void setHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
    }
    
    public Boolean getHasBigDecimal() {
        return hasBigDecimal;
    }
    
    public void setHasBigDecimal(Boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }
    
    public Boolean getHasQuery() {
        return hasQuery;
    }
    
    public void setHasQuery(Boolean hasQuery) {
        this.hasQuery = hasQuery;
    }
    
    public Boolean getNeedDateAnnotation() {
        return needDateAnnotation;
    }
    
    public void setNeedDateAnnotation(Boolean needDateAnnotation) {
        this.needDateAnnotation = needDateAnnotation;
    }
    
    public GenConfig getGenConfig() {
        return genConfig;
    }
    
    public void setGenConfig(GenConfig genConfig) {
        this.genConfig = genConfig;
    }
    
}

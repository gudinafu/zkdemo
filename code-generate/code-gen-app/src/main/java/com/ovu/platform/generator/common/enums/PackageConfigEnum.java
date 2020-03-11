package com.ovu.platform.generator.common.enums;

public enum PackageConfigEnum {
    BASE_SERVICE("OVUBaseService", "com.ovu.platform.mybatis.service.OVUBaseService"),
    BASE_SERVICE_IMPL("OVUBaseServiceImpl", "com.ovu.platform.mybatis.service.OVUBaseServiceImpl"),
    BASE_DAO("OVUBaseDao", "com.ovu.platform.mybatis.dao.OVUBaseDao"),
    PAGING("OVUPage", "com.ovu.platform.common.bean.OVUPage"),
    MYBATIS_UTIL("MybatisUtil","com.ovu.platform.mybatis.util"),
    MAPPER_ANNOTATION_CLASS("@OVURepository", "com.core.base.mybatis.annotation.OVURepository");

    public String className;
    public String packageName;

    PackageConfigEnum(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}

package com.ovu.platform.generator.common.bo;

import com.ovu.platform.generator.common.enums.PackageConfigEnum;
import lombok.Data;


@Data
public class GlobalConfig {
    private PackageConfigEnum baseDao = PackageConfigEnum.BASE_DAO;
    private PackageConfigEnum baseService = PackageConfigEnum.BASE_SERVICE;
    private PackageConfigEnum baseServiceImpl = PackageConfigEnum.BASE_SERVICE_IMPL;
    private PackageConfigEnum paging = PackageConfigEnum.PAGING;
    private PackageConfigEnum mybatisUtil = PackageConfigEnum.MYBATIS_UTIL;
    private PackageConfigEnum mapperAnnotationClass = PackageConfigEnum.MAPPER_ANNOTATION_CLASS;
}

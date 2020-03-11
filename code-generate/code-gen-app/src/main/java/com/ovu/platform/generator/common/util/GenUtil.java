package com.ovu.platform.generator.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.util.ObjectUtils;

import com.ovu.platform.common.util.text.StringUtils;
import com.ovu.platform.generator.common.bo.GenConfig;
import com.ovu.platform.generator.common.bo.GlobalConfig;
import com.ovu.platform.generator.common.bo.TableFieldInfo;
import com.ovu.platform.generator.common.bo.TableInfo;
import com.ovu.platform.generator.common.constant.GeneratorConstant;
import com.ovu.platform.generator.common.enums.JdbcTypeToJavaEnum;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateException;
import cn.hutool.extra.template.TemplateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码生成
 *
 * @author jie
 * @date 2019-01-02
 */
@Slf4j
public class GenUtil {
    private static final String DATE = "Date";
    private static final String BIGDECIMAL = "BigDecimal";
    private static final String PK = "PRI";
    private static final String CREATE_TIME = "createTime";
    private static final String MODIFY_TIME = "modifyTime";
    public static final char SEPARATOR = '_';
    
    /**
     * 生成代码
     *
     * @param genConfig
     * @param tableInfo
     * @throws Exception
     */
    public static void generatorCode(TableInfo tableInfo, GenConfig genConfig) throws Exception {
        
        //设置velocity资源加载器
        //分页
        tableInfo.setPaging(true);
        // 处理table数据
        handleTableInfo(tableInfo, genConfig);
        Map<String, Object> map = new HashMap<>();
        map.put("author", genConfig.getAuthor());
        map.put("date", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        map.put("config", new GlobalConfig());
        map.put("backUrlPrefix", genConfig.getBackUrlPrefix());
        map.put("frontUrlPrefix", genConfig.getFrontUrlPrefix());
        map.put("table", tableInfo);
        map.put("columns", tableInfo.getFieldList());
        map.put("userConfig", genConfig);
        
        TemplateEngine engine = TemplateUtil.createEngine(
                new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        
        String codeType = genConfig.getCodeType();
        boolean generatorBackCode = GeneratorConstant.CodeType.BACK.equals(codeType)
                || GeneratorConstant.CodeType.ALL.equals(codeType);
        boolean generatorFrontCode = GeneratorConstant.CodeType.FRONT.equals(codeType)
                || GeneratorConstant.CodeType.ALL.equals(codeType);
        //不生成前端代码
        generatorFrontCode = false;
        List<String> templates = getAdminTemplateNames();
        // 生成后端代码
        if (generatorBackCode) {
            for (String templateName : templates) {
                Template template = engine.getTemplate("generator/back/" + templateName + ".ftl");
                String filePath = getAdminFilePath(templateName, tableInfo, genConfig);
                File file = new File(filePath);
                
                // 如果非覆盖生成
                if (!genConfig.getCover()) {
                    if (FileUtil.exist(file)) {
                        continue;
                    }
                }
                // 生成代码
                genFile(file, template, map);
            }
        }
        
        // 生成前端代码
        if (generatorFrontCode) {
            templates = getFrontTemplateNames();
            for (String templateName : templates) {
                Template template = engine.getTemplate("generator/front/" + templateName + ".ftl");
                String filePath = getFrontFilePath(templateName, genConfig,
                        tableInfo.getChangeClassName());
                File file = new File(filePath);
                
                // 如果非覆盖生成
                if (!genConfig.getCover()) {
                    if (FileUtil.exist(file)) {
                        continue;
                    }
                }
                // 生成代码
                genFile(file, template, map);
            }
        }
    }
    
    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }
    
    /**
     * 处理数据
     *
     * @param tableInfo
     */
    public static void handleTableInfo(TableInfo tableInfo, GenConfig genConfig) {
        
        List<TableFieldInfo> fieldInfoList = tableInfo.getFieldList();
        String tableName = tableInfo.getTableName();
        // 是否移除表前缀
        
        if (tableName.startsWith("tb_")) {
            int index = tableName.indexOf("tb_");
            tableName = tableName.substring(index + "tb_".length());
        }
        
        if (tableName.startsWith("t_")) {
            int index = tableName.indexOf("t_");
            tableName = tableName.substring(index + "t_".length());
        }
        //从配置文件获取参数
        Configuration config1 = getConfig();

        genConfig.setBackPath(config1.getString("backPath") + "."
                + toCapitalizeCamelCase(tableName).toLowerCase());
        genConfig.setModuleName(toCapitalizeCamelCase(tableName).toLowerCase());
        
        tableInfo.setClassName(toCapitalizeCamelCase(tableName));
        tableInfo.setChangeClassName(toCamelCase(tableName));
        tableInfo.setPackageName(genConfig.getBackPath());
        tableInfo.setPaging(genConfig.getPaging());
        
        List<TableFieldInfo> queryFieldList = new ArrayList<>();
        List<TableFieldInfo> editFiledList = new ArrayList<>();
        tableInfo.setQueryFieldList(queryFieldList);
        tableInfo.setEditFiledList(editFiledList);
        for (TableFieldInfo fieldInfo : fieldInfoList) {
            String columnName = fieldInfo.getColumnName();
            if ("is_deleted".equals(columnName)) {
                fieldInfo.setFieldName("deleted");
                tableInfo.setDeleted(Boolean.TRUE);
            } else {
                fieldInfo.setFieldName(toCamelCase(columnName));
            }
            JdbcTypeToJavaEnum jdbcTypeToJavaEnum = JdbcTypeToJavaEnum
                    .get(fieldInfo.getColumnType());
            if (jdbcTypeToJavaEnum == null) {
                System.out.println("");
            }
            fieldInfo.setFieldType(jdbcTypeToJavaEnum.fieldType);
            fieldInfo.setFiledPackageName(jdbcTypeToJavaEnum.fieldPackageName);
            fieldInfo.setJdbcType(jdbcTypeToJavaEnum.jdbcType);
            
            // 筛选出主键信息
            if (PK.equals(fieldInfo.getColumnKey())) {
                tableInfo.setPrimaryKey(fieldInfo);
                fieldInfo.setColumnKey(PK);
            }
            
            // 判断表是否有日期类型字段
            if (DATE.equals(fieldInfo.getFieldType())) {
                tableInfo.setHasDate(Boolean.TRUE);
                fieldInfo.setIsDate(Boolean.TRUE);
                if (CREATE_TIME.equals(fieldInfo.getFieldName())
                        || MODIFY_TIME.equals(fieldInfo.getFieldName())) {
                    fieldInfo.setNeedDateAnnotation(Boolean.FALSE);
                } else {
                    fieldInfo.setNeedDateAnnotation(Boolean.TRUE);
                    tableInfo.setNeedDateAnnotation(Boolean.TRUE);
                }
            }
            
            // 判断表是否有decimal类型字段
            if (BIGDECIMAL.equals(fieldInfo.getFieldType())) {
                tableInfo.setHasBigDecimal(Boolean.TRUE);
            }
            
            // 判断是否有查询条件
            if (StringUtils.isNotEmpty(fieldInfo.getColumnQuery())) {
                tableInfo.setHasQuery(Boolean.TRUE);
                queryFieldList.add(fieldInfo);
            }
            
            // 筛选出页面需要维护的属性
            if (fieldInfo.getEditShow()) {
                editFiledList.add(fieldInfo);
            }
        }
    }
    
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        
        s = s.toLowerCase();
        
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 获取后端代码模板名称
     *
     * @return
     */
    public static List<String> getAdminTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("Controller");
        templateNames.add("ServiceImpl");
        templateNames.add("Service");
        templateNames.add("Dao");
        templateNames.add("Entity");
        templateNames.add("Mapper");
        return templateNames;
    }
    
    /**
     * 获取前端代码模板名称
     *
     * @return
     */
    public static List<String> getFrontTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("ctrl");
        templateNames.add("index");
        templateNames.add("modal");
        return templateNames;
    }
    
    /**
     * 定义后端文件路径以及名称
     */
    public static String getAdminFilePath(String templateName, TableInfo tableInfo,
            GenConfig genConfig) {
        // 项目路径
        String projectPath = genConfig.getProjectPath();
        System.out.println("生成代码路径:" + projectPath);
        
        if (!projectPath.endsWith(File.separator)) {
            projectPath += File.separator;
        }
        // 代码包路径
        String packagePath = projectPath + "src.main.java".replace(".", File.separator)
                + File.separator;
        if (!ObjectUtils.isEmpty(genConfig.getBackPath())) {
            packagePath += genConfig.getBackPath().replace(".", File.separator) + File.separator;
        }
        // mapper文件包路径
        String mapperPath = projectPath + File.separator
                + "src.main.resources.mybatis.mapper.mysql".replace(".", File.separator)
                + File.separator;
        if (!ObjectUtils.isEmpty(genConfig.getMapperPath())) {
            mapperPath += genConfig.getMapperPath().replace(".", File.separator) + File.separator;
        }
        
        String className = tableInfo.getClassName();
        
        if ("Entity".equals(templateName)) {
            return packagePath + "entity" + File.separator + className + ".java";
        }
        
        if ("Controller".equals(templateName)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }
        
        if ("Service".equals(templateName)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }
        
        if ("ServiceImpl".equals(templateName)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className
                    + "ServiceImpl.java";
        }
        
        if ("Dao".equals(templateName)) {
            return packagePath + "repository" + File.separator + className + "Dao.java";
        }
        
        if ("Mapper".equals(templateName)) {
            return mapperPath + className + "Mapper.xml";
        }
        
        return null;
    }
    
    /**
     * 定义前端文件路径以及名称
     */
    public static String getFrontFilePath(String templateName, GenConfig genConfig,
            String apiName) {
        String path = genConfig.getFrontPath();
        
        if ("ctrl".equals(templateName)) {
            return path + File.separator + apiName + "Ctrl.js";
        }
        
        if ("index".equals(templateName)) {
            return path + File.separator + apiName + ".html";
        }
        
        if ("modal".equals(templateName)) {
            return path + File.separator + "modal.addOrEdit.html";
        }
        return null;
    }
    
    public static void genFile(File file, Template template, Map<String, Object> map)
            throws IOException {
        // 生成目标文件
        Writer writer = null;
        try {
            FileUtil.touch(file);
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            template.render(map, writer);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }
    }
}

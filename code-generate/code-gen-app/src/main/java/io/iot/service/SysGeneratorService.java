package io.iot.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovu.platform.generator.common.bo.GenConfig;
import com.ovu.platform.generator.common.bo.GeneratorSearchBo;
import com.ovu.platform.generator.common.bo.TableFieldInfo;
import com.ovu.platform.generator.common.bo.TableInfo;
import com.ovu.platform.generator.common.constant.GeneratorConstant.CodeType;
import com.ovu.platform.generator.common.util.GenUtil;
import com.ovu.platform.generator.vo.ColumnInfo;

import cn.hutool.core.io.FileUtil;
import io.iot.dao.GeneratorDao;
import io.iot.dao.SysGeneratorDao;

/**
 * 代码生成器
 */
@Service
public class SysGeneratorService {
    @Autowired
    private SysGeneratorDao sysGeneratorDao;
    
    @Resource
    private GeneratorDao generatorDao;
    
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGeneratorDao.queryList(map);
    }
    
    public int queryTotal(Map<String, Object> map) {
        return sysGeneratorDao.queryTotal(map);
    }
    
    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorDao.queryTable(tableName);
    }
    
    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorDao.queryColumns(tableName);
    }
    
    public void change(TableInfo tableInfo, List<ColumnInfo> list)
            throws IllegalAccessException, InvocationTargetException {
        List<TableFieldInfo> fieldList = new ArrayList<>();
        tableInfo.setFieldList(fieldList);
        TableFieldInfo tf = null;
        for (ColumnInfo info : list) {
            tf = new TableFieldInfo();
            tf.setColumnComment((String) info.getColumnComment());
            tf.setColumnKey(info.getColumnKey());
            tf.setColumnName((String) info.getColumnName());
            //精确查找
            tf.setColumnQuery("2");
            tf.setColumnShow(true);
            tf.setColumnType((String) info.getColumnType());
            tf.setEditShow(true);
            tf.setIsNullable("NO");
            fieldList.add(tf);
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
    public byte[] generatorCode(String[] tableNames) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        GeneratorSearchBo search = null;
        TableInfo tableInfo = null;
        GenConfig genConfig = new GenConfig();
        
        //从配置文件获取参数
        Configuration config1 = getConfig();
        
        genConfig.setPaging(true);
        genConfig.setProjectPath(config1.getString("projectPath"));
        genConfig.setAuthor(config1.getString("author"));
        genConfig.setBackUrlPrefix(config1.getString("version"));
        genConfig.setFrontUrlPrefix("/");

        
        // 项目路径
        String projectPath = genConfig.getProjectPath();
        File checkExist = new File(projectPath);
        if (checkExist.exists() == false) {
            checkExist.mkdirs();
        } else {
            FileUtil.del(checkExist);
            checkExist.mkdirs();
        }
        
        genConfig.setCodeType(CodeType.ALL);
        for (String tableName : tableNames) {
            search = new GeneratorSearchBo();
            search.setTableName(tableName);
            List<ColumnInfo> list = generatorDao.selectColumns(search);
            
            tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);
            tableInfo.setGenConfig(genConfig);
            change(tableInfo, list);
            GenUtil.generatorCode(tableInfo, tableInfo.getGenConfig());
            

        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}

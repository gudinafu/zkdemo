package io.iot.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ovu.platform.common.bean.OVUPage;
import com.ovu.platform.common.bean.Result;
import com.ovu.platform.generator.common.bo.GeneratorSearchBo;
import com.ovu.platform.generator.common.bo.TableInfo;
import com.ovu.platform.generator.common.util.GenUtil;
import com.ovu.platform.generator.service.GeneratorService;
import com.ovu.platform.generator.vo.ColumnInfo;

import io.iot.service.SysGeneratorService;

/**
 * @author flpeng
 * @date 2019/8/28 下午6:34
 */
@RestController
@RequestMapping("/platform/generator")
public class GeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;
    
    @Resource
    private GeneratorService generatorService;

    /**
     * 获取所有的数据库
     *
     * @param search
     * @return
     */


    /**
     * 获取数据库所有的表
     *
     * @param page
     * @param search
     * @return
     */
    @RequestMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result tables(OVUPage<GeneratorSearchBo> page, GeneratorSearchBo search) {
        
        page.setPageIndex(page.getPageIndex() - 1);
        page.setPageSize(page.getPageSize());
        OVUPage<GeneratorSearchBo> result = generatorService.queryByPage(page, search);
        page.setPageIndex(page.getPageIndex() + 1);
        return Result.success(result);
    }

    /**
     * 获取表对应的列信息
     *
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/columns", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result columns(GeneratorSearchBo search) {
        List<ColumnInfo> list = generatorService.selectColumns(search);
        return Result.success(list);
    }

    /**
     * 生成代码
     *
     * @param tableInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result generator(@RequestBody TableInfo tableInfo) throws Exception {
        
        return Result.success();
    }

    
    @ResponseBody
    @RequestMapping(value = "/databases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result databases(GeneratorSearchBo search) {
        List<String> list = generatorService.getDatabases(search);
        return Result.success(list);
    }
    
    /**
     * 生成代码
     * @throws Exception 
     */
    @RequestMapping("/code")
    @ResponseBody
    public Result code(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] tableNames = new String[] {};
        String tables = request.getParameter("tables");
        
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        
        sysGeneratorService.generatorCode(tableNames);
        
        return Result.success();
    }
}
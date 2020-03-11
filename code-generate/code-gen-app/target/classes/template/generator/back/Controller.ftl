package ${table.packageName}.controller;

import ${table.packageName}.entity.${table.className};
import ${table.packageName}.service.${table.className}Service;
import com.ovu.platform.common.bean.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<#if table.paging>
import ${config.paging.packageName};
</#if>

import javax.annotation.Resource;

/**
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("${backUrlPrefix}/${userConfig.moduleName}")
public class ${table.className}Controller {
    @Resource
    private ${table.className}Service ${table.changeClassName}Service;

<#if table.paging>
    /**
     * 分页
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result page(${config.paging.className}<${table.className}> page, ${table.className} ${table.changeClassName}) {
        ${config.paging.className}<${table.className}> result = ${table.changeClassName}Service.queryByPage(page, ${table.changeClassName});
        return Result.success(result);
    }
</#if>

    /**
     * 新增或修改
     *
     * @param ${table.changeClassName}
     * @return
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result edit(@RequestBody ${table.className} ${table.changeClassName}) {
        ${table.changeClassName}Service.edit(${table.changeClassName});
        return Result.success();
    }

    /**
     * 获取数据
     *
     * @param ${table.primaryKey.fieldName}
     * @return
     */
    @RequestMapping(value = "/get/{${table.primaryKey.fieldName}}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result get(@PathVariable ${table.primaryKey.fieldType} ${table.primaryKey.fieldName}) {
        ${table.className} ${table.changeClassName} = ${table.changeClassName}Service.selectByPrimaryKey(${table.primaryKey.fieldName});
        return Result.success(${table.changeClassName});
    }

    /**
     * 删除
     *
     * @param ${table.primaryKey.fieldName}
     * @return
     */
    @RequestMapping(value = "/delete/{${table.primaryKey.fieldName}}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result delete(@PathVariable ${table.primaryKey.fieldType} ${table.primaryKey.fieldName}) {
        ${table.changeClassName}Service.deleteByPrimaryKey(${table.primaryKey.fieldName});
        return Result.success();
    }
}
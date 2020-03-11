package ${table.packageName}.service;

import ${config.baseService.packageName};
import ${table.packageName}.entity.${table.className};
<#if table.paging>
import ${config.paging.packageName};
</#if>

/**
 * @author ${author}
 * @date ${date}
 */
public interface ${table.className}Service extends ${config.baseService.className}<${table.className},${table.primaryKey.fieldType}> {
<#if table.paging>
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    ${config.paging.className} queryByPage(${config.paging.className}<${table.className}> page, ${table.className} ${table.changeClassName});
</#if>

    /**
     * 新增或修改
     *
     * @param ${table.changeClassName}
     * @return
     */
    void edit(${table.className} ${table.changeClassName});
}

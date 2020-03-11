package ${table.packageName}.repository;

import ${config.baseDao.packageName};
import ${table.packageName}.entity.${table.className};
<#if table.paging>
import java.util.List;
import ${config.paging.packageName};
import org.apache.ibatis.annotations.Param;
</#if>

/**
 * @author ${author}
 * @date ${date}
 */
public interface ${table.className}Dao extends ${config.baseDao.className}<${table.className},${table.primaryKey.fieldType}> {
<#if table.paging>
    /**
     * 分页查询
     *
     * @param page
     * @param ${table.changeClassName}
     * @return
     */
    List<${table.className}> queryByPaging(@Param("paging") ${config.paging.className} page, @Param("${table.changeClassName}") ${table.className} ${table.changeClassName});

    /**
     * 分页查询总数
     *
     * @param ${table.changeClassName}
     * @return
     */
    Integer getTotalCount(@Param("${table.changeClassName}") ${table.className} ${table.changeClassName});
</#if>
}

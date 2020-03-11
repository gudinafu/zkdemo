package ${table.packageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${config.baseDao.packageName};
import ${table.packageName}.entity.${table.className};
import ${config.baseServiceImpl.packageName};
import ${table.packageName}.service.${table.className}Service;
import ${table.packageName}.repository.${table.className}Dao;
import com.ovu.platform.common.util.text.UUIDUtil;
<#if table.paging>
import java.util.List;
import ${config.paging.packageName};
</#if>
<#if table.deleted>
import com.ovu.platform.api.common.bean.OvuConstant;
</#if>


/**
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${table.className}ServiceImpl extends ${config.baseServiceImpl.className}<${table.className},${table.primaryKey.fieldType}>
            implements ${table.className}Service {

    @Resource
    private ${table.className}Dao ${table.changeClassName}Dao;

    @Override
    public ${config.baseDao.className}<${table.className}, ${table.primaryKey.fieldType}> getDao() {
        return ${table.changeClassName}Dao;
    }

<#if table.paging>
    @Override
    public ${config.paging.className} queryByPage(${config.paging.className}<${table.className}> page, ${table.className} ${table.changeClassName}) {
        Integer total = ${table.changeClassName}Dao.getTotalCount(${table.changeClassName});
        List<${table.className}> list = null;
        if (total > 0) {
            list = ${table.changeClassName}Dao.queryByPaging(page, ${table.changeClassName});
        }

        page.setData(list);
        page.setTotalCount(total);

        return page;
    }
</#if>

    @Override
    public void edit(${table.className} ${table.changeClassName}) {
        ${table.primaryKey.fieldType} ${table.primaryKey.fieldName} = ${table.changeClassName}.get${table.primaryKey.fieldName?cap_first}();
        if (${table.primaryKey.fieldName} == null) {
            ${table.changeClassName}.setId(UUIDUtil.uuid32());
            ${table.changeClassName}Dao.insertSelective(${table.changeClassName});
        } else {
            ${table.changeClassName}Dao.updateByPrimaryKeySelective(${table.changeClassName});
        }
    }

<#if table.deleted>
    @Override
    public int deleteByPrimaryKey(${table.primaryKey.fieldType} ${table.primaryKey.fieldName}) {
        ${table.className} ${table.changeClassName} = new ${table.className}();
        ${table.changeClassName}.set${table.primaryKey.fieldName?cap_first}(${table.primaryKey.fieldName});
        ${table.changeClassName}.setDeleted(OvuConstant.Delete.DELETE);
        return ${table.changeClassName}Dao.updateByPrimaryKeySelective(${table.changeClassName});
    }
</#if>
}

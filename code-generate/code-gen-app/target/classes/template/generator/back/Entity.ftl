package ${table.packageName}.entity;

import lombok.Data;
<#if table.hasDate>
import java.util.Date;
</#if>
<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if table.needDateAnnotation>
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;
</#if>

/**
  * @author ${author}
  * @date ${date}
  */
@Data
public class ${table.className} implements Serializable {
<#if columns??>
<#list columns as column>
    <#if column.columnComment != ''>
    /**
     * ${column.columnComment}
     */
    </#if>
    <#if column.needDateAnnotation>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.fieldType} ${column.fieldName};
</#list>
</#if>
}
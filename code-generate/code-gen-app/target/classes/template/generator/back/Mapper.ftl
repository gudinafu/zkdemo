<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${table.packageName}.repository.${table.className}Dao">
    <resultMap id="BaseResultMap" type="${table.packageName}.entity.${table.className}">
    <#list columns as column>
        <#if column.columnKey = 'PRI'>
        <id column="${column.columnName}" jdbcType="${column.jdbcType}" property="${column.fieldName}"/>
        <#else>
        <result column="${column.columnName}" jdbcType="${column.jdbcType}" property="${column.fieldName}"/>
        </#if>
    </#list>
    </resultMap>
    <sql id="Base_Column_List">
    <#list columns as column>
        <#if column_has_next>
        t1.${column.columnName},
        <#else>
        t1.${column.columnName}
        </#if>
    </#list>
    </sql>
    <select id="selectByPrimaryKey" parameterType="${table.primaryKey.filedPackageName}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${table.tableName} t1
        where t1.${table.primaryKey.columnName} = <#noparse>#{</#noparse>${table.primaryKey.fieldName},jdbcType=${table.primaryKey.jdbcType}<#noparse>}</#noparse>
    </select>
    <delete id="deleteByPrimaryKey" parameterType="${table.primaryKey.filedPackageName}">
        delete from ${table.tableName}
        where ${table.primaryKey.columnName} = <#noparse>#{</#noparse>${table.primaryKey.fieldName},jdbcType=${table.primaryKey.jdbcType}<#noparse>}</#noparse>
    </delete>
    <insert id="insert" parameterType="${table.packageName}.entity.${table.className}" useGeneratedKeys="true" keyProperty="${table.primaryKey.fieldName}">
        insert into ${table.tableName} (
    <#list columns as column>
        <#if column_has_next>
        ${column.columnName},
        <#else>
        ${column.columnName}
        </#if>
    </#list>
        )
        values (
    <#list columns as column>
        <#if column_has_next>
        <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>,
        <#else>
        <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>
        </#if>
    </#list>
        )
    </insert>
    <insert id="insertSelective" parameterType="${table.packageName}.entity.${table.className}" useGeneratedKeys="true" keyProperty="${table.primaryKey.fieldName}">
        insert into ${table.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="@${config.mybatisUtil.packageName}.${config.mybatisUtil.className}@isNotEmpty(${column.fieldName})">
                ${column.columnName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="@${config.mybatisUtil.packageName}.${config.mybatisUtil.className}@isNotEmpty(${column.fieldName})">
                <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>,
            </if>
        </#list>
        </trim>
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="${table.packageName}.entity.${table.className}">
        update ${table.tableName}
        <set>
        <#list columns as column>
            <if test="@${config.mybatisUtil.packageName}.${config.mybatisUtil.className}@isNotEmpty(${column.fieldName})">
                ${column.columnName} = <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>,
            </if>
        </#list>
        </set>
        where ${table.primaryKey.columnName} = <#noparse>#{</#noparse>${table.primaryKey.fieldName},jdbcType=${table.primaryKey.jdbcType}<#noparse>}</#noparse>
    </update>
    <update id="updateByPrimaryKey" parameterType="${table.packageName}.entity.${table.className}">
        update ${table.tableName}
        set
    <#list columns as column>
        <#if column_has_next>
        ${column.columnName} = <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>,
        <#else>
        ${column.columnName} = <#noparse>#{</#noparse>${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>
        </#if>
    </#list>
        where ${table.primaryKey.columnName} = <#noparse>#{</#noparse>${table.primaryKey.fieldName},jdbcType=${table.primaryKey.jdbcType}<#noparse>}</#noparse>
    </update>

<#if table.paging>
    <select id="queryByPaging" resultMap="BaseResultMap">
        <include refid="common.pageHead"/>
        <include refid="getPagingSql"/>
        <include refid="common.pageBottom"/>
    </select>

    <select id="getTotalCount" resultType="int">
        select count(1) from (
        <include refid="getPagingSql"/>
        ) t1
    </select>

    <sql id="getPagingSql">
        select
        <include refid="Base_Column_List" />
        from ${table.tableName} t1
        <where>
            <#if table.deleted>
            t1.is_deleted = 0
            </#if>
            <#if table.queryFieldList??>
            <if test="@${config.mybatisUtil.packageName}.${config.mybatisUtil.className}@isNotEmpty(${table.changeClassName})">
                <#list table.queryFieldList as column>
                <if test="@${config.mybatisUtil.packageName}.${config.mybatisUtil.className}@isNotEmpty(${table.changeClassName}.${column.fieldName})">
                        <#if column.columnQuery??&&column.columnQuery = '1'>
                    AND t1.${column.columnName} LIKE CONCAT('%',<#noparse>#{</#noparse>${table.changeClassName}.${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>,'%')
                        <#else>
                    AND t1.${column.columnName} = <#noparse>#{</#noparse>${table.changeClassName}.${column.fieldName},jdbcType=${column.jdbcType}<#noparse>}</#noparse>
                        </#if>
                </if>
                </#list>
            </if>
            </#if>
        </where>
        order by t1.create_time desc
    </sql>
</#if>
</mapper>
package com.ldh.platform.mybatis.service;


/**
 * Mybatis Service基础类,Service接口直接继承该类
 * Created by flpeng on 2017/8/29.
 */
public interface BaseService<T,PK> {
    /**
     * 根据主键删除记录
     * @param modelPK
     * @return
     */
    int deleteByPrimaryKey(PK modelPK);

    /**
     * 插入
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 插入非null的记录
     * @param model
     * @return
     */
    int insertSelective(T model);

    /**
     * 根据主键查询出记录
     * @param modelPK
     * @return
     */
    T selectByPrimaryKey(PK modelPK);

    /**
     * 根据主键更新非null的属性
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(T model);

    /**
     * 根据主键更新全部属性
     * @param model
     * @return
     */
    int updateByPrimaryKey(T model);
}

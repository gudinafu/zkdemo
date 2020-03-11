package com.ldh.platform.mybatis.service;


import com.ldh.platform.mybatis.dao.BaseDao;

/**
 * Mybatis ServiceImpl基础类,ServiceImpl类直接继承该类
 * Created by flpeng on 2017/8/29.
 */
public abstract class BaseServiceImpl<T,PK> implements BaseService<T,PK> {

    /**
     * 获取具体的实现Dao,继承方需要实现该方法
     * @return
     */
    public abstract BaseDao<T, PK> getDao();

    @Override
    public int deleteByPrimaryKey(PK modelPK) {
        return getDao().deleteByPrimaryKey(modelPK);
    }

    @Override
    public int insert(T model) {
        return getDao().insert(model);
    }

    @Override
    public int insertSelective(T model) {
        return getDao().insertSelective(model);
    }

    @Override
    public T selectByPrimaryKey(PK modelPK) {
        return getDao().selectByPrimaryKey(modelPK);
    }

    @Override
    public int updateByPrimaryKeySelective(T model) {
        return getDao().updateByPrimaryKeySelective(model);
    }

    @Override
    public int updateByPrimaryKey(T model) {
        return getDao().updateByPrimaryKey(model);
    }
}

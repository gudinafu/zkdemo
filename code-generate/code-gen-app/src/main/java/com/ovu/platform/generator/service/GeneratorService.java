package com.ovu.platform.generator.service;

import java.util.List;

import com.ovu.platform.common.bean.OVUPage;
import com.ovu.platform.generator.common.bo.GeneratorSearchBo;
import com.ovu.platform.generator.vo.ColumnInfo;

/**
 * @author flpeng
 * @date 2019/8/28 下午6:34
 */
public interface GeneratorService {

    /**
     * 分页查询表信息
     *
     * @param page
     * @param search
     * @return
     */
    OVUPage queryByPage(OVUPage page, GeneratorSearchBo search);

    /**
     * 查询表的列信息
     *
     * @param search
     * @return
     */
    List<ColumnInfo> selectColumns(GeneratorSearchBo search);

    /**
     * 获取所有的数据库
     *
     * @param search
     * @return
     */
    List<String> getDatabases(GeneratorSearchBo search);
}

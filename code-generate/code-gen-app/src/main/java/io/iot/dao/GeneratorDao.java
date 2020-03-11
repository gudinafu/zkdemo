package io.iot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ovu.platform.common.bean.OVUPage;
import com.ovu.platform.generator.common.bo.GeneratorSearchBo;
import com.ovu.platform.generator.vo.ColumnInfo;
import com.ovu.platform.generator.vo.TableVo;

/**
 * @author flpeng
 * @date 2019/8/28 下午6:34
 */
@com.core.base.mybatis.annotation.OVURepository
@Mapper
public interface GeneratorDao {

    /**
     * 分页查询表
     *
     * @param page
     * @param search
     * @return
     */
    List<TableVo> queryTableByPaging(@Param("paging") OVUPage page, @Param("search") GeneratorSearchBo search);

    /**
     * 查询表总数
     *
     * @param search
     * @return
     */
    Integer getTableTotalCount(@Param("search") GeneratorSearchBo search);

    /**
     * 查询表的列信息
     *
     * @param search
     * @return
     */
    List<ColumnInfo> selectColumns(@Param("search") GeneratorSearchBo search);

    /**
     * 获取所有的数据库
     *
     * @param search
     * @return
     */
    List<String> getDatabases(@Param("search") GeneratorSearchBo search);
}

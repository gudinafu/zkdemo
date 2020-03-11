package io.iot.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ovu.platform.common.bean.OVUPage;
import com.ovu.platform.generator.common.bo.GeneratorSearchBo;
import com.ovu.platform.generator.service.GeneratorService;
import com.ovu.platform.generator.vo.ColumnInfo;
import com.ovu.platform.generator.vo.TableVo;

import io.iot.dao.GeneratorDao;

/**
 * @author flpeng
 * @date 2019/8/28 下午6:34
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private GeneratorDao generatorDao;

    @Override
    public OVUPage queryByPage(OVUPage page, GeneratorSearchBo search) {
        Integer total = generatorDao.getTableTotalCount(search);
        List<TableVo> list = null;
        if (total > 0) {
            list = generatorDao.queryTableByPaging(page, search);
        }

        // 返回
        page.setTotalCount(total);
        page.setData(list);

        return page;
    }

    @Override
    public List<ColumnInfo> selectColumns(GeneratorSearchBo search) {
        List<ColumnInfo> list = generatorDao.selectColumns(search);
        return list;
    }

    @Override
    public List<String> getDatabases(GeneratorSearchBo search) {
        return generatorDao.getDatabases(search);
    }
}

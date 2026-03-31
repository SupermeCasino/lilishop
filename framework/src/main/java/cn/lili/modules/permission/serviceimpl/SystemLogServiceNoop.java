package cn.lili.modules.permission.serviceimpl;

import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.SearchVO;
import cn.lili.modules.permission.entity.vo.SystemLogVO;
import cn.lili.modules.permission.service.SystemLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(value = "lili.data.elasticsearch.enabled", havingValue = "false")
public class SystemLogServiceNoop implements SystemLogService {

    @Override
    public void saveLog(SystemLogVO systemLogVO) {
    }

    @Override
    public void deleteLog(List<String> id) {
    }

    @Override
    public void flushAll() {
    }

    @Override
    public IPage<SystemLogVO> queryLog(String storeId, String operatorName, String key, SearchVO searchVo, PageVO pageVO) {
        Page<SystemLogVO> page = new Page<>();
        page.setTotal(0);
        page.setRecords(Collections.emptyList());
        return page;
    }
}

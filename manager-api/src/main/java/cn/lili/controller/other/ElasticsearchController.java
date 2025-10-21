package cn.lili.controller.other;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.search.service.EsGoodsIndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ElasticsearchController
 *
 * @author Chopper
 * @version v1.0
 * 2021-03-24 18:32
 */
@RestController
@Tag(name = "ES初始化接口")
@RequestMapping("/manager/other/elasticsearch")
public class ElasticsearchController {

    @Autowired
    private EsGoodsIndexService esGoodsIndexService;

    @GetMapping
    public ResultMessage<String> init() {
        esGoodsIndexService.init();
        return ResultUtil.success();
    }

    @GetMapping("/progress")
    public ResultMessage<Map<String, Long>> getProgress() {
        return ResultUtil.data(esGoodsIndexService.getProgress());
    }

}

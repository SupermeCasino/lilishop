package cn.lili.controller.goods;


import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.Specification;
import cn.lili.modules.goods.service.SpecificationService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


/**
 * 管理端,商品规格接口
 *
 * @author pikachu
 * @since 2020-02-18 15:18:56
 */
@RestController
@Tag(name = "管理端,商品规格接口")
@RequestMapping("/manager/goods/spec")
public class SpecificationManagerController {

    @Autowired
    private SpecificationService specificationService;


    @GetMapping("/all")
    @Operation(description = "获取所有可用规格")
    public ResultMessage<List<Specification>> getAll() {
        return ResultUtil.data(specificationService.list());
    }

    @GetMapping
    @Operation(description = "搜索规格")
    public ResultMessage<Page<Specification>> page(@Parameter(description = "规格名称") String specName, @Parameter(description = "分页参数") PageVO page) {
        LambdaQueryWrapper<Specification> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(CharSequenceUtil.isNotEmpty(specName), Specification::getSpecName, specName);
        return ResultUtil.data(specificationService.page(PageUtil.initPage(page), lambdaQueryWrapper));
    }

    @PostMapping
    @Operation(description = "保存规格")
    public ResultMessage<Object> save(@Valid Specification specification) {
        specificationService.save(specification);
        return ResultUtil.success();
    }

    @PutMapping("/{id}")
    @Operation(description = "更改规格")
    public ResultMessage<Object> update(@Valid Specification specification, @PathVariable String id) {
        specification.setId(id);
        return ResultUtil.data(specificationService.saveOrUpdate(specification));
    }

    @Parameter(name = "ids", description = "规格ID", required = true)
    @DeleteMapping("/{ids}")
    @Operation(description = "批量删除")
    public ResultMessage<Object> delAllByIds(@PathVariable List<String> ids) {
        return ResultUtil.data(specificationService.deleteSpecification(ids));
    }
}

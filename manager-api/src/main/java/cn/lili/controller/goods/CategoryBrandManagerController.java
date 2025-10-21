package cn.lili.controller.goods;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.vos.CategoryBrandVO;
import cn.lili.modules.goods.service.CategoryBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,分类品牌接口
 *
 * @author pikachu
 * @since 2020-02-27 15:18:56
 */
@RestController
@Tag(name = "管理端,分类品牌接口")
@RequestMapping("/manager/goods/categoryBrand")
public class CategoryBrandManagerController {

    /**
     * 规格品牌管理
     */
    @Autowired
    private CategoryBrandService categoryBrandService;

    @Operation(summary = "根据分类ID获取品牌列表")
    @Parameter(name = "categoryId", description = "分类ID", required = true)
    @GetMapping("/{categoryId}")
    public ResultMessage<List<CategoryBrandVO>> getCategoryBrand(@PathVariable String categoryId) {
        return ResultUtil.data(categoryBrandService.getCategoryBrandList(categoryId));
    }

    @Operation(summary = "保存分类品牌关联")
    @Parameter(name = "categoryId", description = "分类ID", required = true)
    @PostMapping("/{categoryId}")
    public ResultMessage<Object> saveCategoryBrand(@PathVariable String categoryId, @RequestBody List<String> brandIds) {
        categoryBrandService.saveCategoryBrandList(categoryId, brandIds);
        return ResultUtil.success();
    }

}

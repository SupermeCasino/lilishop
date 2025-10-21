package cn.lili.controller.goods;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.CategorySpecification;
import cn.lili.modules.goods.entity.dos.Specification;
import cn.lili.modules.goods.service.CategorySpecificationService;
import cn.lili.modules.goods.service.SpecificationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理端,商品分类规格接口
 *
 * @author pikachu
 * @since 2020-02-27 15:18:56
 */
@RestController
@Tag(name = "管理端,商品分类规格接口")
@RequestMapping("/manager/goods/categorySpec")
public class CategorySpecificationManagerController {

    /**
     * 分类规格
     */
    @Autowired
    private CategorySpecificationService categorySpecificationService;

    /**
     * 规格
     */
    @Autowired
    private SpecificationService specificationService;


    @Operation(summary = "查询某分类下绑定的规格信息")
    @Parameter(name = "categoryId", description = "分类id", required = true)
    @GetMapping("/{categoryId}")
    public List<Specification> getCategorySpec(@PathVariable String categoryId) {
        return categorySpecificationService.getCategorySpecList(categoryId);
    }

    @Operation(summary = "查询某分类下绑定的规格信息,商品操作使用")
    @Parameter(name = "categoryId", description = "分类id", required = true)
    @GetMapping("/goods/{categoryId}")
    public List<Specification> getSpec(@PathVariable String categoryId) {
        return specificationService.list();
    }


    @Operation(summary = "保存某分类下绑定的规格信息")
    @Parameter(name = "categoryId", description = "分类id", required = true)
    @Parameter(name = "categorySpecs", description = "规格id数组", required = true)
    @PostMapping("/{categoryId}")
    public ResultMessage<String> saveCategoryBrand(@PathVariable String categoryId,
                                                   @RequestParam String[] categorySpecs) {
        //删除分类规格绑定信息
        this.categorySpecificationService.remove(new QueryWrapper<CategorySpecification>().eq("category_id", categoryId));
        //绑定规格信息
        if (categorySpecs != null && categorySpecs.length > 0) {
            List<CategorySpecification> categorySpecifications = new ArrayList<>();
            for (String categorySpec : categorySpecs) {
                categorySpecifications.add(new CategorySpecification(categoryId, categorySpec));
            }
            categorySpecificationService.saveBatch(categorySpecifications);
        }
        return ResultUtil.success();
    }

}

package cn.lili.controller.goods;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.CategoryParameterGroup;
import cn.lili.modules.goods.entity.dos.Parameters;
import cn.lili.modules.goods.entity.vos.ParameterGroupVO;
import cn.lili.modules.goods.service.CategoryParameterGroupService;
import cn.lili.modules.goods.service.ParametersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,分类绑定参数组接口
 *
 * @author pikachu
 * @since 2020-02-18 15:18:56
 */
@RestController
@Tag(name = "管理端,分类绑定参数组接口")
@RequestMapping("/manager/goods/categoryParameters")
public class CategoryParameterGroupManagerController {

    /**
     * 参数组
     */
    @Autowired
    private ParametersService parametersService;

    /**
     * 分类参数
     */
    @Autowired
    private CategoryParameterGroupService categoryParameterGroupService;

    @Operation(summary = "查询某分类下绑定的参数信息")
    @Parameter(name = "categoryId", description = "分类id", required = true)
    @GetMapping("/{categoryId}")
    public ResultMessage<List<ParameterGroupVO>> getCategoryParam(@PathVariable String categoryId) {
        return ResultUtil.data(categoryParameterGroupService.getCategoryParams(categoryId));
    }

    @Operation(summary = "保存数据")
    @PostMapping
    public ResultMessage<CategoryParameterGroup> saveOrUpdate(@Validated CategoryParameterGroup categoryParameterGroup) {

        if (categoryParameterGroupService.save(categoryParameterGroup)) {
            return ResultUtil.data(categoryParameterGroup);
        }
        throw new ServiceException(ResultCode.CATEGORY_PARAMETER_SAVE_ERROR);
    }

    @Operation(summary = "更新数据")
    @PutMapping
    public ResultMessage<CategoryParameterGroup> update(@Validated CategoryParameterGroup categoryParameterGroup) {

        if (categoryParameterGroupService.updateById(categoryParameterGroup)) {
            return ResultUtil.data(categoryParameterGroup);
        }
        throw new ServiceException(ResultCode.CATEGORY_PARAMETER_UPDATE_ERROR);
    }

    @Operation(summary = "通过id删除参数组")
    @Parameter(name = "id", description = "参数组ID", required = true)
    @DeleteMapping("/{id}")
    public ResultMessage<Object> delAllByIds(@PathVariable String id) {
        //删除参数
        parametersService.remove(new QueryWrapper<Parameters>().eq("group_id", id));
        //删除参数组
        categoryParameterGroupService.removeById(id);
        return ResultUtil.success();
    }

}

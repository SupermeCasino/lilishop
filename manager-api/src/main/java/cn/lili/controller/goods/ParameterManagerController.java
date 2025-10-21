package cn.lili.controller.goods;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.Parameters;
import cn.lili.modules.goods.service.ParametersService;     
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 管理端,分类绑定参数组管理接口
 *
 * @author Bulbasaur
 * @since 2020/11/26 16:15
 */
@RestController
@Tag(name = "管理端,分类绑定参数组管理接口")
@RequestMapping("/manager/goods/parameters")
public class ParameterManagerController {

    @Autowired
    private ParametersService parametersService;


    @Operation(summary = "添加参数")
    @PostMapping
    public ResultMessage<Parameters> save(@Valid Parameters parameters) {

        if (parametersService.save(parameters)) {
            return ResultUtil.data(parameters);
        }
        throw new ServiceException(ResultCode.PARAMETER_SAVE_ERROR);

    }

    @Operation(summary = "编辑参数")
    @PutMapping
    public ResultMessage<Parameters> update(@Valid Parameters parameters) {

        if (parametersService.updateParameter(parameters)) {
            return ResultUtil.data(parameters);
        }
        throw new ServiceException(ResultCode.PARAMETER_UPDATE_ERROR);
    }

    @Operation(summary = "通过id删除参数")
    @Parameter(name = "id", description = "参数ID", required = true)
    @DeleteMapping("/{id}")
    public ResultMessage<Object> delById(@PathVariable String id) {
        parametersService.removeById(id);
        return ResultUtil.success();

    }

}

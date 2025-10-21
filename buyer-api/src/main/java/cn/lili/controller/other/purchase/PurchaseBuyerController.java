package cn.lili.controller.other.purchase;

import cn.lili.common.aop.annotation.PreventDuplicateSubmissions;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.GoodsUnit;
import cn.lili.modules.goods.service.GoodsUnitService;
import cn.lili.modules.purchase.entity.dos.PurchaseOrder;
import cn.lili.modules.purchase.entity.params.PurchaseOrderSearchParams;
import cn.lili.modules.purchase.entity.vos.PurchaseOrderVO;
import cn.lili.modules.purchase.service.PurchaseOrderService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

/**
 * 买家端,采购接口
 *
 * @author Chopper
 * @since 2020/11/16 10:06 下午
 */
@Tag(name = "买家端,采购接口")
@RestController
@RequestMapping("/buyer/other/purchase/purchase")
public class PurchaseBuyerController {

    /**
     * 采购单
     */
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private GoodsUnitService goodsUnitService;


    @Operation(description = "分页获取商品计量单位")
    @GetMapping("/goodsUnit")
    public ResultMessage<IPage<GoodsUnit>> goodsUnitPage(PageVO pageVO) {
        return ResultUtil.data(goodsUnitService.page(PageUtil.initPage(pageVO)));
    }


    @PreventDuplicateSubmissions
    @Operation(description = "添加采购单")
    @PostMapping
    public ResultMessage<PurchaseOrderVO> addPurchaseOrderVO(@RequestBody PurchaseOrderVO purchaseOrderVO) {
        return ResultUtil.data(purchaseOrderService.addPurchaseOrder(purchaseOrderVO));
    }

    @Operation(description = "采购单分页")
    @GetMapping
    public ResultMessage<IPage<PurchaseOrder>> get(PurchaseOrderSearchParams purchaseOrderSearchParams) {
        return ResultUtil.data(purchaseOrderService.page(purchaseOrderSearchParams));
    }

    @Operation(description = "采购单详情")
    @Parameter(name = "id", description = "采购单ID", required = true)
    @GetMapping("/{id}")
    public ResultMessage<PurchaseOrderVO> getPurchaseOrder(@NotNull @PathVariable String id) {
        return ResultUtil.data(purchaseOrderService.getPurchaseOrder(id));
    }

    @Operation(description = "会员采购单分页")
    @GetMapping("/getByMember")
    public ResultMessage<IPage<PurchaseOrder>> getByMember(PurchaseOrderSearchParams purchaseOrderSearchParams) {
        purchaseOrderSearchParams.setMemberId(UserContext.getCurrentUser().getId());
        return ResultUtil.data(purchaseOrderService.page(purchaseOrderSearchParams));
    }

    @PreventDuplicateSubmissions
    @Operation(description = "关闭采购单")
    @Parameter(name = "id", description = "采购单ID", required = true)
    @PutMapping("/{id}")
    public ResultMessage<Object> close(@NotNull @PathVariable String id) {
        purchaseOrderService.close(id);
        return ResultUtil.success(ResultCode.SUCCESS);
    }

}

package cn.lili.controller.other.purchase;

import cn.lili.modules.purchase.service.PurchaseOrderService;
import cn.lili.modules.purchase.service.PurchaseQuotedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,采购报价接口
 *
 * @author Bulbasaur
 * @since 2020/11/16 10:06 下午
 */
@Tag(name = "买家端,采购报价接口")
@RestController
@RequestMapping("/buyer/other/purchase/purchaseQuoted")
public class PurchaseQuotedController {

    /**
     * 采购单报价
     */
    @Autowired
    private PurchaseQuotedService purchaseQuotedService;
    /**
     * 采购单
     */
    @Autowired
    private PurchaseOrderService purchaseOrderService;

//    @ApiOperation(description = "添加采购单报价")
//    @PostMapping
//    public ResultMessage<PurchaseQuoted> addPurchaseOrderVO(@RequestBody PurchaseQuotedVO purchaseQuotedVO) {
//        PurchaseOrder purchaseOrder=purchaseOrderService.getById(purchaseQuotedVO.getPurchaseOrderId());
//        if(DateUtil.compare(purchaseOrder.getDeadline(),new DateTime())< 0){
//            ResultUtil.error(ResultCode.PURCHASE_ORDER_DEADLINE_ERROR);
//        }
//        return ResultUtil.data(purchaseQuotedService.addPurchaseQuoted(purchaseQuotedVO));
//    }
//
//    @ApiOperation(description = "报价列表")
//    @ApiImplicitParam(name = "purchaseOrderId", description = "报价单ID", required = true, dataType = "String", paramType = "path")
//    @GetMapping("/purchaseOrder/{purchaseOrderId}")
//    public ResultMessage<List<PurchaseQuoted>> get(@NotNull @PathVariable String purchaseOrderId) {
//        return ResultUtil.data(purchaseQuotedService.getByPurchaseOrderId(purchaseOrderId));
//    }
//
//    @ApiOperation(description = "报价单详情")
//    @ApiImplicitParam(name = "id", description = "报价单ID", required = true, dataType = "String", paramType = "path")
//    @GetMapping(description = "purchaseQuoted/{id}")
//    public ResultMessage<PurchaseQuotedVO> getPurchaseQuoted(@NotNull @PathVariable String id) {
//        return ResultUtil.data(purchaseQuotedService.getById(id));
//    }


}

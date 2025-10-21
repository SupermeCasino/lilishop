package cn.lili.modules.purchase.entity.vos;

import cn.lili.modules.purchase.entity.dos.PurchaseOrder;
import cn.lili.modules.purchase.entity.dos.PurchaseOrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 采购单VO
 *
 * @author Bulbasaur
 * @since 2020/11/26 19:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderVO extends PurchaseOrder {

    private List<PurchaseOrderItem> purchaseOrderItems;
}

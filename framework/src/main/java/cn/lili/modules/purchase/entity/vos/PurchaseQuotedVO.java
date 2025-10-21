package cn.lili.modules.purchase.entity.vos;

import cn.lili.modules.purchase.entity.dos.PurchaseQuotedItem;
import cn.lili.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 报价单VO
 *
 * @author Bulbasaur
 * @since 2020/11/26 19:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseQuotedVO extends BaseEntity {

    private List<PurchaseQuotedItem> purchaseQuotedItems;
}

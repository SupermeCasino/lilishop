package cn.lili.modules.purchase.entity.params;

import cn.lili.common.vo.PageVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供求单查询参数
 *
 * @author Bulbasaur
 * @since 2020/11/27 11:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderSearchParams extends PageVO {

    @Schema(description = "会员ID")
    private String memberId;

    @Schema(description = "分类ID")
    private String categoryId;

    @Schema(description = "状态，开启：OPEN，关闭：CLOSE")
    private String status;
}

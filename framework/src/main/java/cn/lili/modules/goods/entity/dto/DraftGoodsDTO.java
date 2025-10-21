package cn.lili.modules.goods.entity.dto;

import cn.lili.modules.goods.entity.dos.DraftGoods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 草稿商品DTO
 *
 * @author paulG
 * @since 2020/12/22
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DraftGoodsDTO extends DraftGoods {

    private static final long serialVersionUID = 5255666163196674178L;

    @Schema(description = "商品参数")
    @Valid
    private List<GoodsParamsDTO> goodsParamsDTOList;

    @Schema(description = "商品图片")
    private List<String> goodsGalleryList;

    @Schema(description = "sku列表")
    @Valid
    private List<Map<String, Object>> skuList;

    /**
     * 批发商品规则
     */
    @Schema(description = "批发商品规则")
    private List<WholesaleDTO> wholesaleList;

}

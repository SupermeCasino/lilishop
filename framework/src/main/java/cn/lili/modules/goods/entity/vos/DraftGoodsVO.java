package cn.lili.modules.goods.entity.vos;

import cn.lili.modules.goods.entity.dos.DraftGoods;
import cn.lili.modules.goods.entity.dos.Wholesale;
import cn.lili.modules.goods.entity.dto.GoodsParamsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 草稿商品VO
 *
 * @author pikachu
 * @since 2020-02-26 23:24:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DraftGoodsVO extends DraftGoods {

    private static final long serialVersionUID = 6377623919990713567L;

    @Schema(description = "分类名称")
    private List<String> categoryName;

    @Schema(description = "商品参数")
    private List<GoodsParamsDTO> goodsParamsDTOList;

    @Schema(description = "商品图片")
    private List<String> goodsGalleryList;

    @Schema(description = "sku列表")
    private List<GoodsSkuVO> skuList;

    /**
     * 批发商品规则
     */
    @Schema(description = "批发商品规则")
    private List<Wholesale> wholesaleList;
}

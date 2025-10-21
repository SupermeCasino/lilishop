package cn.lili.modules.goods.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品关联参数
 *
 * @author pikachu
 * @since 2020-02-23 9:14:33
 */
@Data
@Schema(description = "商品参数分组")
public class GoodsParamsDTO implements Serializable {

    private static final long serialVersionUID = 4892783539320159200L;

    @Schema(description = "分组id")
    private String groupId;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "分组内的商品参数列表")
    private List<GoodsParamsItemDTO> goodsParamsItemDTOList;

}
package cn.lili.modules.goods.entity.dos;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HtmlUtil;
import cn.lili.modules.goods.entity.enums.DraftGoodsSaveType;
import cn.lili.modules.goods.entity.enums.GoodsStatusEnum;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 草稿商品
 *
 * @author pikachu
 * @since 2020-02-23 9:14:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("li_draft_goods")
@Schema(description = "草稿商品")
@AllArgsConstructor
@NoArgsConstructor
public class DraftGoods extends BaseEntity {

    private static final long serialVersionUID = 370683495251252601L;

    @Schema(description = "商品名称")
    private String goodsName;

    @DecimalMax(value = "99999999", message = "价格不能超过99999999")
    @Schema(description = "商品价格")
    private Double price;


    @Schema(description = "品牌id")
    private String brandId;

    @Schema(description = "分类path")
    private String categoryPath;

    @Schema(description = "计量单位")
    private String goodsUnit;

    @Schema(description = "卖点")
    private String sellingPoint;

    /**
     * @see GoodsStatusEnum
     */
    @Schema(description = "上架状态")
    private String marketEnable;

    @Schema(description = "详情")
    private String intro;


    @Schema(description = "商品移动端详情")
    private String mobileIntro;

    @Schema(description = "购买数量")
    private Integer buyCount;

    @DecimalMax(value = "99999999", message = "库存不能超过99999999")
    @Schema(description = "库存")
    private Integer quantity;

    @Schema(description = "可用库存")
    private Integer enableQuantity;

    @Schema(description = "商品好评率")
    private Double grade;

    @Schema(description = "缩略图路径")
    private String thumbnail;

    @Schema(description = "大图路径")
    private String big;

    @Schema(description = "小图路径")
    private String small;

    @Schema(description = "原图路径")
    private String original;

    @Schema(description = "店铺分类id")
    private String storeCategoryPath;

    @Schema(description = "评论数量")
    private Integer commentNum;

    @Schema(description = "卖家id")
    private String storeId;

    @Schema(description = "卖家名字")
    private String storeName;

    @Schema(description = "运费模板id")
    private String templateId;

    @Schema(description = "是否自营")
    private Boolean selfOperated;

    @Schema(description = "商品视频")
    private String goodsVideo;

    @Schema(description = "是否为推荐商品")
    private Boolean recommend;

    /**
     * @see cn.lili.modules.goods.entity.enums.GoodsSalesModeEnum
     */
    @Schema(description = "销售模式")
    private String salesModel;

    /**
     * @see DraftGoodsSaveType
     */
    @Schema(description = "草稿商品保存类型")
    private String saveType;

    @Schema(description = "分类名称JSON")
    private String categoryNameJson;

    @Schema(description = "商品参数JSON")
    private String goodsParamsListJson;

    @Schema(description = "商品图片JSON")
    private String goodsGalleryListJson;

    @Schema(description = "sku列表JSON")
    private String skuListJson;

    /**
     * @see cn.lili.modules.goods.entity.enums.GoodsTypeEnum
     */
    @Schema(description = "商品类型", required = true)
    private String goodsType;

    public String getIntro() {
        if (CharSequenceUtil.isNotEmpty(intro)) {
            return HtmlUtil.unescape(intro);
        }
        return intro;
    }

    public String getMobileIntro() {
        if (CharSequenceUtil.isNotEmpty(mobileIntro)) {
            return HtmlUtil.unescape(mobileIntro);
        }
        return mobileIntro;
    }

}
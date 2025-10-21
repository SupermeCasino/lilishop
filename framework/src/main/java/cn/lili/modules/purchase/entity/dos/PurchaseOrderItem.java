package cn.lili.modules.purchase.entity.dos;

import cn.lili.mybatis.BaseIdEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 采购单子内容
 *
 * @author Bulbasaur
 * @since 2020/11/26 19:32
 */
@Data
@TableName("li_purchase_order_item")
@Schema(description = "采购单子内容")
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItem extends BaseIdEntity {

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间", hidden = true)
    private Date createTime;

    @Schema(description = "采购ID")
    private String purchaseOrderId;

    @Schema(description = "商品名称")
    private String goodsName;

    @Schema(description = "数量")
    private String num;

    @Schema(description = "数量单位")
    private String goodsUnit;

    @Schema(description = "价格")
    private Double price;

    @Schema(description = "规格")
    private String specs;

    @Schema(description = "图片")
    private String images;


}

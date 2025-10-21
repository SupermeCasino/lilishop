package cn.lili.modules.purchase.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 供求单
 *
 * @author pikachu
 * @since 2020-03-14 23:04:56
 */
@Data
@Schema(description = "供求单")
@TableName("li_purchase_order")
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends BaseEntity {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "截止时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    @Schema(description = "收货时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiptTime;

    @Schema(description = "价格类型")
    private String priceMethod;

    @Schema(description = "地址名称， '，'分割")
    private String consigneeAddressPath;

    @Schema(description = "地址id，'，'分割 ")
    private String consigneeAddressIdPath;

    @Schema(description = "是否需要发票")
    private Boolean needReceipt;

    @Schema(description = "补充说明")
    private String supplement;

    @Schema(description = "联系类型")
    private String contactType;

    @Schema(description = "联系人")
    private String contacts;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "供求人")
    private String memberId;

    @Schema(description = "状态，开启：OPEN，关闭：CLOSE")
    private String status;

    @Schema(description = "分类ID")
    private String categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

}
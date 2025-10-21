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
 * 报价单
 *
 * @author Chopper
 * @since 2020/11/26 20:43
 */
@Data
@Schema(description = "供求单报价")
@TableName("li_purchase_quoted")
@EqualsAndHashCode(callSuper = false)
public class PurchaseQuoted extends BaseIdEntity {

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间", hidden = true)
    private Date createTime;

    @Schema(description = "采购单ID")
    private String purchaseOrderId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "报价说明")
    private String context;

    @Schema(description = "附件")
    private String annex;

    @Schema(description = "公司名称")
    private String companyName;

    @Schema(description = "联系人")
    private String contacts;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "报价人")
    private String memberId;

}

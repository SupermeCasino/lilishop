package cn.lili.modules.goods.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("li_goods_group")
@Schema(description = "商品分组")
public class GoodsGroup extends BaseEntity {

    @NotNull
    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "分组描述")
    private String description;
}


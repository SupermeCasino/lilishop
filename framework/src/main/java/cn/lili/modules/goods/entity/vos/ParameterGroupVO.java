package cn.lili.modules.goods.entity.vos;

import cn.lili.modules.goods.entity.dos.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 参数组vo
 *
 * @author pikachu
 * @since 2020年03月02日 16:55:21
 */
@Schema(description = "参数组vo")
@Data
public class ParameterGroupVO implements Serializable {

    private static final long serialVersionUID = 724427321881170297L;
    @Schema(description = "参数组关联的参数集合")
    private List<Parameters> params;
    @Schema(description = "参数组名称")
    private String groupName;
    @Schema(description = "参数组id")
    private String groupId;


}

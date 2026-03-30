/**
 * 作者：mike
 * 日期：2026-03-30
 */
package cn.lili.modules.goods.serviceimpl;

import cn.lili.modules.goods.entity.dos.GoodsGroup;
import cn.lili.modules.goods.mapper.GoodsGroupMapper;
import cn.lili.modules.goods.service.GoodsGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品分组业务服务实现类。
 * <p>
 * 基于 MyBatis-Plus 的 {@link ServiceImpl} 实现通用 CRUD。
 * </p>
 *
 * @author mike
 * @date 2026-03-30
 */
@Service
public class GoodsGroupServiceImpl extends ServiceImpl<GoodsGroupMapper, GoodsGroup> implements GoodsGroupService {
}


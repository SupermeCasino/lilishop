package cn.lili.modules.goods.serviceimpl;

import cn.lili.modules.goods.entity.dos.GoodsGroupGoods;
import cn.lili.modules.goods.mapper.GoodsGroupGoodsMapper;
import cn.lili.modules.goods.service.GoodsGroupGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsGroupGoodsServiceImpl extends ServiceImpl<GoodsGroupGoodsMapper, GoodsGroupGoods> implements GoodsGroupGoodsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGroupGoods(String groupId, List<String> goodsIds) {
        if (goodsIds == null || goodsIds.isEmpty()) {
            return true;
        }

        List<String> cleanGoodsIds = goodsIds.stream()
                .filter(i -> i != null && !i.trim().isEmpty())
                .map(String::trim)
                .distinct()
                .toList();
        if (cleanGoodsIds.isEmpty()) {
            return true;
        }

        // 批量设置分组：仅重置本次选中商品的分组，不影响其他商品
        QueryWrapper<GoodsGroupGoods> removeWrapper = new QueryWrapper<>();
        removeWrapper.in("goods_id", cleanGoodsIds);
        this.remove(removeWrapper);

        List<GoodsGroupGoods> list = new ArrayList<>();
        for (String goodsId : cleanGoodsIds) {
            GoodsGroupGoods rel = new GoodsGroupGoods();
            rel.setGroupId(groupId);
            rel.setGoodsId(goodsId);
            list.add(rel);
        }
        return this.saveBatch(list);
    }
}


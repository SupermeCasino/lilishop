package cn.lili.modules.goods.service;

import cn.lili.modules.goods.entity.dos.GoodsGroupGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsGroupGoodsService extends IService<GoodsGroupGoods> {

    boolean updateGroupGoods(String groupId, List<String> goodsIds);
}


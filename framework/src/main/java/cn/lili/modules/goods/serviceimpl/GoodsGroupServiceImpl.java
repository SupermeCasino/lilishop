package cn.lili.modules.goods.serviceimpl;

import cn.lili.modules.goods.entity.dos.GoodsGroup;
import cn.lili.modules.goods.mapper.GoodsGroupMapper;
import cn.lili.modules.goods.service.GoodsGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GoodsGroupServiceImpl extends ServiceImpl<GoodsGroupMapper, GoodsGroup> implements GoodsGroupService {
}


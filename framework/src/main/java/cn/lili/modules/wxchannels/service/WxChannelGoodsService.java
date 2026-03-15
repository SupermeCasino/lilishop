package cn.lili.modules.wxchannels.service;

import cn.lili.modules.wxchannels.entity.dos.WxChannelGoods;
import cn.lili.modules.wxchannels.entity.dto.WxChannelGoodsSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxChannelGoodsService extends IService<WxChannelGoods> {

    IPage<WxChannelGoods> page(WxChannelGoodsSearchParams params);
}

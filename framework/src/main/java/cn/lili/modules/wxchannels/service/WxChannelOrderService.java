package cn.lili.modules.wxchannels.service;

import cn.lili.modules.wxchannels.entity.dos.WxChannelOrder;
import cn.lili.modules.wxchannels.entity.dto.WxChannelOrderSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxChannelOrderService extends IService<WxChannelOrder> {

    IPage<WxChannelOrder> page(WxChannelOrderSearchParams params);
}

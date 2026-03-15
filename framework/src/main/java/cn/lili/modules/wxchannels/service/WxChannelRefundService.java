package cn.lili.modules.wxchannels.service;

import cn.lili.modules.wxchannels.entity.dos.WxChannelRefund;
import cn.lili.modules.wxchannels.entity.dto.WxChannelRefundSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxChannelRefundService extends IService<WxChannelRefund> {

    IPage<WxChannelRefund> page(WxChannelRefundSearchParams params);
}

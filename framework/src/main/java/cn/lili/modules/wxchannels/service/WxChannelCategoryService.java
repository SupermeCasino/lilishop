package cn.lili.modules.wxchannels.service;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.wxchannels.entity.dos.WxChannelCategory;
import cn.lili.modules.wxchannels.entity.dto.WxChannelCategorySubmitDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxChannelCategoryService extends IService<WxChannelCategory> {

    IPage<WxChannelCategory> page(PageVO page, String status);

    void submit(WxChannelCategorySubmitDTO dto);
}

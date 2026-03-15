package cn.lili.modules.wxchannels.service;

import cn.lili.modules.wxchannels.entity.vo.WxChannelOverviewDailyVO;
import cn.lili.modules.wxchannels.entity.vo.WxChannelOverviewSummaryVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface WxChannelOverviewService {

    WxChannelOverviewSummaryVO summary(Long startTime, Long endTime);

    List<WxChannelOverviewDailyVO> daily(Long startTime, Long endTime);

    void export(HttpServletResponse response, Long startTime, Long endTime);
}

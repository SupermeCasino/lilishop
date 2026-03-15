package cn.lili.modules.wxchannels.entity.dto;

import lombok.Data;

@Data
public class WxChannelsSetting {
    private String appId;
    private String appSecret;
    private String apiBase;
    private String tokenUrl;
}

package cn.lili.modules.wxchannels.service;

public interface WxChannelsApiService {

    void updateInventory(String skuId, Integer stock);

    void changeStatus(String goodsId, String status);
}

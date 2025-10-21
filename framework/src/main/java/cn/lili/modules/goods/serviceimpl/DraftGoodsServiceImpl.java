package cn.lili.modules.goods.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;


import cn.lili.modules.goods.entity.dos.*;
import cn.lili.modules.goods.entity.dto.*;
import cn.lili.modules.goods.entity.vos.DraftGoodsVO;
import cn.lili.modules.goods.mapper.DraftGoodsMapper;
import cn.lili.modules.goods.service.*;
import cn.lili.modules.goods.sku.GoodsSkuBuilder;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 草稿商品业务层实现
 *
 * @author paulG
 * @since 2020/12/19
 **/
@Service
public class DraftGoodsServiceImpl extends ServiceImpl<DraftGoodsMapper, DraftGoods> implements DraftGoodsService {
    /**
     * 分类
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * 商品相册
     */
    @Autowired
    private GoodsGalleryService goodsGalleryService;
    /**
     * 规格商品
     */
    @Autowired
    private GoodsSkuService goodsSkuService;

    @Autowired
    private WholesaleService wholesaleService;

    @Override
    public boolean addGoodsDraft(DraftGoodsDTO draftGoods) {
        draftGoods.setGoodsGalleryListJson(JSON.toJSONString(draftGoods.getGoodsGalleryList()));
        draftGoods.setSkuListJson(JSON.toJSONString(draftGoods.getSkuList()));
        draftGoods.setGoodsParamsListJson(JSON.toJSONString(draftGoods.getGoodsParamsDTOList()));
        return this.save(draftGoods);
    }

    @Override
    public boolean updateGoodsDraft(DraftGoodsDTO draftGoods) {
        draftGoods.setGoodsGalleryListJson(JSON.toJSONString(draftGoods.getGoodsGalleryList()));
        draftGoods.setSkuListJson(JSON.toJSONString(draftGoods.getSkuList()));
        draftGoods.setGoodsParamsListJson(JSON.toJSONString(draftGoods.getGoodsParamsDTOList()));
        return this.updateById(draftGoods);
    }

    @Override
    public void saveGoodsDraft(DraftGoodsDTO draftGoods) {

        if (draftGoods.getGoodsGalleryList() != null && !draftGoods.getGoodsGalleryList().isEmpty()) {
            GoodsGallery goodsGallery = goodsGalleryService.getGoodsGallery(draftGoods.getGoodsGalleryList().get(0));
            draftGoods.setOriginal(goodsGallery.getOriginal());
            draftGoods.setSmall(goodsGallery.getSmall());
            draftGoods.setThumbnail(goodsGallery.getThumbnail());
        }
        // 商品图片
        draftGoods.setGoodsGalleryListJson(JSON.toJSONString(draftGoods.getGoodsGalleryList()));
        // 商品参数
        draftGoods.setGoodsParamsListJson(JSON.toJSONString(draftGoods.getGoodsParamsDTOList()));
        boolean result = this.saveOrUpdate(draftGoods);
        if (result && draftGoods.getSkuList() != null && !draftGoods.getSkuList().isEmpty()) {
            List<GoodsSku> goodsSkus = GoodsSkuBuilder.buildBatch(new Goods(draftGoods), draftGoods.getSkuList());
            GoodsOperationDTO.GoodsOperationDTOBuilder goodsOperationDTOBuilder = GoodsOperationDTO.builder().goodsTemplateFlag(true).salesModel(draftGoods.getSalesModel());
            if (draftGoods.getWholesaleList() != null && !draftGoods.getWholesaleList().isEmpty()) {

                for (WholesaleDTO wholesaleDTO : draftGoods.getWholesaleList()) {
                    wholesaleDTO.setTemplateId(draftGoods.getId());
                }
                goodsOperationDTOBuilder.wholesaleList(draftGoods.getWholesaleList());
            }
            goodsSkuService.renderGoodsSkuList(goodsSkus, goodsOperationDTOBuilder.build());
            LambdaUpdateWrapper<DraftGoods> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(DraftGoods::getId, draftGoods.getId());
            updateWrapper.set(DraftGoods::getSkuListJson, JSON.toJSONString(goodsSkus));
            this.update(updateWrapper);
        }
    }

    @Override
    public DraftGoodsVO getDraftGoods(String id) {

        DraftGoods draftGoods = this.getById(id);
        DraftGoodsVO draftGoodsVO = new DraftGoodsVO();
        BeanUtil.copyProperties(draftGoods, draftGoodsVO);
        //商品分类名称赋值
        List<String> categoryName = new ArrayList<>();
        String[] strArray = draftGoods.getCategoryPath().split(",");
        List<Category> categories = categoryService.listByIds(Arrays.asList(strArray));
        for (Category category : categories) {
            categoryName.add(category.getName());
        }
        draftGoodsVO.setCategoryName(categoryName);
        draftGoodsVO.setGoodsParamsDTOList(JSON.parseArray(draftGoods.getGoodsParamsListJson(), GoodsParamsDTO.class));
        draftGoodsVO.setGoodsGalleryList(JSON.parseArray(draftGoods.getGoodsGalleryListJson(), String.class));
        List<GoodsSku> list = JSON.parseArray(draftGoods.getSkuListJson(), GoodsSku.class);
        draftGoodsVO.setSkuList(goodsSkuService.getGoodsSkuVOList(list));
        List<Wholesale> wholesaleList = wholesaleService.findByTemplateId(draftGoods.getId());
        if (CollUtil.isNotEmpty(wholesaleList)) {
            draftGoodsVO.setWholesaleList(wholesaleList);
        }
        return draftGoodsVO;
    }

    @Override
    public void deleteGoodsDraft(String id) {
        this.removeById(id);
        this.wholesaleService.removeByTemplateId(id);
    }

    @Override
    public IPage<DraftGoods> getDraftGoods(DraftGoodsSearchParams searchParams) {
        return this.page(PageUtil.initPage(searchParams), searchParams.queryWrapper());
    }

}

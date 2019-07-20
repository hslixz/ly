package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @author l
 * @since 2019/7/12
 */
public interface IBrandService {
    /**
     * 查询品牌
     *
     * @param key    品牌名关键字
     * @param page   页码
     * @param rows   单页条数
     * @param sortBy 排序字段
     * @param desc   是否逆序
     * @return 品牌列表
     */
    PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    /**
     * 保存品牌
     *
     * @param brand 品牌实体
     * @param cids  分类id列表
     */
    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 更新品牌
     *
     * @param brand 品牌实体
     * @param cids  分类id列表
     */
    void updateBrand(Brand brand, List<Long> cids);

    /**
     * 删除分类品牌关联关系
     *
     * @param bid 品牌id
     */
    void deleteCategoryAndBrandByBrand(Long bid);

    /**
     * 删除品牌
     * @param bid 品牌id
     */
    void deleteBrand(Long bid);
}

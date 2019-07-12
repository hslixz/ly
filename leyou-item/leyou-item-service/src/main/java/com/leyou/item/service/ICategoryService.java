package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * 商品分类service接口
 * @author l
 * @since 2019-7-12
 */
public interface ICategoryService {
    /**
     * 根据父id查询子节点
     * @param pid 父id
     * @return 商品分类列表
     */
    List<Category> queryCategoriesByPid(Long pid);
}

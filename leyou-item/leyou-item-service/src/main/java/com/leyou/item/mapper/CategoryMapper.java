package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 商品分类mapper
 *
 * @author l
 * @since
 */
public interface CategoryMapper extends Mapper<Category> {
    /**
     * 根据bid查询商品分类
     *
     * @param bid 品牌id
     * @return 商品分类列表
     */
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}

package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author l
 * @since 2019/7/12
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 新增商品分类与品牌中间表
     *
     * @param cid 商品分类id
     * @param bid 品牌id
     */
    @Insert("INSERT INTO tb_category_brand(category_id, brand_id) VALUES(#{cid}, #{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 删除分类品牌关联数据
     * @param bid 品牌id
     * @return 删除行数
     */
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    Integer deleteCategoryAndBrandByBrand(Long bid);
}

package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.IBrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author l
 * @since 2019/7/12
 */
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        PageHelper.startPage(page, rows);
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> this.brandMapper.insertCategoryAndBrand(cid, brand.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBrand(Brand brand, List<Long> cids) {
        //删除原分类品牌关系
        deleteCategoryAndBrandByBrand(brand.getId());
        //更新品牌
        this.brandMapper.updateByPrimaryKeySelective(brand);
        //新增新分类品牌关系
        cids.forEach(cid -> this.brandMapper.insertCategoryAndBrand(cid, brand.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategoryAndBrandByBrand(Long bid) {
        this.brandMapper.deleteCategoryAndBrandByBrand(bid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBrand(Long bid) {
        //删除品牌
        this.brandMapper.deleteByPrimaryKey(bid);
        //删除分类品牌关联关系
        deleteCategoryAndBrandByBrand(bid);
    }
}

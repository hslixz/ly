package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author l
 * @since 2019/7/12
 */
@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 分页查询品牌列表
     *
     * @param key    品牌名关键字
     * @param page   页码
     * @param rows   单页条数
     * @param sortBy 排序字段
     * @param desc   是否逆序
     * @return 品牌列表
     */
    @RequestMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 保存品牌
     *
     * @param brand 品牌实体
     * @param cids  分类id列表
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新品牌
     *
     * @param brand
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.updateBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除品牌
     *
     * @param bid 品牌id
     * @return
     */
    @DeleteMapping("{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") Long bid) {
        this.brandService.deleteBrand(bid);
        return ResponseEntity.ok().build();
    }
}

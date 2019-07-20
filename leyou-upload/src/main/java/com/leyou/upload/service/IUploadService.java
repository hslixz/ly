package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author l
 * @since 2019/7/16
 */
public interface IUploadService {
    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);
}

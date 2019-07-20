package com.leyou.upload.service.impl;

import com.leyou.upload.service.IUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author l
 * @since 2019/7/16
 */
@Service
public class UploadServiceImpl implements IUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);
    /**
     * 符合条件的图片类型
     */
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif", "image/png");

    @Override
    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }
        try {
            //校验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }
            //保存文件
            file.transferTo(new File("F:\\image\\" + originalFilename));
            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            LOGGER.error("服务器内部错误：[{}], 文件名：{}", e, originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}

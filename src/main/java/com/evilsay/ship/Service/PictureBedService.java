package com.evilsay.ship.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/** 七牛云服务
 * @Author: EvilSay
 * @Date: 2019/2/28 18:27
 */
public interface PictureBedService {
    /**
     * 生成七牛云简单上传凭证
     * @return  七牛云简单上传凭证，覆盖上传等操作不可用
     */
    String getSimpleToken();

    /**
     * 生成七牛云覆盖上传凭证
     * @param fileKey   需要覆盖的文件名称
     * @return          七牛云覆盖上传凭证
     */
    String getOverrideToken(String fileKey);

    /**
     * 生成带有回调信息的上传凭证
     * @return  七牛云上传凭证（附带回调信息）
     */
    String getWithReturnToken();

    /**
     * 上传图片到七牛云
     * @param multipartFile 上传图片文件
     * @throws Exception 文件异常
     */
    String uploadImageToQiniuyun(MultipartFile multipartFile) throws IOException;
}

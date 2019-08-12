package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.Constant.CookieConstant;
import com.evilsay.ship.Constant.PictureBedConstant;
import com.evilsay.ship.Service.PictureBedService;
import com.evilsay.ship.Utils.Bed.PictureBedUtils;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: EvilSay
 * @Date: 2019/2/28 18:28
 */
@Service
@Slf4j
public class PictureBedServiceImpl implements PictureBedService {

    private final Auth auth = PictureBedUtils.getAuth();

    @Override
    public String getSimpleToken() {

        return auth.uploadToken(PictureBedConstant.BUCKET);
    }

    @Override
    public String getOverrideToken(String fileKey) {

        return auth.uploadToken(PictureBedConstant.BUCKET,fileKey);
    }

    @Override
    public String getWithReturnToken() {

        StringMap stringMap = new StringMap();
        stringMap.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        return auth.uploadToken(PictureBedConstant.BUCKET,null,CookieConstant.EXPIRE,stringMap);
    }

    @Override
    public String uploadImageToQiniuyun(MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {

            String token = this.getWithReturnToken();
            //构建一个带指示Zone对象的配置类
            Configuration cg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cg);
            //获取文件名称
            String originalFilename = multipartFile.getOriginalFilename();
            //生成UUID作为文件的KEY值防止出现上传文件时文件名重复
            String uuid = UUID.randomUUID().toString();

            InputStream inputStream = multipartFile.getInputStream();

            // 默认不指定key的情况下，以文件内容的hash值作为文件名
            // 使用（文件前缀+UUID+文件名称）作为文件的空间key，防止出现key重复
            String key = "image/upload/" + uuid + "/" + originalFilename;

            Response response = uploadManager.put(inputStream, key, token, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            log.info("解析结果为--" + putRet.toString());

            return PictureBedConstant.PICTURERETURN_ADDRESS + key;
        }

        return null;
    }
}

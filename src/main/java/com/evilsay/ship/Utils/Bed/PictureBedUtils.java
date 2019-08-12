package com.evilsay.ship.Utils.Bed;

import com.evilsay.ship.Constant.PictureBedConstant;
import com.evilsay.ship.Exception.PictureException;
import com.qiniu.util.Auth;
import org.springframework.util.StringUtils;

/**
 * @Author: EvilSay
 * @Date: 2019/2/27 22:55
 */
public class PictureBedUtils {

    public static Auth getAuth(){
        String as = PictureBedConstant.ACCESSKEY;
        String sk = PictureBedConstant.SECRET_KEY;
        String bk = PictureBedConstant.BUCKET;
        if (StringUtils.isEmpty(as) || StringUtils.isEmpty(sk) || StringUtils.isEmpty(bk)){
            throw new PictureException("七牛云参数不正确！");
        }
        return Auth.create(as,sk);
    }
}

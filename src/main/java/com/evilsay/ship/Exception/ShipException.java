package com.evilsay.ship.Exception;

import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Enums.ResultShopEnum;
import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 22:02
 */
@Getter
public class ShipException extends RuntimeException {

    private Integer code;

    public ShipException(ResultEnum resultEnum) {
        //传递到父类的构造方法
        super(resultEnum.getMessage());
        this.code  = resultEnum.getCode();
    }

    public ShipException(ResultShopEnum resultShopEnum) {
        super(resultShopEnum.getMessage());
        this.code = resultShopEnum.getCode();
    }

    public ShipException(ResultShopEnum resultShopEnum,ResultEnum resultEnum) {
        super(resultShopEnum.getMessage()+"或"+resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.code = resultShopEnum.getCode();
    }

    public ShipException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ShipException() {
    }
}

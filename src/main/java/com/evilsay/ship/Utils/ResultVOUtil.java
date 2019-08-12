package com.evilsay.ship.Utils;

import com.evilsay.ship.VO.ResultVO;

/**封装JSON头部
 * @Author: EvilSay
 * @Date: 2019/1/22 0:12
 */
public class ResultVOUtil {

    //成功返回
    public static ResultVO<Object> success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    //成功访问不回传数据
    public static ResultVO<Object> success(){
        return success(null);
    }

    //错误
    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
    //错误
    public static ResultVO error(String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage(message);
        return resultVO;
    }
    public static ResultVO success_status(String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage(message);
        return resultVO;
    }
}

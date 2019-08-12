package com.evilsay.ship.Controller.Mobile;

import com.evilsay.ship.VO.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: EvilSay
 * @Date: 2019/4/1 21:26
 */
@RestController
public class  MobileTest {
    @GetMapping("/networkcheck")
    public ResultVO test(){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("成功");
        resultVO.setCode(1);
        return resultVO;
    }
}

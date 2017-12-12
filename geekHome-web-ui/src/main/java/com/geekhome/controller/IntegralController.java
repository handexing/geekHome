package com.geekhome.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.Integral;
import com.geekhome.entity.dao.IntegralDao;
import com.geekhome.entity.service.IntegralService;

@RestController
@RequestMapping("integral")
public class IntegralController
{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private IntegralService integralService;
    
    @Autowired
    private IntegralDao integralDao;
    
    /**
     * 更新积分和插入次数
     * @param userName 用户名
     * @param currentIntegral 当前积分
     * @return
     */
    @RequestMapping("signInForIntegral")
    @CrossOrigin
    public ExecuteResult<Integral> signInForIntegral(@RequestParam(value = "userName",required=false) String userName,@RequestParam(value = "currentIntegral",required=false) Integer currentIntegral,HttpServletRequest request)
    {
        final ExecuteResult<Integral> result = new ExecuteResult<>();
        try{
            Integral integral = integralDao.findIntegralByUserName(userName);
            if(integral.getState() == 1)
            {
                result.setData(new Integral());
                result.setSuccess(false);
                return result;
            }
            integral = integralService.signInForIntegral(userName, currentIntegral,request);//更新积分和插入次数
            result.setData(integral);
            result.setSuccess(true); 
            return result;
        }
        catch(Exception e){
            logger.error("", e);
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
            result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
        }
        return result;
    }
}

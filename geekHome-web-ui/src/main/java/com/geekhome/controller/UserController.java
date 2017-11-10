package com.geekhome.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.geekhome.common.utils.EmailUtils;
import com.geekhome.common.utils.GeetestConfig;
import com.geekhome.common.utils.GeetestLib;
import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.User;
import com.geekhome.entity.VerifyMessage;
import com.geekhome.entity.dao.UserDao;
import com.geekhome.entity.service.UserService;

/**
 * @Description: 用户
 * @author handx
 * @date 2017年9月12日 下午4:07:46
 * @version V1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailUtils emailUtils;

	@RequestMapping("userRegister")
	@CrossOrigin
	public ExecuteResult<User> userRegister(@RequestBody User user) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			result.setData(userService.saveUser(user));
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@RequestMapping("userLogin")
	@CrossOrigin
	public ExecuteResult<User> userLogin(String userName, String password) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			String pwd = PasswordUtil.createCustomPwd(password, userName + User.SALT);
			User user = userDao.findUserByUserNameAndPassword(userName, pwd);
			result.setData(user);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@RequestMapping("startCaptcha")
	@CrossOrigin
	public void startCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
					GeetestConfig.isnewfailback());

			String resStr = "{}";

			HashMap<String, String> map = new HashMap<>();
			String userid = "geekHome";
			map.put("user_id", userid);

			// 进行验证预处理
			int gtServerStatus = gtSdk.preProcess(map);

			// 将服务器状态设置到session中
			request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
			// 将userid设置到session中
			request.getSession().setAttribute("userid", userid);

			resStr = gtSdk.getResponseStr();
			PrintWriter out = response.getWriter();
			out.println(resStr);

		} catch (final Exception e) {
			logger.error("", e);
		}
	}

	/*@RequestMapping("verifyLogin")
	@CrossOrigin
	public void verifyLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
					GeetestConfig.isnewfailback());

			String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
			String validate = request.getParameter(GeetestLib.fn_geetest_validate);
			String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

			// 从session中获取gt-server状态
			int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

			// 从session中获取userid
			String userid = (String) request.getSession().getAttribute("userid");

			HashMap<String, String> map = new HashMap<>();
			map.put("user_id", userid);

			int gtResult = 0;
			if (gt_server_status_code == 1) {
				// gt-server正常，向gt-server进行二次验证
				gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, map);
				System.out.println(gtResult);
			} else {
				// gt-server非正常情况下，进行failback模式验证
				System.out.println("failback:use your own server captcha validate");
				gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
				System.out.println(gtResult);
			}
			
			Map<String, String> data = new HashMap<>();
			
			if (gtResult == 1) {
				// 验证成功
				PrintWriter out = response.getWriter();
				
//				JSONObject data = new JSONObject();
				try {
					data.put("status", "success");
					data.put("version", gtSdk.getVersionInfo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				out.println(data);
			} else {
				// 验证失败
//				JSONObject data = new JSONObject();
				try {
					data.put("status", "fail");
					data.put("version", gtSdk.getVersionInfo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				PrintWriter out = response.getWriter();
				out.println(data);
			}

		} catch (final Exception e) {
			logger.error("", e);
		}
	}*/
	
	@RequestMapping("modifyPersonInfo")
	@CrossOrigin
	public ExecuteResult<User> modifyPersonInfo(@RequestBody User user) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			result.setData(userService.saveUser(user));
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	/**
	 * 发送验证邮件
	 * @param user
	 * @return
	 */
	@RequestMapping("getEmailCode")
    @CrossOrigin
    public ExecuteResult<User> getEmailCode(@RequestBody User user , HttpServletRequest request) {
        final ExecuteResult<User> result = new ExecuteResult<>();
        try {
            User u = userService.verifyByNameAndEmail(user);
            if(u.getId()!=null){
                String code = EmailUtils.getCode(); 
                emailUtils.doTask(u.getEmail() , code); //异步发送
                result.setData(userService.verifyByNameAndEmail(user));
                result.setSuccess(true);
                //将验证码放于session中保存，存放之前先清除
                if(request.getSession().getAttribute("verifyCode") != null)
                {
                    request.getSession().removeAttribute("verifyCode"); 
                }
                request.getSession().setAttribute("verifyCode", code);
            }else {
                result.setData(new User());
                result.setSuccess(false);
            }
        } catch (final Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
            result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
        }
        return result;
    }
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping("modifyPersonPwd")
    @CrossOrigin
	public ExecuteResult<User> modifyPersonPwd(@RequestBody VerifyMessage verifyMessage, HttpServletRequest request){
	    final ExecuteResult<User> result = new ExecuteResult<>();
	    try{
	        if ( StringUtils.isNotBlank( verifyMessage.getEmailCode() ) && StringUtils.isNotBlank( verifyMessage.getPassword() ) ){
	            String code = (String)request.getSession().getAttribute("verifyCode"); 
	            if(verifyMessage.getEmailCode().equals(code)){
	                String pwd = PasswordUtil.createCustomPwd(verifyMessage.getPassword(), verifyMessage.getUserName() + User.SALT);
	                Integer num = userService.modifyPersonPwd(verifyMessage.getUserName(),pwd);
	                if(num <= 0){
	                    result.setSuccess(false); 
	                    result.setData(new User());
	                    return result;
	                }
	                else{
	                    result.setSuccess(true); 
	                    return result;
	                }
	            }else {
	                result.setSuccess(false);
	                result.setData(new User());
	                result.setErrorCode(ErrorCode.VERIFY_CODE_WRONG.getErrorCode());
	                result.setErrorMsg(ErrorCode.VERIFY_CODE_WRONG.getErrorMsg());
	                return result;
	            }
	        }
	    }catch (final Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
            result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
        }
	    return result;
	}
}

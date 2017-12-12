package com.geekhome.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.geekhome.common.utils.EmailUtils;
import com.geekhome.common.utils.FileUtil;
import com.geekhome.common.utils.GeetestConfig;
import com.geekhome.common.utils.GeetestLib;
import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.common.vo.MarkdownUploadImage;
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
	
	@Value("${upload.path}")
    String upload_dir;//上传地址

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailUtils emailUtils;

	/**
	 * 用户注册
	 * @return
	 */
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

	/**
	 * 用户登录 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("userLogin")
	@CrossOrigin
	public ExecuteResult<User> userLogin(String userName, String password) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			String pwd = PasswordUtil.createCustomPwd(password, userName + User.SALT);
			User user = userService.findUserByUserNameAndPassword(userName, pwd);
			if(user == null)
            {
                result.setSuccess(false);
                result.setErrorCode(ErrorCode.USERNAME_PASSWORD_WRONG.getErrorCode());
                result.setErrorMsg(ErrorCode.USERNAME_PASSWORD_WRONG.getErrorMsg());
            }else {
                result.setSuccess(true);
                result.setData(user);
            }
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

	/*
	 * @RequestMapping("verifyLogin")
	 * 
	 * @CrossOrigin public void verifyLogin(HttpServletRequest request,
	 * HttpServletResponse response) { try { GeetestLib gtSdk = new
	 * GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
	 * GeetestConfig.isnewfailback());
	 * 
	 * String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
	 * String validate = request.getParameter(GeetestLib.fn_geetest_validate);
	 * String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
	 * 
	 * // 从session中获取gt-server状态 int gt_server_status_code = (Integer)
	 * request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
	 * 
	 * // 从session中获取userid String userid = (String)
	 * request.getSession().getAttribute("userid");
	 * 
	 * HashMap<String, String> map = new HashMap<>(); map.put("user_id", userid);
	 * 
	 * int gtResult = 0; if (gt_server_status_code == 1) { //
	 * gt-server正常，向gt-server进行二次验证 gtResult =
	 * gtSdk.enhencedValidateRequest(challenge, validate, seccode, map);
	 * System.out.println(gtResult); } else { // gt-server非正常情况下，进行failback模式验证
	 * System.out.println("failback:use your own server captcha validate"); gtResult
	 * = gtSdk.failbackValidateRequest(challenge, validate, seccode);
	 * System.out.println(gtResult); }
	 * 
	 * Map<String, String> data = new HashMap<>();
	 * 
	 * if (gtResult == 1) { // 验证成功 PrintWriter out = response.getWriter();
	 * 
	 * // JSONObject data = new JSONObject(); try { data.put("status", "success");
	 * data.put("version", gtSdk.getVersionInfo()); } catch (Exception e) {
	 * e.printStackTrace(); } out.println(data); } else { // 验证失败 // JSONObject data
	 * = new JSONObject(); try { data.put("status", "fail"); data.put("version",
	 * gtSdk.getVersionInfo()); } catch (Exception e) { e.printStackTrace(); }
	 * PrintWriter out = response.getWriter(); out.println(data); }
	 * 
	 * } catch (final Exception e) { logger.error("", e); } }
	 */

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

	@RequestMapping("checkUserName")
	@CrossOrigin
	public ExecuteResult<User> checkUserName(String userName) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			User user = userDao.findUserByUserName(userName);
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
	
	@RequestMapping("checkEmail")
	@CrossOrigin
	public ExecuteResult<User> checkEmail(String email) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			User user = userDao.findUserByEmail(email);
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

	/**
	 * 发送验证邮件
	 * 
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
	 * 
	 * @return
	 */
	@RequestMapping("modifyPersonPwd")
	@CrossOrigin
	public ExecuteResult<User> modifyPersonPwd(@RequestBody VerifyMessage verifyMessage, HttpServletRequest request){
	    final ExecuteResult<User> result = new ExecuteResult<>();
	    try{
	        //邮箱修改
	        if ( StringUtils.isNotBlank( verifyMessage.getEmailCode() ) && StringUtils.isNotBlank( verifyMessage.getPassword()) && verifyMessage.getFlag() == 1 ){
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
	        }else
	        {
	            //直接修改
	            String oldPassword = PasswordUtil.createCustomPwd(verifyMessage.getPassword(), verifyMessage.getUserName() + User.SALT);
	            String newPassword = PasswordUtil.createCustomPwd(verifyMessage.getNewPassword(), verifyMessage.getUserName() + User.SALT);
	            User user = userDao.findUserByUserNameAndPassword(verifyMessage.getUserName(), oldPassword);
	            if(user == null)
	            {
	                result.setSuccess(false);
	                result.setErrorCode(ErrorCode.OLD_PWD_WRONG.getErrorCode());
	                result.setErrorMsg(ErrorCode.OLD_PWD_WRONG.getErrorMsg());
	            }else {
	                userService.modifyPersonPwd(verifyMessage.getUserName(),newPassword);
	                result.setSuccess(true); 
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
	
	/**
	 * 修改头像
	 * @param croppedImage 裁剪后的图片
	 * @param request
	 * @return
	 */
	@RequestMapping("modifyAvatar")
	@CrossOrigin
	public MarkdownUploadImage modifyAvatar(@RequestParam(value = "userName", required = false)String userName,
	    @RequestParam(value = "imageName", required = false)String imageName,@RequestParam(value = "croppedImage", required = false)MultipartFile croppedImage, HttpServletRequest request)
	{
	    try {
	        if(FileUtil.isImage(croppedImage.getInputStream())) {
	            String  realPath = upload_dir + File.separator + imageName;
	            File tempFile = new File(realPath);
	            FileUtil.uploadImage(tempFile, croppedImage);
	            //改变头像路径
	            String url = imageName;
	            userDao.modifyAvatar(url,userName);
	            return new MarkdownUploadImage(1, "上传成功！", url);
	        } 
	        else{
	            return new MarkdownUploadImage(0, "上传失败!", null); 
	        }
	    }catch(Exception e) {
	        logger.error("", e);
            return new MarkdownUploadImage(0, "上传失败!", null);
	    }
	}
	
	
}

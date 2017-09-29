package com.geekhome.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description: 密码生成工具类
 * @author handx  
 * @date 2017年9月29日 上午11:16:51 
 * @version V1.0
 */
public class PasswordUtil {

    public static String createAdminPwd(String password, String salt){
        return new SimpleHash("md5",password,ByteSource.Util.bytes(salt),2).toHex();
    }
}

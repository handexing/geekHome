package com.geekhome.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {

    public static String createAdminPwd(String password, String salt){
        return new SimpleHash("md5",password,ByteSource.Util.bytes(salt),2).toHex();
    }
    
    public static String createCustomPwd(String password, String salt){
        return new SimpleHash("md5",password,ByteSource.Util.bytes(salt),1).toHex();
    }
}

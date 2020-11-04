package com.zxe.common.utils;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 5:18 PM 2020/11/4
 * @Version: 1.0
 */
public class PasswordUtils {
    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String ZYD_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    /**
     * AES 加密
     * @param password
     *         未加密的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AESUtils.encrypt(Md5Utils.MD5(salt + ZYD_SECURITY_KEY), password);
    }

    /**
     * AES 解密
     * @param encryptPassword
     *         加密后的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AESUtils.decrypt(Md5Utils.MD5(salt + ZYD_SECURITY_KEY), encryptPassword);
    }
}

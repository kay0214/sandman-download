package com.sandman.film.utils;

import java.security.MessageDigest;

/**
 * Created by sunpeikai on 2018/5/24.
 */
public class PasswordEncrypt {

    /**
     * 根据盐加密密码,加密算法MD5(MD5(password) + salt)
     * @auth sunpeikai
     * @param
     * @return
     */
    public static String getPasswordEncrypt(String password,String salt){
        return MD5(MD5(password) + salt);
    }

    /**
     * MD5加密
     * @auth sunpeikai
     * @param
     * @return
     */
    public final static String MD5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}

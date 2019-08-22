package top.tinx.blog.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 创建人: Wills
 * 创建时间：2019/8/21 17:35
 * 描述: md5加密工具类
 */
public class Md5EncryptionUtil {

    public static String encrypt(String encText,String salt,int encTime){
        SimpleHash simpleHash = new SimpleHash("md5",encText,salt,encTime);
        return simpleHash.toString();
    }
}

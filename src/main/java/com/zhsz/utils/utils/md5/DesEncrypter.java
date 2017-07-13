/*
 * Copyright (c) 2006-2007 by SuyPower
 * All rights reserved.
 */
package com.zhsz.utils.utils.md5;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;



/**
 * 描述： 加密解密工具类
 *
 * @author dhchen
 * @version 1.0
 */
public class DesEncrypter {
    public final static String PASSWORD_CRYPT_KEY = "__jDrog_";
    public final static String DES = "DES";

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作

        Cipher cipher = Cipher.getInstance(DES);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        // 现在，获取数据并加密

        // 正式执行加密操作

        return cipher.doFinal(src);

    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建一个DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作

        Cipher cipher = Cipher.getInstance(DES);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        // 现在，获取数据并解密

        // 正式执行解密操作

        return cipher.doFinal(src);

    }

    /**
     * 对字符串进行加密，使用可逆的加密技术进行操作
     *
     * @param password 设置需要处理字符串
     * @param key      密钥，长度必须是8的倍数
     * @return 返回加密后的数据 String
     */
    public final static String encrypt(String password, String key) {

        try {

            return byte2hex(encrypt(password.getBytes(), key
                    .getBytes()));
        } catch (Exception e) {

        }

        return null;
    }

    /**
     * 对字符串进行解密，解密通过encrypt函数加过密的字符窜
     *
     * @param 设置需要处理字符串
     * @param key       密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据  String
     */
    public final static String decrypt(String password, String key) {

        try {

            return new String(decrypt(hex2byte(password.getBytes()),
                    key.getBytes()));

        } catch (Exception e) {

        }

        return null;
    }

    /**
     * 对字符串进行加密，使用可逆的加密技术进行操作
     *
     * @param password 设置需要处理字符串
     * @return 返回加密后的数据 String
     */
    public final static String encrypt(String password) {
        return encrypt(password, PASSWORD_CRYPT_KEY);
    }

    /**
     * 对字符串进行解密，解密通过encrypt函数加过密的字符窜
     *
     * @param 设置需要处理字符串
     * @return 返回解密后的原始数据  String
     */
    public final static String decrypt(String password) {
        return decrypt(password, PASSWORD_CRYPT_KEY);
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return String
     */
    public static String byte2hex(byte[] b) {

        String hs = "";

        String stmp = "";

        for (int n = 0; n < b.length; n++) {

            stmp = (Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1)

                hs = hs + "0" + stmp;

            else

                hs = hs + stmp;

        }

        return hs.toUpperCase();

    }

    /**
     * 二行制转换
     *
     * @param b
     * @return byte[]
     */
    public static byte[] hex2byte(byte[] b) {

        if ((b.length % 2) != 0)

            throw new IllegalArgumentException("长度不是偶数");

        byte[] b2 = new byte[b.length / 2];

        for (int n = 0; n < b.length; n += 2) {

            String item = new String(b, n, 2);

            b2[n / 2] = (byte) Integer.parseInt(item, 16);

        }

        return b2;
    }

    public static void main(String[] args) {
        String ss = DesEncrypter.encrypt("les", DesEncrypter.PASSWORD_CRYPT_KEY);
        System.out.println(ss);
        String res = DesEncrypter.decrypt(ss, DesEncrypter.PASSWORD_CRYPT_KEY);
        System.out.println(res);
    }

}

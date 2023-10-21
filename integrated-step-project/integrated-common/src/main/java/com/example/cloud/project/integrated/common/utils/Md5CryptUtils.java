package com.example.cloud.project.integrated.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author gys
 * @date 2023/10/21
 * @description
 */
public class Md5CryptUtils {

    public static String md5(byte[] message){
        try {
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算消息的摘要
            byte[] digest = md.digest(message);
            // 将摘要转换为十六进制字符串
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String md5(String message){
        return md5(message.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        System.out.println(md5("test1111"));
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}

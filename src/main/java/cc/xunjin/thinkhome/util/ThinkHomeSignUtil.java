package cc.xunjin.thinkhome.util;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import cc.xunjin.thinkhome.config.GlobalConfig;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public class ThinkHomeSignUtil {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * ThinkHome 的签名算法。
     * @param params body 中需要签名的 键值对。
     * @param secretKey 私钥，ThinkHome 颁发的 secretKey。
     * @param timestamp 时间戳。到 秒。
     * @return
     */
    public static String sign(String params, String secretKey, Long timestamp) {
        StringBuilder sb = new StringBuilder(params);
        sb.append(secretKey).append(timestamp);
        return md5(sb.toString());
    }

    /**
     * ThinkHome 的签名算法。
     * @param bodyParams body 中需要签名的 键值对。
     * @param secretKey 私钥，ThinkHome 颁发的 secretKey。
     * @param timestamp 时间戳。到 秒。
     * @return
     */
    public static String sign(Map<String, Object> bodyParams, String secretKey, Long timestamp) {
        return sign(JSON.toJSONString(bodyParams), secretKey, timestamp);
    }

    /**
     * ThinkHome 的签名算法。
     * @param params body 中需要签名的 键值对。
     * @param timestamp 时间戳。到 秒。
     * @return
     */
    public static String sign(String params, Long timestamp) {
        return sign(params, GlobalConfig.getSecretKey(), timestamp);
    }

    /**
     * 计算字符串的 MD5 摘要值的16进制值。
     * @param str 被计算的字符串。
     * @return MD5摘要值的16进制值字符形式，小写，
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String md5(String str) {
        //确定计算方法
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes("UTF-8"));
            String s = bytesToHex(digest);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 二进制 转 16进制 小写。
     * 参考：https://blog.csdn.net/worm0527/article/details/69939307
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }


}

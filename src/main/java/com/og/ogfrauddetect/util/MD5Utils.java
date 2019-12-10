package com.og.ogfrauddetect.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Optional;
import java.util.Random;

public class MD5Utils {
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest), Charset.forName("UTF-8"));
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }

    private static String getSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        sb.append("0".repeat(16 - sb.length()));
        return sb.toString();
    }

    public static String getSaltMD5(String password) {
        String salt = getSalt();
        password = Optional.ofNullable(md5Hex(password + salt)).orElse("");

        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * 驗證加鹽後的密文和原文是否一樣
     */
    public static boolean getSaltverifyMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String salt = new String(cs2);
        String md5 = Optional.ofNullable(md5Hex(password + salt)).orElse("");
        return md5.equals(String.valueOf(cs1));
    }

}

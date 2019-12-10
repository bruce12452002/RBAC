package com.og.ogfrauddetect;

import com.og.ogfrauddetect.util.MD5Utils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;


public class EncryptTest extends BaseJUnit {

    @Test
//    @Ignore
//    @Repeat(2)
    public void test() {
//        String plaintext = "xxx";
//        String cipherText = MD5Utils.getSaltMD5(plaintext);
//        System.out.println("MD5：" + cipherText);
//        System.out.println("是否一樣:" + MD5Utils.getSaltverifyMD5(plaintext, cipherText));
        System.out.println("是否一樣:" + MD5Utils.getSaltverifyMD5("xxx", "a6bb56d0447c66557fb8c23d47f10f09ec5bb92c84199d53"));
    }

    @Test
    @Ignore
//    @Repeat(5)
    public void test2() {
        String s = "123";
        String repeat = s.repeat(2);
        System.out.println(repeat);
        System.out.println(s);

//        StringBuilder sb = new StringBuilder(16);
//        Random random = new Random();
//        sb.append(random.nextInt(99999999)).append(random.nextInt(99999999));
//        System.out.println(sb);
    }

}

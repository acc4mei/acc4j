package com.acc4j.jasypt;

import org.junit.Test;

public class JasyptSampleTest {
    @Test
    public void jasyptSample() {
        String e1 = JasyptSample.encrypt("simple message", "secret-password", "PBEWithMD5AndDES");
        String e2 = JasyptSample.encrypt("simple message", "secret-password", "PBEWithMD5AndDES");
        String e3 = JasyptSample.encrypt("simple message", "secret-password", "PBEWithMD5AndDES");

        System.out.println("相同的原文多次加密后获取的密文并不一样：");
        System.out.println("第1次加密：" + e1);
        System.out.println("第2次加密：" + e2);
        System.out.println("第3次加密：" + e3);
        System.out.println();

        String d1 = JasyptSample.decrypt(e1, "secret-password", "PBEWithMD5AndDES");
        String d2 = JasyptSample.decrypt(e2, "secret-password", "PBEWithMD5AndDES");
        String d3 = JasyptSample.decrypt(e3, "secret-password", "PBEWithMD5AndDES");

        System.out.println("相同密钥和算法的情况下，即使原文多次加密产生的密文不一样也能解密恢复成原文：");
        System.out.println("第1次解密：" + d1);
        System.out.println("第2次解密：" + d2);
        System.out.println("第3次解密：" + d3);
    }

    @Test
    public void decryptSomething() {
        System.out.println(JasyptSample.decrypt("LQrPL758gMeJ7Q89/zryriCLt6hJVBhC", "EWRREWRERWECCCXC", "PBEWithMD5AndDES"));
    }
}
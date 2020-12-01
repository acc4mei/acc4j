package com.acc4j.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 通常是整合入spring中，使用的第三方jasypt-spring-boot自动配置
 * 用于加密配置文件中的敏感信息，然后通过ENC(...)解密使用，无侵入式，Spring对此无感知
 * .
 * 重要参数：
 * 1. password 自定义密钥，用于salt等加密解密相关信息生成
 * 2. algorithm 加密算法，与JDK和Provider有关，通常使用 PBEWithMD5AndDES
 * 3. stringOutputType 密文编码方式，默认base64，通常不用修改
 * .
 * PS1. 加密解密时要使用 ① 相同的密文相关配置 ② 相同类型的Encryptor 才能成功，否则抛出EncryptionOperationNotPossibleException
 * PS2. jasypt-spring-boot默认使用StandardPBEStringEncryptor
 */
public final class JasyptSample {

    private JasyptSample() {
    }

    public static String encrypt(String message, String password, String algorithm) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password);
        stringEncryptor.setAlgorithm(algorithm);
        return stringEncryptor.encrypt(message);
    }

    public static String decrypt(String encText, String password, String algorithm) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password);
        stringEncryptor.setAlgorithm(algorithm);
        return stringEncryptor.decrypt(encText);
    }
}

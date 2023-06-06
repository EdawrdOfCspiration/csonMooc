package com.cspiration.mooc.utils;

import com.cspiration.mooc.entity.RSA256Key;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，禁止转发和分享
 */
public class SecretKeyUtil {

    //数字签名
    public static final String KEY_ALGORITHM = "RSA";

    //RSA密钥长度
    public static final int KEY_SIZE = 1024;

    //唯一的密钥实例
    public static volatile RSA256Key rsa256Key;

    /**
     * 生成 公钥 & 私钥
     * @return
     */
    public static RSA256Key generateRSA256Key() throws NoSuchAlgorithmException {
        if (rsa256Key == null) {
            synchronized (RSA256Key.class) {
                if (rsa256Key == null) {
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                    keyPairGenerator.initialize(KEY_SIZE);
                    KeyPair keyPair = keyPairGenerator.generateKeyPair();

                    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

                    rsa256Key = new RSA256Key();
                    rsa256Key.setPrivateKey(privateKey);
                    rsa256Key.setPublicKey(publicKey);
                }
            }
        }

        return rsa256Key;
    }
}

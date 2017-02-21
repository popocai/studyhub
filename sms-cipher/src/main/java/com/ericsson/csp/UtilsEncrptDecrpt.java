package com.ericsson.csp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author:gefei
 * @createTime:Nov 5, 2015 10:00:30 AM
 * @className:UtilsEncrptDecrpt.java
 * @classDescription:证书加密，解密
 */
public class UtilsEncrptDecrpt {
    final static String keystorePath      = "D:/ServerCA.p12";                                                         // 证书路径

    final static String keystorePass      = "123456";                                                                  // 证书密码

    final static int    MAX_ENCRYPT_BLOCK = 117;                                                                       // 加密的固定长度，超过117会报错，如果超过117，就分段加密

    final static int    MAX_DECRYPT_BLOCK = 128;                                                                       // 解密的固定长度，如果超过128，就分段解密

    final static int    initKeySize       = 1024;

    static String       source            = "张三 www.xuhang.us张三 www.xuhang.us张三 张三 www.xuhang.us张三 www.xuhang.us张三 张三 "
            + "www.xuhang.us张三 www.xuhang.us张三 张三 www.xuhang.us张三 www.xuhang.us张三 www.xuhang.us张三张三 www.xuhang.us张三 "
            + "www.xuhang.us张三三aa张三张三 www.xuhang.us张三三aa张三张三 www.xuhang.us张三三aa张三王五";                                  // 明文

    // 测试方法
    public static void main(String[] args) {

        UtilsEncrptDecrpt.firstTest();

    }

    @SuppressWarnings("unchecked")
    public static void firstTest() {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            // 初始化确定密钥大小的密钥生成器
            keyPairGenerator.initialize(initKeySize);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            System.out.println("==========|加密|==========");
            // String source2 = "wayne_xuhang@163.com";
            // String key = "123";//mi yue 密钥
            // source = encode(key, source);//jia mi hou d source 加密后的明文
            // signature //qian min hou d shu ju 对加密后的明文签名
            // string source = signature + source + key; // 通过私钥 再次加密 发送

            System.out.println("原始数据字节长度：" + source.getBytes("UTF-8").length + " 原始数据为：" + source);
            KeyStore keyStore = loadKeyStore(keystorePath, keystorePass);
            // System.out.println("keystore type = " + keyStore.getType());
            Enumeration enuml = keyStore.aliases();
            String keyAlias = null;
            if (enuml.hasMoreElements()) {
                keyAlias = (String) enuml.nextElement();
                // System.out.println("alias=[" + keyAlias + "]");
            }

            // 获得私钥
            PrivateKey privateKey = getPrivateKey(keyStore, keyAlias, keystorePass);
            // PrivateKey privateKey = keyPair.getPrivate();
            // 使用私钥加密
            byte[] encrypted = asymmetricEncrypt("RSA/ECB/PKCS1Padding", source.getBytes("UTF-8"), privateKey);
            System.out.println("私钥加密后的字节长度（私钥）：" + encrypted.length);
            // 加密后的数据
            System.out.println("加密算法（私钥）：" + privateKey.getAlgorithm());
            System.out.println("私钥：" + privateKey.getEncoded());
            System.out.println("加密后的数据（十六进制）：" + byte2Hex(encrypted));

            System.out.println("\n==========|解密|==========");
            // 获得公钥
            PublicKey publicKey = getPublicKey(keyStore.getCertificate(keyAlias));
            // PublicKey publicKey = keyPair.getPublic();
            // 使用公钥解密
            byte[] decrypted = asymmetricDecrypt("RSA/ECB/PKCS1Padding", encrypted, publicKey);
            System.out.println("解密后的字节长度（公钥）：" + decrypted.length);
            // 解密后的数据
            System.out.println("解密算法（公钥）：" + publicKey.getAlgorithm());
            System.out.println("公钥：" + publicKey.getEncoded());
            System.out.println("解密后数据（字节）:" + byte2Hex(decrypted));
            System.out.println("解密后的数据（明文）:" + new String(decrypted, "UTF-8"));

            System.out.println("\n==========|签名|==========");
            // 如果私钥使用的rsa算法，这里签名也只能使用rsa算法
            // 获得签名对象Signature和使用的私钥算法必须一致，DSA不能配合md5使用

            // String alg = ((X509Certificate)getCertFromKStore(alias, keyStore)).getSigAlgName();
            Signature signature = Signature.getInstance("SHA1WithDSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(source.getBytes("UTF-8"));
            // signature.update(source2.getBytes("UTF-8"),0,source2.getBytes("UTF-8").length);
            byte[] sign = signature.sign();
            System.out.println("签名plain：" + source);
            System.out.println("签名后数据：" + byte2Hex(sign));
            System.out.println("签名算法/私钥算法：" + signature.getAlgorithm() + "/" + keyPair.getPrivate().getAlgorithm());

            System.out.println("\n==========|验签|==========");
            Signature vSignature = Signature.getInstance("SHA1WithDSA");
            vSignature.initVerify(keyPair.getPublic());
            vSignature.update(source.getBytes("UTF-8"));
            // vSignature.update(source2.getBytes("UTF-8"));
            boolean b = vSignature.verify(hex2Byte(byte2Hex(sign)));
            System.out.println("验签算法/公钥算法：" + vSignature.getAlgorithm() + "/" + keyPair.getPublic().getAlgorithm());
            System.out.println("验签结果：" + b);

            System.out.println("\n==========|手动签名|==========");
            System.out.println("签名plain：" + source);
            byte[] digestText = digest(source);
            System.out.println("信息摘要：" + byte2Hex(digestText));
            byte[] sign1 = asymmetricEncrypt("RSA", digestText, privateKey);
            System.out.println("数字签名：" + byte2Hex(sign1));

            System.out.println("\n==========|手动验签|==========");
            byte[] digest1 = asymmetricDecrypt("RSA", sign1, publicKey);
            System.out.println("解密签名得到的摘要：" + byte2Hex(digest1));
            System.out.println("手动验签结果" + byte2Hex(digest1).equals(byte2Hex(digestText)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 加载密钥库keystore
    public static KeyStore loadKeyStore(String keystorePath, String keystorePass)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        // 提供密钥库类型
        /*
         * KeyStore keyStore = KeyStore.getInstance("PKCS12"); //读取keystore文件的输入流 InputStream in = new
         * FileInputStream(keystorePath); keyStore.load(in, keystorePass.toCharArray());
         */
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream fis = new FileInputStream(keystorePath);
        char[] nPassword = null;
        if ((keystorePass == null) || keystorePass.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = keystorePass.toCharArray();
        }
        keyStore.load(fis, nPassword);
        fis.close();
        return keyStore;

    }

    // 对称加密
    public static byte[] symmetricEncrypt(String transformation, byte[] plainText, Key key) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        cipher.update(plainText);

        return cipher.doFinal();
    }

    // 对称解密
    public static byte[] symmetricDecrypt(String transformation, byte[] cipherText, Key key) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.DECRYPT_MODE, key);

        cipher.update(cipherText);

        return cipher.doFinal();
    }

    // 非对称加密
    public static byte[] asymmetricEncrypt(String transformation, byte[] plainText, PrivateKey key)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        int inputLen = plainText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // int MAX_ENCRYPT_BLOCK = 117;//加密的固定长度，超过117会报错
        // 对数据分段加密
        while ((inputLen - offSet) > 0) {
            if ((inputLen - offSet) > MAX_ENCRYPT_BLOCK) {// 一次加密的字节长度
                cache = cipher.doFinal(plainText, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // cipher.update(plainText);
        // return cipher.doFinal();
        return encryptedData;
    }

    // 非对称解密
    public static byte[] asymmetricDecrypt(String transformation, byte[] cipherText, PublicKey key)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.DECRYPT_MODE, key);

        int inputLen = cipherText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // int MAX_DECRYPT_BLOCK = 128;//解密的固定长度
        // 对数据分段解密
        while ((inputLen - offSet) > 0) {
            if ((inputLen - offSet) > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(cipherText, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(cipherText, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decryptedData;

        // cipher.update(cipherText);
        // return cipher.doFinal();
    }

    // 获取公钥PublicKey
    public static PublicKey getPublicKey(Certificate certificate) {
        return certificate.getPublicKey();
    }

    // 获取私钥PrivateKey
    public static PrivateKey getPrivateKey(KeyStore keyStore, String alias, String certpass)
            throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return (PrivateKey) keyStore.getKey(alias, certpass.toCharArray());
    }

    // 字节数组转十六进制
    public static String byte2Hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte element : b) {
            String hex = Integer.toHexString(0x00ff & element);
            if (hex.length() < 2) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    // 十六进制转字节数组
    public static byte[] hex2Byte(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; (i * 2) < hex.length(); i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, (2 * i) + 2), 16);
        }
        return bytes;
    }

    // 信息摘要
    public static byte[] digest(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");

        md.update(source.getBytes());

        return md.digest();
    }
}

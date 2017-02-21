/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.ericsson.csp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

public class UtilDemo2 {

    // load certificate from file
    public static Certificate loadCertificate(String certPath) throws CertificateException, FileNotFoundException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream in = new FileInputStream(certPath);
        Certificate certificate = certificateFactory.generateCertificate(in);
        return certificate;
    }

    public static byte[] asymmetricRSAEncrypt(byte[] plainText, PrivateKey key) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return asymmetricEncrypt("RSA/ECB/PKCS1Padding", plainText, key);
    }

    public static byte[] asymmetricEncrypt(String transformation, byte[] plainText, PrivateKey key)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipher.update(plainText);
        return cipher.doFinal();
    }

    public static byte[] asymmetricRSADncrypt(byte[] cipherText, PublicKey key) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return asymmetricDecrypt("RSA/ECB/PKCS1Padding", cipherText, key);
    }

    public static byte[] asymmetricDecrypt(String transformation, byte[] cipherText, PublicKey key)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.DECRYPT_MODE, key);

        cipher.update(cipherText);

        return cipher.doFinal();
    }

    public static void printByte(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(bytes[i]);
        }
        System.out.println();
    }

    public static byte[] digest(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(source.getBytes());
        return md.digest();
    }

    public static PrivateKey loadPrivateKeyFromPKCS8File(String keyFile) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(keyFile));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }

    public static byte[] signatureWithPrivateKey(String source, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(source.getBytes());
        byte[] sig = signature.sign();
        return sig;
    }

    @Deprecated
    public static PrivateKey loadPrivateKeyFromOriginalKeyFileDeprecated(String keyFile) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(keyFile));

        String b64encoded = new String(privateKeyBytes);

        b64encoded = b64encoded.replaceAll("-----BEGIN RSA PRIVATE KEY-----\\s", "");
        b64encoded = b64encoded.replaceAll("-----END RSA PRIVATE KEY-----\\s", "");

        System.out.println(b64encoded);

        byte[] asn1PrivateKeyBytes = org.apache.commons.codec.binary.Base64
                .decodeBase64(b64encoded.getBytes("US-ASCII"));
        RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure(
                (ASN1Sequence) ASN1Primitive.fromByteArray(asn1PrivateKeyBytes));
        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(),
                asn1PrivKey.getPrivateExponent());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(rsaPrivKeySpec);
        System.out.println(privKey.getPrivateExponent());

        return privKey;

    }

    public static PrivateKey loadPrivateKeyFromKeyFile(String keyFile) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(keyFile));

        String b64encoded = new String(privateKeyBytes);
        b64encoded = b64encoded.replaceAll("-----BEGIN RSA PRIVATE KEY-----\\s", "");
        b64encoded = b64encoded.replaceAll("-----END RSA PRIVATE KEY-----\\s", "");

        byte[] asn1PrivateKeyBytes = org.apache.commons.codec.binary.Base64
                .decodeBase64(b64encoded.getBytes("US-ASCII"));

        ASN1Sequence seq = ASN1Sequence.getInstance(ASN1Primitive.fromByteArray(asn1PrivateKeyBytes));

        org.bouncycastle.asn1.pkcs.RSAPrivateKey key = org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance(seq);

        RSAPrivateCrtKeySpec privSpec = new RSAPrivateCrtKeySpec(key.getModulus(), key.getPublicExponent(),
                key.getPrivateExponent(), key.getPrime1(), key.getPrime2(), key.getExponent1(), key.getExponent2(),
                key.getCoefficient());

        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(privSpec);
        return privKey;
    }

    public static byte[] signature(String source, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privateKey);
        sig.update(source.getBytes());
        byte[] signature = sig.sign();
        return signature;
    }

    public static boolean verifySignature(String source, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(publicKey);
        sig.update(source.getBytes());
        return sig.verify(signature);
    }

}

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

import static com.ericsson.csp.cipher.utils.ChiperUtils.*;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;

public class CipherTest {
    static String source         = "One is always on a strange road, watching strange scenery and listening to strange music. Then one day, you will find that the things you try hard to forget are already gone.";

    static String privateKeyFile = "certificate/LG_Test_cert_06.key";

    static String publicKeyFile  = "certificate/LG_Test_cert_06.crt";

    public static void main(String[] args) throws Exception {
        InputStream privKeyInputStream = CipherTest.class.getClassLoader().getResourceAsStream(privateKeyFile);
        InputStream publKeyInputStream = CipherTest.class.getClassLoader().getResourceAsStream(publicKeyFile);

        PublicKey publicKey = loadCertificateFromInputStream(publKeyInputStream).getPublicKey();
        System.out.println("Loaded Private Key");
        PrivateKey privateKey = loadPrivateKeyFromInputStream(privKeyInputStream);
        System.out.println("Loaded Public Key");

        System.out.println("--------------- Start  Signature--------------");
        byte[] sign = signature(source, privateKey);
        System.out.println("Sign length: " + sign.length);
        System.out.println("Sign Base64 : " + new String(Base64.encodeBase64(sign)));

        System.out.println("--------------- Verify Signature--------------");
        System.out.println("Result of verify signature: " + verifySignature(source, sign, publicKey));
        System.out.println("--------------- End    Signature--------------");

        System.out.println("--------------- Start Encrypt --------------");
        System.out.println("Source Length : " + source.getBytes().length);
        byte[] encryptedWithPrivateKey = asymmetricRSAEncrypt(source.getBytes("UTF-8"), privateKey);
        System.out.println("Encrypt with private key:");
        System.out.println("Length after encrypt : " + encryptedWithPrivateKey.length);
        System.out.println(new String(Base64.encodeBase64(encryptedWithPrivateKey)));
        System.out.println();
        byte[] encryptedWithPublicKey = asymmetricRSAEncrypt(source.getBytes("UTF-8"), publicKey);
        System.out.println("Encrypt with public key:");
        System.out.println("Length after encrypt : " + encryptedWithPublicKey.length);
        System.out.println(new String(Base64.encodeBase64(encryptedWithPublicKey)));

        System.out.println("--------------- Start Decrypt --------------");
        byte[] decryptedWithPrivateKey = asymmetricRSADecrypt(encryptedWithPublicKey, privateKey);
        byte[] decryptedWithPublicKey = asymmetricRSADecrypt(encryptedWithPrivateKey, publicKey);

        System.out.println(
                "decryptedWithPrivateKey equals source : " + new String(decryptedWithPrivateKey).equals(source));
        System.out.println(
                "decryptedWithPublicKey  equals source : " + new String(decryptedWithPublicKey).equals(source));

    }
}

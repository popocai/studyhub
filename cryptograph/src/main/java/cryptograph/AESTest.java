/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package cryptograph;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESTest {
    static byte[] bytesKey;

    static String SRC = "This is the text";

    public static void main(String[] args) throws Exception {
        // Gen Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // keyGenerator.init(new SecureRandom());
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        bytesKey = key.getEncoded();

        System.out.println("KEY:" + Hex.encodeHexString(bytesKey));

        jdkAES();
        System.out.println("#################################");

        bcDES();
    }

    private static void jdkAES() throws Exception {
        // Key convert
        // DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        // SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
        // SecretKey desKey = factory.generateSecret(desKeySpec);

        Key key = new SecretKeySpec(bytesKey, "AES");

        // 加解密方式/工作模式/填充模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        System.out.println("JDK:" + cipher.getProvider());

        byte[] result = cipher.doFinal(SRC.getBytes());
        String hexResult = Hex.encodeHexString(result);
        System.out.println("Encrypt:" + hexResult);

        cipher.init(Cipher.DECRYPT_MODE, key);
        result = cipher.doFinal(Hex.decodeHex(hexResult.toCharArray())
        // result
        );
        System.out.println("Decrypt:" + new String(result));
    }

    private static void bcDES() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        Key key = new SecretKeySpec(bytesKey, "AES");

        // 加解密方式/工作模式/填充模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        System.out.println("BC:" + cipher.getProvider());

        byte[] result = cipher.doFinal(SRC.getBytes());
        String hexResult = Hex.encodeHexString(result);
        System.out.println("Encrypt:" + hexResult);

        cipher.init(Cipher.DECRYPT_MODE, key);
        result = cipher.doFinal(Hex.decodeHex(hexResult.toCharArray())
        // result
        );
        System.out.println("Decrypt:" + new String(result));
    }
}

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

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DES3Test {
    static byte[] bytesKey;

    static byte[] result;

    public static void main(String[] args) throws Exception {
        // Gen Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        // keyGenerator.init(168);
        keyGenerator.init(new SecureRandom());
        SecretKey key = keyGenerator.generateKey();
        bytesKey = key.getEncoded();

        jdk3DES();
        bc3DES();
    }

    private static void jdk3DES() throws Exception {
        // Key convert

        DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
        SecretKey desKey = factory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);

        System.out.println("JDK:" + cipher.getProvider());

        result = cipher.doFinal("ABC".getBytes());
        String hexResult = Hex.encodeHexString(result);
        System.out.println(hexResult);

        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] decrypt = cipher.doFinal(Hex.decodeHex(hexResult.toCharArray())
        // result
        );
        System.out.println(new String(decrypt));
    }

    private static void bc3DES() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Key convert
        DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
        SecretKey desKey = factory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);

        System.out.println("JDK:" + cipher.getProvider());

        // result = cipher.doFinal("ABC".getBytes());
        // String hexResult = Hex.encodeHexString(result);
        // System.out.println(hexResult);

        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] decrypt = cipher.doFinal(
                // Hex.decodeHex(hexResult.toCharArray())
                result
        );
        System.out.println(new String(decrypt));
    }
}

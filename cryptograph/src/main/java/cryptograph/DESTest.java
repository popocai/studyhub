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

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DESTest {
    static byte[] bytesKey;

    public static void main(String[] args) throws Exception {
     // Gen Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey key = keyGenerator.generateKey();
        bytesKey = key.getEncoded();

        jdkDES();
        bcDES();
    }

    private static void jdkDES() throws Exception {
        // Key convert
        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = factory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);

        System.out.println("JDK:" + cipher.getProvider());

        byte[] result = cipher.doFinal("ABC".getBytes());
        String hexResult = Hex.encodeHexString(result);
        System.out.println(hexResult);

        cipher.init(Cipher.DECRYPT_MODE, desKey);
        result = cipher.doFinal(Hex.decodeHex(hexResult.toCharArray())
        // result
        );
        System.out.println(new String(result));
    }

    private static void bcDES() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Key convert
        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES", "BC");
        SecretKey desKey = factory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);

        System.out.println("BC" + cipher.getProvider());

        byte[] result = cipher.doFinal("ABC".getBytes());
        String hexResult = Hex.encodeHexString(result);
        System.out.println(hexResult);

        cipher.init(Cipher.DECRYPT_MODE, desKey);
        result = cipher.doFinal(Hex.decodeHex(hexResult.toCharArray())
        // result
        );
        System.out.println(new String(result));
    }
}

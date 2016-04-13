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
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class PBETest {
    private static final String ALGORITHM = "PBEWITHMD5andDES";

    static String               password  = "ericsson";

    static String               SRC       = "This is a text";

    static byte[]               result;

    public static void main(String[] args) throws Exception {
        jdkPBE();
    }

    private static void jdkPBE() throws Exception {
        // salt
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        Key key = keyFactory.generateSecret(keySpec);

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);

        result = cipher.doFinal(SRC.getBytes());
        System.out.println("JDK PBE Result : " + Hex.encodeHexString(result));

        PBEKeySpec keySpec2 = new PBEKeySpec("WRONG".toCharArray());
        Key key2 = keyFactory.generateSecret(keySpec2);

        cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
        System.out.println("JDK Decrypt:" + new String(cipher.doFinal(result)));
    }
}

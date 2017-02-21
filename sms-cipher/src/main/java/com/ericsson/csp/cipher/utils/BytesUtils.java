/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.ericsson.csp.cipher.utils;

public class BytesUtils {
    public static byte[] hex2Byte(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; (i * 2) < hex.length(); i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, (2 * i) + 2), 16);
        }
        return bytes;
    }

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
}

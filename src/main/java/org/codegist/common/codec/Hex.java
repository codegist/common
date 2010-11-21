package org.codegist.common.codec;

public final class Hex {

    private Hex() {
    }

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };


    /**
     * Encodes the given bytes to hexadecimal String
     *
     * @param data bytes to encode
     * @return hexadecimal string
     */
    public static String encodeHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0, l = data.length; i < l; i++) {
            sb.append(DIGITS[(0xF0 & data[i]) >>> 4]);
            sb.append(DIGITS[0x0F & data[i]]);
        }
        return sb.toString();
    }

}

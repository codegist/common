package org.codegist.common.lang;

import java.util.Random;

public final class Randoms {
    private static final char[] ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RDM = new Random();

    private Randoms() {
    }

    /**
     * Returns a random alphanumeric string of the specified length
     *
     * @param length string length
     * @return the random alphanumeric string
     */
    public static String randomAlphaNumeric(int length) {
        char[] c = new char[length];
        for (int i = 0; i < length; i++) {
            c[i] = ALPHANUM[RDM.nextInt(ALPHANUM.length)];
        }
        return String.valueOf(c);
    }
}

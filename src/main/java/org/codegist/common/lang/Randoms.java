/*
 * Copyright 2010 CodeGist.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.common.lang;

import java.util.Random;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Randoms {
    private static final char[] ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RDM = new Random();

    private Randoms() {
        throw new IllegalStateException();
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

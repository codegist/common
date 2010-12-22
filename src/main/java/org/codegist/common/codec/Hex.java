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

package org.codegist.common.codec;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Hex {

    private Hex() {
        throw new IllegalStateException();
    }

    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * Encodes the given bytes to hexadecimal String
     *
     * @param data bytes to encode
     * @return hexadecimal string
     */
    public static String encodeAsString(byte[] data) {
        return String.valueOf(encode(data));
    }

    /**
     * Encodes the given bytes to hexadecimal char array
     *
     * @param data bytes to encode
     * @return hexadecimal char array
     */
    public static char[] encode(byte[] data) {
        char[] c = new char[data.length * 2];
        for (int i = 0, j = 0, l = data.length; i < l; i++) {
            c[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            c[j++] = DIGITS[0x0F & data[i]];
        }
        return c;
    }

}

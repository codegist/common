/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.lang;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HashCodeBuilderTest {


    // -----------------------------------------------------------------------

    @Test
    public void testConstructorEx1() {
        try {
            new HashCodeBuilder(0, 0);

        } catch (IllegalArgumentException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testConstructorEx2() {
        try {
            new HashCodeBuilder(2, 2);

        } catch (IllegalArgumentException ex) {
            return;
        }
        fail();
    }

    static class TestObject {
        private int a;

        public TestObject(int a) {
            this.a = a;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestObject)) {
                return false;
            }
            TestObject rhs = (TestObject) o;
            return (a == rhs.a);
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }


    @Test
    public void testSuper() {
        Object obj = new Object();
        assertEquals(17 * 37 + (19 * 41 + obj.hashCode()), new HashCodeBuilder(17, 37).appendSuper(
                new HashCodeBuilder(19, 41).append(obj).hashCode()).hashCode());
    }

    @Test
    public void testObject() {
        Object obj = null;
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj = new Object();
        assertEquals(17 * 37 + obj.hashCode(), new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testLong() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((long) 0L).hashCode());
        assertEquals(17 * 37 + (int) (123456789L ^ (123456789L >> 32)), new HashCodeBuilder(17, 37).append(
                (long) 123456789L).hashCode());
    }

    @Test
    public void testInt() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((int) 0).hashCode());
        assertEquals(17 * 37 + 123456, new HashCodeBuilder(17, 37).append((int) 123456).hashCode());
    }

    @Test
    public void testShort() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((short) 0).hashCode());
        assertEquals(17 * 37 + 12345, new HashCodeBuilder(17, 37).append((short) 12345).hashCode());
    }

    @Test
    public void testChar() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((char) 0).hashCode());
        assertEquals(17 * 37 + 1234, new HashCodeBuilder(17, 37).append((char) 1234).hashCode());
    }

    @Test
    public void testByte() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((byte) 0).hashCode());
        assertEquals(17 * 37 + 123, new HashCodeBuilder(17, 37).append((byte) 123).hashCode());
    }

    @Test
    public void testDouble() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((double) 0d).hashCode());
        double d = 1234567.89;
        long l = Double.doubleToLongBits(d);
        assertEquals(17 * 37 + (int) (l ^ (l >> 32)), new HashCodeBuilder(17, 37).append(d).hashCode());
    }

    @Test
    public void testFloat() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((float) 0f).hashCode());
        float f = 1234.89f;
        int i = Float.floatToIntBits(f);
        assertEquals(17 * 37 + i, new HashCodeBuilder(17, 37).append(f).hashCode());
    }

    @Test
    public void testBoolean() {
        assertEquals(17 * 37 + 0, new HashCodeBuilder(17, 37).append(true).hashCode());
        assertEquals(17 * 37 + 1, new HashCodeBuilder(17, 37).append(false).hashCode());
    }

    @Test
    public void testObjectArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((Object[]) null).hashCode());
        Object[] obj = new Object[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37 + obj[1].hashCode(), new HashCodeBuilder(17, 37).append(obj)
                .hashCode());
    }

    @Test
    public void testObjectArrayAsObject() {
        Object[] obj = new Object[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37 + obj[1].hashCode(), new HashCodeBuilder(17, 37).append(
                (Object) obj).hashCode());
    }

    @Test
    public void testLongArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((long[]) null).hashCode());
        long[] obj = new long[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = 5L;
        int h1 = (int) (5L ^ (5L >> 32));
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = 6L;
        int h2 = (int) (6L ^ (6L >> 32));
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testLongArrayAsObject() {
        long[] obj = new long[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = 5L;
        int h1 = (int) (5L ^ (5L >> 32));
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = 6L;
        int h2 = (int) (6L ^ (6L >> 32));
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testIntArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((int[]) null).hashCode());
        int[] obj = new int[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testIntArrayAsObject() {
        int[] obj = new int[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testShortArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((short[]) null).hashCode());
        short[] obj = new short[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = (short) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = (short) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testShortArrayAsObject() {
        short[] obj = new short[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = (short) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = (short) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testCharArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((char[]) null).hashCode());
        char[] obj = new char[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = (char) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = (char) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testCharArrayAsObject() {
        char[] obj = new char[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = (char) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = (char) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testByteArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((byte[]) null).hashCode());
        byte[] obj = new byte[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = (byte) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = (byte) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testByteArrayAsObject() {
        byte[] obj = new byte[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = (byte) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = (byte) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testDoubleArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((double[]) null).hashCode());
        double[] obj = new double[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = 5.4d;
        long l1 = Double.doubleToLongBits(5.4d);
        int h1 = (int) (l1 ^ (l1 >> 32));
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = 6.3d;
        long l2 = Double.doubleToLongBits(6.3d);
        int h2 = (int) (l2 ^ (l2 >> 32));
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testDoubleArrayAsObject() {
        double[] obj = new double[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = 5.4d;
        long l1 = Double.doubleToLongBits(5.4d);
        int h1 = (int) (l1 ^ (l1 >> 32));
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = 6.3d;
        long l2 = Double.doubleToLongBits(6.3d);
        int h2 = (int) (l2 ^ (l2 >> 32));
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testFloatArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((float[]) null).hashCode());
        float[] obj = new float[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = 5.4f;
        int h1 = Float.floatToIntBits(5.4f);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = 6.3f;
        int h2 = Float.floatToIntBits(6.3f);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testFloatArrayAsObject() {
        float[] obj = new float[2];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = 5.4f;
        int h1 = Float.floatToIntBits(5.4f);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = 6.3f;
        int h2 = Float.floatToIntBits(6.3f);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testBooleanArray() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((boolean[]) null).hashCode());
        boolean[] obj = new boolean[2];
        assertEquals((17 * 37 + 1) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = true;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = false;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    @Test
    public void testBooleanArrayAsObject() {
        boolean[] obj = new boolean[2];
        assertEquals((17 * 37 + 1) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[0] = true;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
        obj[1] = false;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).hashCode());
    }

    @Test
    public void testBooleanMultiArray() {
        boolean[][] obj = new boolean[2][];
        assertEquals((17 * 37) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = new boolean[0];
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = new boolean[1];
        assertEquals((17 * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0] = new boolean[2];
        assertEquals(((17 * 37 + 1) * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[0][0] = true;
        assertEquals(((17 * 37 + 0) * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).hashCode());
        obj[1] = new boolean[1];
        assertEquals((((17 * 37 + 0) * 37 + 1) * 37 + 1), new HashCodeBuilder(17, 37).append(obj).hashCode());
    }

    /**
     * Ensures LANG-520 remains true
     */
    @Test
    public void testToHashCodeEqualsHashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder(17, 37).append(new Object()).append('a');
        assertEquals("hashCode() is no longer returning the same value as hashCode() - see LANG-520",
                hcb.hashCode(), hcb.hashCode());
    }
}

package org.codegist.common.codec;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HexTest {

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Hex.encodeHex(null);
    }

    @Test
    public void testEmpty() {
        assertEquals("", Hex.encodeHex(new byte[0]));
    }

    @Test
    public void test1() {
        assertEquals("000102030405060708090a0b0c0d0e0f1011", Hex.encodeHex(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}));
    }

    @Test
    public void test2() {
        assertEquals("00fffefdfcfbfaf9f8f7f6f5f4f3f2f1f0ef", Hex.encodeHex(new byte[]{0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17}));
    }


}

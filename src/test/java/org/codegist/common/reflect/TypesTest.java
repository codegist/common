package org.codegist.common.reflect;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TypesTest {

    @Test
    public void testDefaultValue() {
        assertEquals(Integer.valueOf(0), Types.getDefaultValueFor(int.class));
        assertEquals(Byte.valueOf((byte) 0), Types.getDefaultValueFor(byte.class));
        assertEquals(Short.valueOf((short) 0), Types.getDefaultValueFor(short.class));
        assertEquals(Long.valueOf(0l), Types.getDefaultValueFor(long.class));
        assertEquals(Float.valueOf(0f), Types.getDefaultValueFor(float.class));
        assertEquals(Double.valueOf(0d), Types.getDefaultValueFor(double.class));
        assertEquals(Boolean.valueOf(false), Types.getDefaultValueFor(boolean.class));
        assertEquals(Character.valueOf((char) 0), Types.getDefaultValueFor(char.class));

        assertEquals(null, Types.getDefaultValueFor(Integer.class));
        assertEquals(null, Types.getDefaultValueFor(Byte.class));
        assertEquals(null, Types.getDefaultValueFor(Short.class));
        assertEquals(null, Types.getDefaultValueFor(Long.class));
        assertEquals(null, Types.getDefaultValueFor(Float.class));
        assertEquals(null, Types.getDefaultValueFor(Double.class));
        assertEquals(null, Types.getDefaultValueFor(Boolean.class));
        assertEquals(null, Types.getDefaultValueFor(Character.class));
        assertEquals(null, Types.getDefaultValueFor(Object.class));
        assertEquals(null, Types.getDefaultValueFor(Object[].class));
    }

    @Test(expected = NullPointerException.class)
    public void testGetTypesNull() {
        Types.getTypes((Class<?>) null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetTypesNull2() {
        Types.getTypes((Class<?>[]) null);
    }

    @Test
    public void testGetTypesEmpty() {
        assertArrayEquals(new Class[0], Types.getTypes());
    }

    @Test
    public void testGetTypes() {
        assertArrayEquals(new Class[]{Integer.class, Boolean.class, String.class, Object.class}, Types.getTypes(1, false, "", new Object()));
    }

    @Test(expected = NullPointerException.class)
    public void testGetTypes2() {
        Types.getTypes(1, null, false, "", new Object());
    }


}

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

package org.codegist.common.reflect;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class TypesTest {

    @Test(expected = NullPointerException.class)
    public void testIsVoidNull(){
        Types.isVoid((Class<?>)null);
    }
    @Test(expected = NullPointerException.class)
    public void testIsVoidNull2(){
        Types.isVoid((Type)null);
    }

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

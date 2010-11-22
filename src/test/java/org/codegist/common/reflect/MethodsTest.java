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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;


/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class MethodsTest {

    @Test
    public void testIsEqualsNull() {
        assertFalse(Methods.isEquals(null));
    }

    @Test
    public void testIsEquals1() throws NoSuchMethodException {
        assertTrue(Methods.isEquals(Object.class.getMethod("equals", Object.class)));
    }

    @Test
    public void testIsEquals2() throws NoSuchMethodException {
        assertFalse(Methods.isEquals(Object.class.getMethod("hashCode")));
    }

    @Test
    public void testIsHashCodeNull() {
        assertFalse(Methods.isHashCode(null));
    }

    @Test
    public void testIsHashCode1() throws NoSuchMethodException {
        assertTrue(Methods.isHashCode(Object.class.getMethod("hashCode")));
    }

    @Test
    public void testIsHashCode2() throws NoSuchMethodException {
        assertFalse(Methods.isHashCode(Object.class.getMethod("equals", Object.class)));
    }

    @Test
    public void testIsToStringNull() {
        assertFalse(Methods.isToString(null));
    }

    @Test
    public void testIsToString1() throws NoSuchMethodException {
        assertTrue(Methods.isToString(Object.class.getMethod("toString")));
    }

    @Test
    public void testIsToString2() throws NoSuchMethodException {
        assertFalse(Methods.isToString(Object.class.getMethod("hashCode")));
    }

    @Test
    public void testIsFromObjectNull() {
        assertFalse(Methods.isFromObject(null));
    }

    @Test
    public void testIsFromObject1() throws NoSuchMethodException {
        assertTrue(Methods.isFromObject(Object.class.getMethod("hashCode")));
        assertTrue(Methods.isFromObject(Object.class.getMethod("equals", Object.class)));
        assertTrue(Methods.isFromObject(Object.class.getMethod("toString")));
    }

    @Test
    public void testIsFromObject2() throws NoSuchMethodException {
        assertFalse(Methods.isFromObject(getClass().getMethod("nonObject")));
    }

    public void nonObject() {
    }


    @Test(expected = NullPointerException.class)
    public void testGetDeclaredMethodsThatMatchesNull() {
        Methods.getDeclaredMethodsThatMatches(null, (String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetDeclaredMethodsThatMatchesNull2() {
        Methods.getDeclaredMethodsThatMatches(Interface.class, (String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetDeclaredMethodsThatMatchesNull3() {
        Methods.getDeclaredMethodsThatMatches(Interface.class, (Pattern) null);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches1() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method.*", false);
        assertArrayEquals(Interface.class.getDeclaredMethods(), meths);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches2() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method2.*", false);
        Method[] expected = new Method[]{
                getMethod(Interface.class, "method2"),
                getMethod(Interface.class, "method2", int.class),
                getMethod(Interface.class, "method2", int.class, String.class),
                getMethod(Interface.class, "method2", int[].class, String.class),
                getMethod(Interface.class, "method2", int[].class, List.class),
                getMethod(Interface.class, "method2bis", int.class),
                getMethod(Interface.class, "method2bis", int.class, String.class),
                getMethod(Interface.class, "method2bis", int[].class, String.class),
                getMethod(Interface.class, "method2bis", int[].class, List.class),
                getMethod(Interface.class, "method2bis"),
        };
        assertExpected(expected, meths);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches3() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method2bis", false);
        Method[] expected = new Method[]{
                getMethod(Interface.class, "method2bis", int.class),
                getMethod(Interface.class, "method2bis", int.class, String.class),
                getMethod(Interface.class, "method2bis", int[].class, String.class),
                getMethod(Interface.class, "method2bis", int[].class, List.class),
                getMethod(Interface.class, "method2bis"),
        };
        assertExpected(expected, meths);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches4() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method.*(int,java\\.lang\\.String)", false);
        Method[] expected = new Method[]{};
        assertExpected(expected, meths);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches5() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method.*\\(int,java\\.lang\\.String\\)", true);
        Method[] expected = new Method[]{
                getMethod(Interface.class, "method", int.class, String.class),
                getMethod(Interface.class, "method2", int.class, String.class),
                getMethod(Interface.class, "method2bis", int.class, String.class),
        };
        assertExpected(expected, meths);
    }

    @Test
    public void testGetDeclaredMethodsThatMatches6() {
        Method[] meths = Methods.getDeclaredMethodsThatMatches(Interface.class, "method.*\\(int(\\[\\])?,java\\.lang\\.String\\)", true);
        Method[] expected = new Method[]{
                getMethod(Interface.class, "method", int.class, String.class),
                getMethod(Interface.class, "method2", int.class, String.class),
                getMethod(Interface.class, "method2bis", int.class, String.class),
                getMethod(Interface.class, "method", int[].class, String.class),
                getMethod(Interface.class, "method2", int[].class, String.class),
                getMethod(Interface.class, "method2bis", int[].class, String.class),
        };
        assertExpected(expected, meths);
    }

    public static void assertExpected(Object[] expected, Object[] vals) {
        assertEquals(expected.length, vals.length);
        assertTrue(Arrays.asList(expected).containsAll(Arrays.asList(vals)));
    }

    public interface Interface {
        public void method();

        public void method(int i);

        public void method(int i, String a);

        public void method(int[] i, String a);

        public void method(int[] i, List<String> a);

        public void method2();

        public void method2(int i);

        public void method2(int i, String a);

        public void method2(int[] i, String a);

        public void method2(int[] i, List<String> a);

        public void method2bis();

        public void method2bis(int i);

        public void method2bis(int i, String a);

        public void method2bis(int[] i, String a);

        public void method2bis(int[] i, List<String> a);


    }

    private static Method getMethod(Class<?> cls, String name, Class<?>... args) {
        try {
            return cls.getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

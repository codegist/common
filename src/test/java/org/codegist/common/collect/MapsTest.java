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

package org.codegist.common.collect;

import org.junit.Test;

import java.util.Collections;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class MapsTest {

    @Test
    public void testExtractByPatternNull(){
        assertNull(Maps.extractByPattern(null, (String[])null));
        assertNull(Maps.extractByPattern(null, (Pattern[])null));
    }

    @Test
    public void testExtractByPatternEmpty(){
        Map<String,Object> m = new HashMap<String, Object>(){{
            put("a","b");
            put("c","d");
        }};
        assertTrue(Maps.extractByPattern(m, "").isEmpty());
    }

    @Test
    public void testExtractByPattern(){
        Map<String,Object> m = new HashMap<String, Object>(){{
            put("a123b","b");
            put("c123d","d");
            put("c456d","f");
            put("cdd","h");
            put("46","h");
            put("65","h");
        }};
        Map<String,Object> expected = new HashMap<String, Object>(){{
            put("c123d","d");
            put("c456d","f");
            put("46","h");
            put("65","h");
        }};
        assertEquals(expected, Maps.extractByPattern(m, "c\\d+d","\\d+"));
    }


    @Test
    public void testFilterNull(){
        assertNull(Maps.filter(null, null));
    }

    @Test
    public void testFilterEmpty(){
        Map m = new HashMap<Object, Object>(){{
            put("a","b");
            put("c","d");
        }};
        assertEquals(m, Maps.filter(m, ""));
    }

    @Test
    public void testFilter(){
        Map m = new HashMap<Object, Object>(){{
            put("a","b");
            put("c","d");
            put("e","f");
            put("g","h");
        }};
        Map expected = new HashMap<Object, Object>(){{
            put("c","d");
        }};
        assertEquals(expected, Maps.filter(m, "a","e","g"));
    }

    @Test
    public void testAreEmptiesNull() {
        assertTrue(Maps.areEmpties(null));
    }

    @Test
    public void testAreEmptiesEmpty1() {
        assertTrue(Maps.areEmpties());
    }

    @Test
    public void testAreEmptiesEmpty2() {
        assertTrue(Maps.areEmpties(new HashMap(), new TreeMap()));
    }

    @Test
    public void testAreEmptiesEmpty3() {
        assertTrue(Maps.areEmpties(new HashMap(), null, new TreeMap()));
    }

    @Test
    public void testNotEmpty() {
        assertFalse(Maps.areEmpties(new HashMap() {{
            put("", "");
        }}, null, new TreeMap()));
    }

    @Test(expected = NullPointerException.class)
    public void testPutIfNotPresentNull1() {
        Maps.putIfNotPresent(null, "key", "val");
    }

    @Test
    public void testPutIfNotPresentNull2() {
        Map<String, String> m = new HashMap<String, String>();
        assertTrue(Maps.putIfNotPresent(m, null, null));
        assertEquals(null, m.get(null));
        assertFalse(Maps.putIfNotPresent(m, null, null));
    }

    @Test
    public void testPutIfNotPresent() {
        Map<String, String> m = new HashMap<String, String>();
        assertTrue(Maps.putIfNotPresent(m, "a", "b"));
        assertEquals("b", m.get("a"));
        assertFalse(Maps.putIfNotPresent(m, "a", "c"));
        assertEquals("b", m.get("a"));
    }

    @Test
    public void testUnmodifiableNull() {
        assertNotNull(Maps.unmodifiable(null));
    }

    @Test
    public void testUnmodifiableNull2() {
        assertNotNull(Maps.unmodifiable(null, true));
    }

    @Test
    public void testUnmodifiableNull3() {
        assertNull(Maps.unmodifiable(null, false));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiable() {
        Map unmodifiable = Maps.unmodifiable(new HashMap());
        unmodifiable.put("a", "n");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiable2() {
        Map unmodifiable = Maps.unmodifiable(null);
        unmodifiable.put("a", "n");
    }

    @Test(expected = NullPointerException.class)
    public void testReverseNull() {
        Maps.reverse(null);
    }

    @Test
    public void testReverseEmpty() {
        Map<Object, List<Object>> reverse = Maps.reverse(new HashMap<Object, Object>());
        assertTrue(reverse.isEmpty());
    }

    @Test
    public void testReverse1() {
        Map<String, List<String>> reverse = Maps.reverse(new HashMap<String, String>() {{
            put("a", "b");
            put("b", "c");
            put("c", "d");
            put("d", "d");
        }});
        assertFalse(reverse.isEmpty());
        assertEquals(java.util.Arrays.asList("a"), reverse.get("b"));
        assertEquals(java.util.Arrays.asList("b"), reverse.get("c"));
        List<String> ds = reverse.get("d");
        Collections.sort(ds);
        assertEquals(java.util.Arrays.asList("c", "d"), ds);
    }


}

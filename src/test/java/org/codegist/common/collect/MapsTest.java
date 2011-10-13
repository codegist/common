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

package org.codegist.common.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class MapsTest {

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

    @Test(expected = NullPointerException.class)
    public void testPutIfNotPresentNull1() {
        Maps.putIfAbsent(null, "key", "val");
    }

    @Test
    public void testPutIfNotPresentNull2() {
        Map<String, String> m = new HashMap<String, String>();
        assertTrue(Maps.putIfAbsent(m, null, null));
        assertEquals(null, m.get(null));
        assertFalse(Maps.putIfAbsent(m, null, null));
    }

    @Test
    public void testPutIfNotPresent() {
        Map<String, String> m = new HashMap<String, String>();
        assertTrue(Maps.putIfAbsent(m, "a", "b"));
        assertEquals("b", m.get("a"));
        assertFalse(Maps.putIfAbsent(m, "a", "c"));
        assertEquals("b", m.get("a"));
    }

}

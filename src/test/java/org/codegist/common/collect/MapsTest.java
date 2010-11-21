package org.codegist.common.collect;

import org.junit.Test;

import java.util.Collections;
import java.util.*;

import static org.junit.Assert.*;

public class MapsTest {

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

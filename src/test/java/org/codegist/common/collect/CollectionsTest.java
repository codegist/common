package org.codegist.common.collect;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionsTest {

    @Test
    public void testNull() {
        assertTrue(Collections.areEmpties(null));
    }

    @Test
    public void testEmpty1() {
        assertTrue(Collections.areEmpties());
    }

    @Test
    public void testEmpty2() {
        assertTrue(Collections.areEmpties(new ArrayList<String>(), new TreeSet<String>()));
    }

    @Test
    public void testEmpty3() {
        assertTrue(Collections.areEmpties(new ArrayList<String>(), null, new TreeSet<String>()));
    }

    @Test
    public void testNoEmpty() {
        assertFalse(Collections.areEmpties(new ArrayList<String>() {{
            add("1");
        }}, new TreeSet<String>()));
    }
}

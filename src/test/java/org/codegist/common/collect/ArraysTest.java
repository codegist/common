package org.codegist.common.collect;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ArraysTest {

    @Test(expected = NullPointerException.class)
    public void testNull1() {
        Arrays.merge(null, new Object[0]);
    }

    @Test(expected = NullPointerException.class)
    public void testNull2() {
        Arrays.merge(String.class, null);
    }

    @Test
    public void testEmpty1() {
        assertArrayEquals(new String[0], Arrays.merge(String.class));
    }

    @Test
    public void testEmpty2() {
        assertArrayEquals(new String[0], Arrays.merge(String.class, new String[0], new String[0]));
    }

    @Test
    public void testNonEmptyWithNull() {
        assertArrayEquals(new String[]{"a", "b"}, Arrays.merge(String.class, new String[0], null, new String[]{"a", "b"}));
    }

    @Test
    public void testMerge() {
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, Arrays.merge(String.class, new String[0], new String[]{"a", "b"}, new String[0], new String[]{"c", "d"}));
    }


}

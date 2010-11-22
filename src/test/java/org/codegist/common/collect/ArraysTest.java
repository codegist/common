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

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
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

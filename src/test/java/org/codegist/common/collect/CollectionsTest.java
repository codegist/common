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

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class CollectionsTest {

    @Test
    public void testNull() {
        assertTrue(Collections.areEmpties((Collection)null));
    }

    @Test(expected = NullPointerException.class)
    public void testNull2() {
        Collections.areEmpties((Collection[])null);
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
        assertFalse(Collections.areEmpties(java.util.Arrays.asList("1"), new TreeSet<String>()));
    }
}

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

package org.codegist.common.lang;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringsTest {

    @Test
    public void testIsBlank(){
        assertTrue(Strings.isBlank("\n\t \n"));
        assertTrue(Strings.isBlank(""));
        assertTrue(Strings.isBlank(null));
        assertTrue(Strings.isBlank(" "));
        assertTrue(Strings.isBlank("    "));
    }
    @Test
    public void testIsNotBlank(){
        assertTrue(Strings.isNotBlank("\n d\n"));
        assertTrue(Strings.isNotBlank("d"));
        assertTrue(Strings.isNotBlank(" ."));
        assertTrue(Strings.isNotBlank("  .  "));
    }
    @Test
    public void testDefaultIfBlank(){
        assertEquals("def", Strings.defaultIfBlank("\n\t \n", "def"));
        assertEquals("def", Strings.defaultIfBlank("", "def"));
        assertEquals("def", Strings.defaultIfBlank(null, "def"));
        assertEquals("def", Strings.defaultIfBlank(" ", "def"));
        assertEquals("def", Strings.defaultIfBlank("    ", "def"));
        assertEquals("\n d\n", Strings.defaultIfBlank("\n d\n", "def"));
        assertEquals("d", Strings.defaultIfBlank("d", "def"));
        assertEquals(" .", Strings.defaultIfBlank(" .", "def"));
        assertEquals("  .  ", Strings.defaultIfBlank("  .  ", "def"));
    }

}

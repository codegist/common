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

package org.codegist.common.log;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class LoggerFactoryTest {

    @Test
    public void testCache(){
        LoggerFactory factory = new LoggerFactory(StdOutLogger.class);
        Logger logger1 = factory.getLogger("test");
        Logger logger2 = factory.getLogger("test");
        assertTrue(logger1 == logger2);
    }
}

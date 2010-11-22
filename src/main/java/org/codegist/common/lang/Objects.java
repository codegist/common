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

package org.codegist.common.lang;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Objects {
    private Objects() {
    }

    /**
     * Returns the given object if not null, otherwise the default one
     *
     * @param o   Object to return if not null
     * @param def Object to return if o is null
     * @param <T> Type arg
     * @return whether o or def
     */
    public static <T> T defaultIfNull(T o, T def) {
        return o != null ? o : def;
    }
}

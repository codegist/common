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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Collections {

    private Collections() {
        throw new IllegalStateException();
    }

    public static <T> String join(String sep, Collection<T> items){
        StringBuilder sb = new StringBuilder();
        int i = 0, max= items.size();
        for(T s : items){
            sb.append(s);
            if(++i < max) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    public static <T> Set<T> asSet(T... values){
        return new HashSet<T>(asList(values));
    }
}

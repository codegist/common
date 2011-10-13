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

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Arrays {

    private Arrays() {
        throw new IllegalStateException();
    }

    /**
     * Merges a list of arrays into a single array.
     *
     * @param cls    Array type
     * @param arrays Arrays to merge
     * @param <T>    Arrays type argument
     * @return merged array
     */
    public static <T> T[] merge(Class<T> cls, T[]... arrays) {
        int totalLength = 0;
        for (T[] arr : arrays) {
            totalLength += arr.length;
        }
        T[] merge = (T[]) java.lang.reflect.Array.newInstance(cls, totalLength);
        int pos = 0;
        for (T[] arr : arrays) {
            System.arraycopy(arr, 0, merge, pos, arr.length);
            pos += arr.length;
        }
        return merge;
    }

    public static <T> String join(String sep, T... items){
        StringBuilder sb = new StringBuilder();
        int i = 0, max= items.length;
        for(T s : items){
            sb.append(s);
            if(++i < max) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    public static <T> T[] arrify(Collection<T> collection, Class<T> clazz){
        return collection.toArray((T[])Array.newInstance(clazz, collection.size()));
    }


    
}

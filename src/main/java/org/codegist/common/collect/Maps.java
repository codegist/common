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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Maps {
    private Maps() {
        throw new IllegalStateException();
    }

    /**
     * Filter the given map values with the given filter keys.
     *
     * @param map Map to filter
     * @param filterKeys Keys to filter
     * @param <K> Map key type
     * @param <V> Map value type
     * @return filtered map where any keys equals to the given one have been removed
     */
    public static <K,V> Map<K,V> filter(Map<K,V> map, K... filterKeys){
        Map<K,V> filtered = new HashMap<K,V>();
        List<K> filter = java.util.Arrays.asList(filterKeys);
        for(Map.Entry<K,V> entry : map.entrySet()){
            if(filter.contains(entry.getKey())) continue;
            filtered.put(entry.getKey(), entry.getValue());
        }
        return filtered;
    }

    /**
     * Puts a key/value entry in the given map if the key is not present or not null
     *
     * @param map   Target map
     * @param key   Key to add
     * @param value Value to add for the given key
     * @param <K>   Key type arg
     * @param <V>   Value type arg
     * @return true if added
     */
    public static <K, V> boolean putIfAbsent(Map<K, V> map, K key, V value) {
        if (map.containsKey(key)) return false;
        map.put(key, value);
        return true;
    }


    public static <K,V> Map<K, V> merge(Map<K, V>... maps){
        Map<K, V> merged = new HashMap<K,V>();
        for(Map<K, V> map : maps){
            merged.putAll(map);
        }
        return merged;
    }

}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Maps {
    private Maps() {
        throw new IllegalStateException();
    }

    /**
     * @see Maps#extractByPattern(java.util.Map, java.util.regex.Pattern...)
     * @param map Map to filter
     * @param filterRegexes Regex strings to use as key filters
     * @param <V> Map value type
     * @return map where keys matches given patterns
     */
    public static <V> Map<String,V> extractByPattern(Map<String,V> map, String... filterRegexes){
        Pattern[] patterns = new Pattern[filterRegexes.length];
        int i = 0;
        for(String regex : filterRegexes) {
            patterns[i++] = Pattern.compile(regex);
        }
        return extractByPattern(map, patterns);
    }

    /**
     * Filters the map elements using given regex pattern over the map keys
     * @param map Map to filter
     * @param filterRegexes Pattern to use as key filters
     * @param <V> Map value type
     * @return map where keys matches given patterns
     */
    public static <V> Map<String,V> extractByPattern(Map<String,V> map, Pattern... filterRegexes){
        Map<String,V> filtered = new HashMap<String,V>();
        for(Pattern p : filterRegexes){
            for(Map.Entry<String,V> entry : map.entrySet()){
                if(!p.matcher(entry.getKey()).matches()) continue;
                filtered.put(entry.getKey(), entry.getValue());
            }
        }
        return filtered;
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

    public static <K,V> Map<K,V> sub(Map<K,V> map, K... keys){
        Map<K,V> filtered = new HashMap<K,V>();
        List<K> filter = java.util.Arrays.asList(keys);
        for(Map.Entry<K,V> entry : map.entrySet()){
            if(!filter.contains(entry.getKey())) continue;
            filtered.put(entry.getKey(), entry.getValue());
        }
        return filtered;
    }

    /**
     * Looks through all the maps and checks if all are null or empty
     *
     * @param maps Maps to check
     * @return return true if all the maps are empty or null
     */
    public static boolean areEmpties(Map... maps) {
        if (maps == null) return true;
        for (Map m : maps) {
            if (m != null && !m.isEmpty()) return false;
        }
        return true;
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


    /**
     * Returns an empty map if given map is null
     *
     * @param map the map to check
     * @param <K> Key type
     * @param <V> Value type
     * @return the given map or a new empty one
     */
    public static <K, V> Map<K, V> defaultsIfNull(Map<K, V> map) {
        return map != null ? map : new HashMap<K, V>();
    }

    /**
     * Returns an unmodifiable view of the given map, if the map is null, return an unmodifiable empty map.
     *
     * @param map The map to get an unmodifiable view from.
     * @param <K> Key Type
     * @param <V> Value Type
     * @return The unmodifiable view of the map.
     */
    public static <K, V> Map<K, V> unmodifiable(Map<K, V> map) {
        return unmodifiable(map, true);
    }

    /**
     * Returns an unmodifiable view of the given map, if emptyIfNull is true and the map is null, return an unmodifiable empty map.
     *
     * @param map         The map to get an unmodifiable view from.
     * @param emptyIfNull Indicates whether to get a empty unmodifiable map if null or not.
     * @param <K>         Key Type
     * @param <V>         Value Type
     * @return The unmodifiable view of the map.
     */
    public static <K, V> Map<K, V> unmodifiable(Map<K, V> map, boolean emptyIfNull) {
        if (emptyIfNull)
            return map != null ? java.util.Collections.unmodifiableMap(map) : java.util.Collections.<K, V>emptyMap();
        else
            return map != null ? java.util.Collections.unmodifiableMap(map) : null;
    }

    /**
     * Reverse the map, keys become values and values becomes keys.
     *
     * @param map The map to reverse
     * @param <K> Key Type
     * @param <V> Value Type
     * @return The reversed map.
     */
    public static <K, V> Map<V, List<K>> reverse(Map<K, V> map) {
        Map<V, List<K>> reverse = new HashMap<V, List<K>>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            List<K> keyList = reverse.get(entry.getValue());
            if (keyList == null) {
                keyList = new ArrayList<K>();
                reverse.put(entry.getValue(), keyList);
            }
            keyList.add(entry.getKey());
        }
        return reverse;
    }

    public static <K,V> Map<K, V> merge(Map<K, V>... maps){
        Map<K, V> merged = new HashMap<K,V>();
        for(Map<K, V> map : maps){
            merged.putAll(map);
        }
        return merged;
    }

}

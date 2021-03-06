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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Objects {
    private Objects() {
        throw new IllegalStateException();
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

    public static String toString(Object o, String defaultIfNull){
        return o != null ? o.toString() : defaultIfNull;
    }

    public static <T> Collection<T> asCollection(T o) {
        if(o == null) {
            return Collections.emptyList();
        }else if (o instanceof Collection) {
            return (Collection<T>) o;
        }else if(o.getClass().isArray()) {
            Collection<T> col = new ArrayList<T>();
            Iterator<T> iter = new ArrayIterator<T>(o);
            while(iter.hasNext()){
                col.add(iter.next());
            }
            return col;
        }else{
            return Collections.singleton(o);
        }

    }
    public static <T> Iterator<T> iterate(Object o) {
        if(o == null) {
            return org.codegist.common.collect.Collections.EMPTY_ITERATOR;
        }else if (o instanceof Collection) {
            return ((Collection<T>) o).iterator();
        }else if(o.getClass().isArray()) {
            return new ArrayIterator<T>(o);
        }else{
            return new SingletonIterator<T>(o);
        }
    }


    private static class SingletonIterator<T> implements Iterator<T> {
        private final AtomicReference<Object> ref;

        private SingletonIterator(Object value) {
            this.ref = new AtomicReference<Object>(value);
        }

        public boolean hasNext() {
            return ref.get() != null;
        }

        public T next() {
            return (T) ref.getAndSet(null);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class ArrayIterator<T> implements Iterator<T> {
        private final Object array;
        private final int length;
        private int index = 0;

        private ArrayIterator(Object array) {
            this.array = array;
            this.length = Array.getLength(array);
        }

        public boolean hasNext() {
            return index < length;
        }

        public T next() {
            return (T) Array.get(array, index++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

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

package org.codegist.common.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * Copyright 2003-2004 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */
public final class Types {
    private Types() {
        throw new IllegalStateException();
    }

    /**
     * Get the underlying class for a type, or null if the type is a variable type.
     *
     * @param type the type
     * @return the underlying class
     * @author Ian Robertson
     * @see <a href="http://www.artima.com/weblogs/viewpost.jsp?thread=208860">http://www.artima.com/weblogs/viewpost.jsp?thread=208860</a>
     */
    public static Class<?> getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Class<?> getComponentClass(Class<?> clazz, Type genericType){
        if(clazz.isArray()) {
            return clazz.getComponentType();
        }else if(Collection.class.isAssignableFrom(clazz)){
            Type typeArg = ((ParameterizedType)genericType).getActualTypeArguments()[0];
            return getClass(typeArg);
        }else if (genericType instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) genericType).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        }else{
            return clazz;
        }
    }

    public static Type getComponentType(Class<?> clazz, Type genericType){
        if(clazz.isArray()) {
            return clazz.getComponentType();
        }else if(Collection.class.isAssignableFrom(clazz)){
            return ((ParameterizedType)genericType).getActualTypeArguments()[0];
        }else if (genericType instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) genericType).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        }else{
            return genericType;
        }
    }

    public static Set<Class<?>> getActors(Type type){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        fillClasses(type, classes);
        return classes;
    }

    private static void fillClasses(Type type, Set<Class<?>> classes){
        Class<?> clazz = getClass(type);
        if(clazz != null) {
            classes.add(clazz);
        }
        if (type instanceof ParameterizedType) {
            for(Type t : ((ParameterizedType) type).getActualTypeArguments()){
                fillClasses(t, classes);
            }
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            fillClasses(componentType, classes);       
        }
    }

    /**
     * @param holder              the Class representing the generic type declaration being instantiated
     * @param actualTypeArguments a (possibly empty) array of types representing the actual type arguments to the parameterized type
     * @return the Type An instance of <tt>ParameterizedType</tt>
     * @see Types#newType(Class, java.lang.reflect.Type[], java.lang.reflect.Type)
     */
    public static ParameterizedType newType(Class<?> holder, Type... actualTypeArguments) {
        return newType(holder, actualTypeArguments, null);
    }

    /**
     * Use this method for generating at runtime a generic Type, eg:
     * <code><pre>
     * Type type = Types.getGenericType(java.util.List.class, new Type[]{java.lang.String.class}, null)
     * </pre></code>
     * <p>The returned type variable will hold generic information for the type java.util.List&lt;java.lang.String&gt;
     *
     * @param holder              the Class representing the generic type declaration being instantiated
     * @param actualTypeArguments a (possibly empty) array of types representing the actual type arguments to the parameterized type
     * @param ownerType           the enclosing type, if known
     * @return the Type An instance of <tt>ParameterizedType</tt>
     */
    public static ParameterizedType newType(Class<?> holder, Type[] actualTypeArguments, Type ownerType) {
        return new ParameterizedTypeImpl(holder, actualTypeArguments, ownerType);
    }

}

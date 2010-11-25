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

import org.codegist.common.collect.Maps;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Types {
    private Types() {
    }

    private static final Map<String, Object> DEFAULTS = Maps.unmodifiable(new HashMap<String, Object>() {{
        put("byte", (byte) 0);
        put("short", (short) 0);
        put("int", 0);
        put("long", 0l);
        put("float", 0f);
        put("double", 0d);
        put("boolean", false);
        put("char", (char) 0);
    }});

    /**
     * Returns the default value for any types including primitives.
     *
     * @param type java type
     * @param <T>
     * @return either null or any primitive default value of type is a primitive
     */
    public static <T> T getDefaultValueFor(Type type) {
        return (T) DEFAULTS.get(String.valueOf(type));
    }

    /**
     * Returns an class array reflecting types of the given object arrays.
     *
     * @param objects array to get the types from
     * @return object types array
     */
    public static Class[] getTypes(Object... objects) {
        Class[] types = new Class[objects.length];
        int i = 0;
        for (Object o : objects) {
            types[i++] = o.getClass();
        }
        return types;
    }

    /**
     * Checks if the given type is void
     *
     * @param type type to check
     * @return true if void
     */
    public static boolean isVoid(Class<?> type) {
        return "void".equals(type.getName());
    }

    /**
     * Checks if the given type is void
     *
     * @param type type to check
     * @return true if void
     */
    public static boolean isVoid(Type type) {
        return "void".equals(String.valueOf(type));
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

    /**
     * Returns the type name from the given class. If given type is an array, returns TypeName[].
     *
     * @param type Type to checks
     * @return type name, supporting arrays.
     */
    public static String getTypeName(Class type) {
        if (type.isArray()) {
            try {
                Class cl = type;
                int dimensions = 0;
                while (cl.isArray()) {
                    dimensions++;
                    cl = cl.getComponentType();
                }
                StringBuffer sb = new StringBuffer();
                sb.append(cl.getName());
                for (int i = 0; i < dimensions; i++) {
                    sb.append("[]");
                }
                return sb.toString();
            } catch (Throwable e) { /*FALLTHRU*/ }
        }
        return type.getName();
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
        return ParameterizedTypeImpl.make(holder, actualTypeArguments, ownerType);
    }
}
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

import java.lang.reflect.Method;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Methods {
    private Methods() {
        throw new IllegalStateException();
    }

    /**
     * Checks if the given method is the Object.equals() method.
     *
     * @param method method to check
     * @return true if the given method is the Object.equals() method
     */
    public static boolean isEquals(Method method) {
        if (!method.getName().equals("equals")) {
            return false;
        }
        Class<?>[] paramTypes = method.getParameterTypes();
        return (paramTypes.length == 1 && paramTypes[0] == Object.class);
    }

    /**
     * Checks if the given method is the Object.hashCode() method.
     *
     * @param method method to check
     * @return true if the given method is the Object.hashCode() method
     */
    public static boolean isHashCode(Method method) {
        return (method.getName().equals("hashCode") && method.getParameterTypes().length == 0);
    }

    /**
     * Checks if the given method is the Object.toString() method.
     *
     * @param method method to check
     * @return true if the given method is the Object.toString() method
     */
    public static boolean isToString(Method method) {
        return (method.getName().equals("toString") && method.getParameterTypes().length == 0);
    }

}

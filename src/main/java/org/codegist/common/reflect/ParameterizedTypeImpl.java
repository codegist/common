/*
 * Copyright 2010 CodeGist.org
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
 *  ==================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.reflect;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;


/**
 * Implementing class for ParameterizedType interface.
 */

class ParameterizedTypeImpl implements ParameterizedType {
    private Type[] actualTypeArguments;
    private Class<?> rawType;
    private Type ownerType;

    ParameterizedTypeImpl(Class<?> rawType,
                                  Type[] actualTypeArguments,
                                  Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        if (ownerType != null) {
            this.ownerType = ownerType;
        } else {
            this.ownerType = rawType.getDeclaringClass();
        }
        TypeVariable[] formals = rawType.getTypeParameters();
        if (formals.length != actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException();
        }
    }


    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }

    public Class<?> getRawType() {
        return rawType;
    }


    public Type getOwnerType() {
        return ownerType;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterizedType) {
            // Check that information is equivalent
            ParameterizedType that = (ParameterizedType) o;

            if (this == that)
                return true;

            Type thatOwner = that.getOwnerType();
            Type thatRawType = that.getRawType();

            return
                    (ownerType == null ?
                            thatOwner == null :
                            ownerType.equals(thatOwner)) &&
                            (rawType == null ?
                                    thatRawType == null :
                                    rawType.equals(thatRawType)) &&
                            Arrays.equals(actualTypeArguments, // avoid clone
                                    that.getActualTypeArguments());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return
                Arrays.hashCode(actualTypeArguments) ^
                        (ownerType == null ? 0 : ownerType.hashCode()) ^
                        (rawType == null ? 0 : rawType.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (ownerType != null) {
            if (ownerType instanceof Class)
                sb.append(((Class) ownerType).getName());
            else
                sb.append(ownerType.toString());

            sb.append(".");

            if (ownerType instanceof ParameterizedTypeImpl) {
                // Find simple name of nested type by removing the
                // shared prefix with owner.
                sb.append(rawType.getName().replace(((ParameterizedTypeImpl) ownerType).rawType.getName() + "$",
                        ""));
            } else
                sb.append(rawType.getName());
        } else
            sb.append(rawType.getName());

        if (actualTypeArguments != null &&
                actualTypeArguments.length > 0) {
            sb.append("<");
            boolean first = true;
            for (Type t : actualTypeArguments) {
                if (!first)
                    sb.append(", ");
                if (t instanceof Class)
                    sb.append(((Class) t).getName());
                else
                    sb.append(t.toString());
                first = false;
            }
            sb.append(">");
        }

        return sb.toString();
    }
}

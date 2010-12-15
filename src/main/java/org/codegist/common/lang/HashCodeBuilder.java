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

/*
 * Class comes from Apache Commons Lang, slightly changed
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codegist.common.lang;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class HashCodeBuilder {
    private final int constant;
    private int hashcode = 0;

    public HashCodeBuilder() {
        this(37, 17);
    }

    public HashCodeBuilder(int hashcode, int constant) {
        Validate.isTrue(constant != 0, "constant must be non zero");
        Validate.isTrue(hashcode != 0, "initial hashcode must be non zero");
        Validate.isTrue(constant % 2 != 0, "initial constant must be an odd value");
        Validate.isTrue(hashcode % 2 != 0, "initial hashcode must be an odd value");
        this.constant = constant;
        this.hashcode = hashcode;
    }


    public HashCodeBuilder append(int value) {
        hashcode = hashcode * constant + value;
        return this;
    }

    public HashCodeBuilder append(short value) {
        hashcode = hashcode * constant + value;
        return this;
    }

    public HashCodeBuilder append(char value) {
        hashcode = hashcode * constant + value;
        return this;
    }

    public HashCodeBuilder append(byte value) {
        hashcode = hashcode * constant + value;
        return this;
    }

    public HashCodeBuilder append(double value) {
        return append(Double.doubleToLongBits(value));
    }

    public HashCodeBuilder append(float value) {
        hashcode = hashcode * constant + Float.floatToIntBits(value);
        return this;
    }

    public HashCodeBuilder append(long value) {
        hashcode = hashcode * constant + ((int) (value ^ (value >> 32)));
        return this;
    }

    public HashCodeBuilder append(boolean value) {
        hashcode = hashcode * constant + (value ? 0 : 1);
        return this;
    }

    public HashCodeBuilder append(Object[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(long[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(int[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(short[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(char[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(byte[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(double[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(float[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(boolean[] array) {
        if (array == null) return appendNull();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
        return this;
    }

    public HashCodeBuilder append(Object object) {
        if (object == null) {
            appendNull();
        } else if (!object.getClass().isArray()) {
            hashcode = hashcode * constant + object.hashCode();
        } else if (object instanceof long[]) {
            append((long[]) object);
        } else if (object instanceof int[]) {
            append((int[]) object);
        } else if (object instanceof short[]) {
            append((short[]) object);
        } else if (object instanceof char[]) {
            append((char[]) object);
        } else if (object instanceof byte[]) {
            append((byte[]) object);
        } else if (object instanceof double[]) {
            append((double[]) object);
        } else if (object instanceof float[]) {
            append((float[]) object);
        } else if (object instanceof boolean[]) {
            append((boolean[]) object);
        } else {
            append((Object[]) object);
        }
        return this;
    }

    private HashCodeBuilder appendNull() {
        hashcode = hashcode * constant;
        return this;
    }

    public int hashCode() {
        return hashcode;
    }

    public HashCodeBuilder appendSuper(int i) {
        return append(i);
    }
}

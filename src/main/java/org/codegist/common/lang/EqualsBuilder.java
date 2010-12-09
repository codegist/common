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

public class EqualsBuilder {
    private boolean equals = true;

    public EqualsBuilder appendSuper(boolean f) {
        if (!equals) return this;
        equals = f;
        return this;
    }
    
    public EqualsBuilder append(int l, int r) {
        if (!equals) return this;
        equals = r == l;
        return this;
    }

    public EqualsBuilder append(short l, short r) {
        if (!equals) return this;
        equals = r == l;
        return this;
    }

    public EqualsBuilder append(char l, char r) {
        if (!equals) return this;
        equals = r == l;
        return this;
    }

    public EqualsBuilder append(byte l, byte r) {
        if (!equals) return this;
        equals = r == l;
        return this;
    }

    public EqualsBuilder append(double l, double r) {
        if (!equals) return this;
        equals = Double.doubleToLongBits(l) == Double.doubleToLongBits(r);
        return this;
    }

    public EqualsBuilder append(float l, float r) {
        if (!equals) return this;
        equals = Float.floatToIntBits(l) == Float.floatToIntBits(r);
        return this;
    }

    public EqualsBuilder append(boolean l, boolean r) {
        if (!equals) return this;
        equals = r == l;
        return this;
    }
    
    public EqualsBuilder append(long l, long r) {
         if (!equals) return this;
           equals = r == l;
        return this;
    }

    public EqualsBuilder append(Object[] l, Object[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(long[] l, long[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(int[] l, int[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(short[] l, short[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(char[] l, char[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(byte[] l, byte[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(double[] l, double[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(float[] l, float[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(boolean[] l, boolean[] r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        if (l.length != r.length) {
            equals = false;
            return this;
        }
        for (int i = 0; i < l.length && equals; ++i) {
            append(l[i], r[i]);
        }
        return this;
    }

    public EqualsBuilder append(Object l, Object r) {
        if (!equals) return this;
        if (l == r) return this;
        if (l == null || r == null) {
            equals = false;
            return this;
        }
        Class lClass = l.getClass();
        if (!lClass.isArray()) {
            if (l instanceof java.math.BigDecimal && r instanceof java.math.BigDecimal) {
                equals = (((java.math.BigDecimal) l).compareTo((java.math.BigDecimal) r) == 0);
            } else {
                equals = l.equals(r);
            }
        } else if (l.getClass() != r.getClass()) {
            equals = false;
        } else if (l instanceof long[]) {
            append((long[]) l, (long[]) r);
        } else if (l instanceof int[]) {
            append((int[]) l, (int[]) r);
        } else if (l instanceof short[]) {
            append((short[]) l, (short[]) r);
        } else if (l instanceof char[]) {
            append((char[]) l, (char[]) r);
        } else if (l instanceof byte[]) {
            append((byte[]) l, (byte[]) r);
        } else if (l instanceof double[]) {
            append((double[]) l, (double[]) r);
        } else if (l instanceof float[]) {
            append((float[]) l, (float[]) r);
        } else if (l instanceof boolean[]) {
            append((boolean[]) l, (boolean[]) r);
        } else {
            append((Object[]) l, (Object[]) r);
        }
        return this;
    }

    public boolean equals() {
        return equals;
    }

    
}

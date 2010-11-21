package org.codegist.common.lang;

import java.util.Arrays;

/**
 * Simple toString builder helper
 */
public class ToStringBuilder {
    private boolean first = true;
    private boolean done = false;
    private final StringBuilder sb;

    public ToStringBuilder(Object o) {
        this.sb = new StringBuilder(o.getClass().getSimpleName() + "[");
    }

    public ToStringBuilder append(String name, short v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, int v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, long v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, float v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, double v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, boolean v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, byte v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, char v) {
        appendComa();
        sb.append(name).append("=").append(v);
        return this;
    }

    public ToStringBuilder append(String name, short[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, int[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, long[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, float[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, double[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, boolean[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, byte[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, char[] v) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(v));
        return this;
    }

    public ToStringBuilder append(String name, Object[] field) {
        appendComa();
        sb.append(name).append("=").append(Arrays.toString(field));
        return this;
    }

    public ToStringBuilder append(String name, Object field) {
        appendComa();
        sb.append(name).append("=").append(field);
        return this;
    }

    private void appendComa() {
        if (!first) {
            sb.append(",");
        }
        first = false;
    }

    public String toString() {
        if (!done) {
            sb.append("]");
            done = true;
        }
        return sb.toString();
    }
}

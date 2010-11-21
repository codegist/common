package org.codegist.common.lang;

public final class Objects {
    private Objects() {
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
}

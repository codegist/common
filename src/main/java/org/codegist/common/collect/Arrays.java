package org.codegist.common.collect;

public final class Arrays {

    private Arrays() {
    }

    /**
     * Merges a list of arrays into a single array.
     *
     * @param cls    Array type
     * @param arrays Arrays to merge
     * @param <T>    Arrays type argument
     * @return merged array
     */
    public static <T> T[] merge(Class<T> cls, T[]... arrays) {
        int totalLength = 0;

        for (T[] arr : arrays) {
            if (arr == null) continue;
            totalLength += arr.length;
        }
        T[] merge = (T[]) java.lang.reflect.Array.newInstance(cls, totalLength);
        int pos = 0;
        for (T[] arr : arrays) {
            if (arr == null) continue;
            System.arraycopy(arr, 0, merge, pos, arr.length);
            pos += arr.length;
        }
        return merge;
    }

}

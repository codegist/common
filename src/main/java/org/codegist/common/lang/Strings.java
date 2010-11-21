package org.codegist.common.lang;

public final class Strings {
    private Strings() {
    }

    /**
     * Checks if the given string is null or blank by trimming it and checking that the length it not 0
     *
     * @param str String to check
     * @return return if the string is blank
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }


    /**
     * Checks if the given string is not null and not blank
     *
     * @param str String to check
     * @return return if the string is blank
     * @see Strings#isBlank(String)
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Returns either str if not blank otherwise def
     *
     * @param str String to check
     * @param def Default to return if str is blank
     * @return either str or def
     */
    public static String defaultIfBlank(String str, String def) {
        return isBlank(str) ? def : str;
    }

}

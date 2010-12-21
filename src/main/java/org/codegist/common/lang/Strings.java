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

package org.codegist.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Strings {
    private Strings() {
        throw new IllegalStateException();
    }

    /**
     * Extract all groups defined in the given pattern for the given string
     * @param pattern Pattern containing groups
     * @param src Input string
     * @return string array of extract groups. Empty if none if find, and the first element correspond to the global match.
     */
    public static String[] extractGroups(String pattern, String src){
        return extractGroups(Pattern.compile(pattern), src);
    }

    /**
     * Extract all groups defined in the given pattern for the given string
     * @param pattern Pattern containing groups
     * @param src Input string
     * @return string array of extract groups. Empty if none if find, and the first element correspond to the global match.
     */
    public static String[] extractGroups(Pattern pattern, String src){
        Matcher matcher = pattern.matcher(src);
        boolean matchFound = matcher.find();
        List<String> groups = new ArrayList<String>();
        if(matchFound) {
            // Get all groups for this match
            for (int i=0; i<=matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }
        }
        return groups.toArray(new String[groups.size()]);
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

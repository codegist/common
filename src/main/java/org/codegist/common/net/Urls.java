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

package org.codegist.common.net;


import org.codegist.common.lang.Objects;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Urls {

    private static final Pattern EQUAL = Pattern.compile("=");

    private Urls() {
        throw new IllegalStateException();
    }

    public static String encode(String value, Charset encoding) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(value, encoding.displayName());
        StringBuilder sb = new StringBuilder(encoded.length());
        char focus;

        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb.append("%2A");
            } else if (focus == '+') {
                sb.append("%20");
            } else if (focus == '%' && (i + 1) < encoded.length() && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(focus);
            }
        }
        return sb.toString();
    }

    /**
     * Parses the given URI's query string and return the parameter map using the system default encoding
     *
     * @param uri URI to parse the query string from
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(URI uri) {
        return parseQueryString(uri, Charset.defaultCharset());
    }

    /**
     * Parses the given query string and return the parameter map using the system default encoding
     *
     * @param queryString QueryString to parse the query string from
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(String queryString) {
        return parseQueryString(queryString, Charset.defaultCharset());
    }

    /**
     * Parses the given URI's query string and return the parameter map using the given encoding
     *
     * @param uri      URI to parse the query string from
     * @param encoding Encoding to use to decode the query string
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(URI uri, Charset encoding) {
        String qs = Objects.defaultIfNull(uri.getRawQuery(), "");
        return parseQueryString(qs, encoding);
    }

    /**
     * Parses the given query string and return the parameter map using the given encoding
     *
     * @param queryString QueryString to parse the query string from
     * @param encoding    Encoding to use to decode the query string
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(String queryString, Charset encoding) {
        Map<String, String> params = new LinkedHashMap<String, String>();
        Scanner scanner = new Scanner(queryString).useDelimiter("&");
        while (scanner.hasNext()) {
            final String[] nameValue = EQUAL.split(scanner.next());
            if (nameValue.length == 0 || nameValue.length > 2)
                throw new IllegalArgumentException("Invalid parameter!");

            final String name = decode(nameValue[0], encoding);
            String value = null;
            if (nameValue.length == 2)
                value = decode(nameValue[1], encoding);
            params.put(name, value);
        }
        return params;
    }

    public static String getQueryString(String url){
        int len = url.length();
        int query = url.indexOf('?') + 1;
        if(query != 0 && query < len) {
            return url.substring(query, len);
        }else{
            return null;
        }
    }

    /**
     * Decode the given url encoded content using the given encoding
     *
     * @param content  url encoded content
     * @param encoding encoding
     * @return decoded content
     */
    public static String decode(String content, Charset encoding) {
        try {
            return URLDecoder.decode(content, encoding.displayName());
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    /**
     * Indicates if the given url has a query string
     *
     * @param url Given url, supposed to be a valid url
     * @return true if a query string is present
     */
    public static boolean hasQueryString(String url) {
        int index = url.indexOf('?');
        return index != -1 && index < url.length();
    }

    /**
     * Remove any double-slash occurence in the url path
     *
     * @param url Given url, supposed to be a valid url
     * @return slash normalized url
     */
    public static String normalizeSlashes(String url) {
        String pUrl = url;
        String protocol, server, queryString = null;
        if (hasQueryString(pUrl)) {
            int questionMark = pUrl.indexOf('?');
            queryString = pUrl.substring(questionMark);
            pUrl = pUrl.substring(0, questionMark);
        }
        int sepP = pUrl.indexOf("://");
        int sepPEnd = sepP + 3;
        int firstSlash = pUrl.indexOf('/', sepPEnd);

        protocol = pUrl.substring(0, sepP);
        if (firstSlash > -1) {
            server = pUrl.substring(sepPEnd, firstSlash);
            pUrl = pUrl.substring(firstSlash);
        } else {
            server = pUrl.substring(sepPEnd);
            pUrl = "";
        }
        StringBuilder urlsb = new StringBuilder().append(protocol).append("://").append(server);
        if (pUrl.length() > 1) {
            char pre = pUrl.charAt(0);
            urlsb.append(pre);
            for (int i = 1; i < pUrl.length(); i++) {
                char c = pUrl.charAt(i);
                if (pre != '/' || c != '/') {
                    urlsb.append(c);
                }
                pre = c;

            }
        } else if (pUrl.length() > 0) {
            urlsb.append(pUrl);
        }

        if (queryString != null) {
            return urlsb.append(queryString).toString();
        } else {
            return urlsb.toString();
        }
    }
}


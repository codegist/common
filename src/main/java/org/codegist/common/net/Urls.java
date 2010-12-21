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

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Urls {

    private Urls() {
        throw new IllegalStateException();
    }

    /**
     * Builds a url query string part using the given parameter, encoded with the given encoding. Do not contains the leading "?"
     *
     * @param params   Parameter to include in the query string
     * @param encoding Encoding to use
     * @return query string
     * @throws UnsupportedEncodingException
     */
    public static String buildQueryString(Map<String, String> params, String encoding) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder();
        int i = 0, max = params.size();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.append(entry.getKey());
            urlBuilder.append("=");
            urlBuilder.append(encode(entry.getValue(), encoding));
            if (++i < max) {
                urlBuilder.append("&");
            }

        }
        return urlBuilder.toString();
    }

    public static String encode(String value, String encoding) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(value, encoding);
        StringBuffer buf = new StringBuffer(encoded.length());
        char focus;

        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                buf.append("%2A");
            } else if (focus == '+') {
                buf.append("%20");
            } else if (focus == '%' && (i + 1) < encoded.length() && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                buf.append('~');
                i += 2;
            } else {
                buf.append(focus);
            }
        }
        return buf.toString();
    }

    /**
     * Parses the given URI's query string and return the parameter map using the system default encoding
     *
     * @param uri URI to parse the query string from
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(URI uri) {
        return parseQueryString(uri, Charset.defaultCharset().displayName());
    }

    /**
     * Parses the given query string and return the parameter map using the system default encoding
     *
     * @param queryString QueryString to parse the query string from
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(String queryString) {
        return parseQueryString(queryString, Charset.defaultCharset().displayName());
    }

    /**
     * Parses the given URI's query string and return the parameter map using the given encoding
     *
     * @param uri      URI to parse the query string from
     * @param encoding Encoding to use to decode the query string
     * @return Map of query string parameters
     */
    public static Map<String, String> parseQueryString(URI uri, String encoding) {
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
    public static Map<String, String> parseQueryString(String queryString, String encoding) {
        Map<String, String> params = new LinkedHashMap<String, String>();
        Scanner scanner = new Scanner(queryString).useDelimiter("&");
        while (scanner.hasNext()) {
            final String[] nameValue = scanner.next().split("=");
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

    /**
     * Decode the given url encoded content using the given encoding
     *
     * @param content  url encoded content
     * @param encoding encoding
     * @return decoded content
     */
    static String decode(String content, String encoding) {
        try {
            return URLDecoder.decode(content, encoding);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

}


package org.codegist.common.net;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public final class Urls {

    private Urls() {
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
            urlBuilder.append(URLEncoder.encode(entry.getValue(), encoding));
            if (++i < max) {
                urlBuilder.append("&");
            }

        }
        return urlBuilder.toString();
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
        return uri == null ? new LinkedHashMap<String, String>() : parseQueryString(uri.getRawQuery(), encoding);
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
        if (queryString != null && queryString.length() > 0) {
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
    private static String decode(String content, String encoding) {
        try {
            return URLDecoder.decode(content, encoding);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

}


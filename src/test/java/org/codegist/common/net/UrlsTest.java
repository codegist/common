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

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class UrlsTest {
    @Test(expected = NullPointerException.class)
    public void testHasQueryStringNull(){
        Urls.hasQueryString(null);
    }

    @Test
    public void testHasQueryString(){
        assertFalse(Urls.hasQueryString("http://120.0.0.1:8080/hello/world"));
        assertTrue(Urls.hasQueryString("http://120.0.0.1:8080/hello/world?"));
        assertTrue(Urls.hasQueryString("http://120.0.0.1:8080/hello/world?d"));
        assertTrue(Urls.hasQueryString("http://120.0.0.1:8080/hello/world?d=s"));
    }

    @Test(expected = NullPointerException.class)
    public void testNormalizeSlashesNull(){
        Urls.normalizeSlashes(null);
    }
    @Test
    public void testNormalizeSlashes(){
        assertEquals("http://120.0.0.1:8080/hello/world?d=s", Urls.normalizeSlashes("http://120.0.0.1:8080/hello/world?d=s"));
        assertEquals("http://120.0.0.1:8080/hello/world", Urls.normalizeSlashes("http://120.0.0.1:8080/hello/world"));
        assertEquals("http://120.0.0.1:8080/hello/world/world/?d=s", Urls.normalizeSlashes("http://120.0.0.1:8080/hello//world///world//?d=s"));
        assertEquals("http://120.0.0.1:8080/hello/world", Urls.normalizeSlashes("http://120.0.0.1:8080//hello//world"));
        assertEquals("http://120.0.0.1:8080", Urls.normalizeSlashes("http://120.0.0.1:8080"));
        assertEquals("http://120.0.0.1:8080?sd?sd", Urls.normalizeSlashes("http://120.0.0.1:8080?sd?sd"));
        assertEquals("http://120.0.0.1:8080/", Urls.normalizeSlashes("http://120.0.0.1:8080/"));
        assertEquals("http://120.0.0.1:8080/", Urls.normalizeSlashes("http://120.0.0.1:8080//"));
        assertEquals("http://120.0.0.1:8080/?sdsd", Urls.normalizeSlashes("http://120.0.0.1:8080//?sdsd"));
    }


    @Test
    public void testEncode() throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("abcABC123", "abcABC123");
        map.put("-._~", "-._~");
        map.put("%", "%25");
        map.put("+", "%2B");
        map.put("&=*", "%26%3D%2A");
        map.put("\n", "%0A");
        map.put("\u0020", "%20");// Space
        map.put("\u007F", "%7F");
        map.put("\u0080", "%C2%80");
        map.put("\u3001", "%E3%80%81");
        for (Map.Entry<String, String> e : map.entrySet()) {
            String val = Urls.encode(e.getKey(), "utf-8");
            assertEquals(e.getValue(), val);
        }
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("abcABC123", "abcABC123");
        map.put("-._~", "-._~");
        map.put("%25", "%");
        map.put("%2B", "+");
        map.put("%26%3D%2A", "&=*");
        map.put("%0A", "\n");
        map.put("%20", "\u0020");// Space
        map.put("%7F", "\u007F");
        map.put("%C2%80", "\u0080");
        map.put("%E3%80%81", "\u3001");
        for (Map.Entry<String, String> e : map.entrySet()) {
            String val = Urls.decode(e.getKey(), "utf-8");
            assertEquals(e.getValue(), val);
        }
    }


    static final Map<String, String> PARAMS = new LinkedHashMap<String, String>() {{
        put("a", "b");
        put("c", "d");
        put("e", "f");
        put("g", "h & f");
        put("special", "sadely all is about $$$ and £££");
    }};
    static final String EXPECTED_UTF8 = "a=b&c=d&e=f&g=h%20%26%20f&special=sadely%20all%20is%20about%20%24%24%24%20and%20%C2%A3%C2%A3%C2%A3";
    static final String EXPECTED_ISO88591 = "a=b&c=d&e=f&g=h%20%26%20f&special=sadely%20all%20is%20about%20%24%24%24%20and%20%A3%A3%A3";

    @Test(expected = NullPointerException.class)
    public void testBuildQueryStringNull() throws UnsupportedEncodingException {
        Urls.buildQueryString(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testBuildQueryStringNull2() throws UnsupportedEncodingException {
        Urls.buildQueryString(new HashMap<String, String>() {{
            put("", "");
        }}, null);
    }

    @Test
    public void testBuildQueryStringEmpty() throws UnsupportedEncodingException {
        assertEquals("", Urls.buildQueryString(new HashMap<String, String>(), null));
    }

    @Test
    public void testBuildQueryString1UTF8() throws UnsupportedEncodingException {
        assertEquals(EXPECTED_UTF8, Urls.buildQueryString(PARAMS, "utf-8"));
    }

    @Test
    public void testBuildQueryStringISO88591() throws UnsupportedEncodingException {
        assertEquals(EXPECTED_ISO88591, Urls.buildQueryString(PARAMS, "ISO-8859-1"));
    }

    @Test(expected = NullPointerException.class)
    public void testParseNull() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((String) null).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParseNull2() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((URI) null).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParseNull3() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((String) null, null).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParseNull4() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((URI) null, null).isEmpty());
    }

    @Test
    public void testParseEmpty() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString("").isEmpty());
    }

    @Test
    public void testParseEmpty2() throws UnsupportedEncodingException, URISyntaxException {
        assertTrue(Urls.parseQueryString(new URI("http://test")).isEmpty());
    }

    @Test
    public void testParseEmpty3() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString("", null).isEmpty());
    }

    @Test
    public void testParseEmpty4() throws UnsupportedEncodingException, URISyntaxException {
        assertTrue(Urls.parseQueryString(new URI("http://test"), null).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParse1() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString(EXPECTED_UTF8, null).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testParse2() throws UnsupportedEncodingException, URISyntaxException {
        assertTrue(Urls.parseQueryString(new URI("http://test?" + EXPECTED_UTF8), null).isEmpty());
    }


    @Test
    public void testParse3() throws UnsupportedEncodingException {
        Map<String, String> params = Urls.parseQueryString(EXPECTED_UTF8, "utf-8");
        // tests this order of the map
        assertEquals(Arrays.asList("a", "c", "e", "g", "special"), new ArrayList(params.keySet()));
        assertEquals(PARAMS, params);
    }

    @Test
    public void testParse4() throws UnsupportedEncodingException {
        Map<String, String> params = Urls.parseQueryString(EXPECTED_UTF8, "ISO-8859-1");
        // tests this order of the map
        assertEquals(Arrays.asList("a", "c", "e", "g", "special"), new ArrayList(params.keySet()));
        assertFalse(PARAMS.equals(params));
    }

    @Test
    public void testParse5() throws UnsupportedEncodingException {
        Map<String, String> params = Urls.parseQueryString(EXPECTED_ISO88591, "ISO-8859-1");
        // tests this order of the map
        assertEquals(Arrays.asList("a", "c", "e", "g", "special"), new ArrayList(params.keySet()));
        assertEquals(PARAMS, params);
    }

    @Test
    public void testParse6() throws UnsupportedEncodingException {
        Map<String, String> params = Urls.parseQueryString(EXPECTED_ISO88591, "utf-8");
        // tests this order of the map
        assertEquals(Arrays.asList("a", "c", "e", "g", "special"), new ArrayList(params.keySet()));
        assertFalse(PARAMS.equals(params));
    }


}

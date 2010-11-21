package org.codegist.common.net;

import org.codegist.common.net.Urls;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.*;

public class UrlsTest {

    static final Map<String, String> PARAMS = new LinkedHashMap<String, String>() {{
        put("a", "b");
        put("c", "d");
        put("e", "f");
        put("g", "h & f");
        put("special", "sadely all is about $$$ and £££");
    }};
    static final String EXPECTED_UTF8 = "a=b&c=d&e=f&g=h+%26+f&special=sadely+all+is+about+%24%24%24+and+%C2%A3%C2%A3%C2%A3";
    static final String EXPECTED_ISO88591 = "a=b&c=d&e=f&g=h+%26+f&special=sadely+all+is+about+%24%24%24+and+%A3%A3%A3";

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

    @Test
    public void testParseNull() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((String) null).isEmpty());
    }

    @Test
    public void testParseNull2() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((URI) null).isEmpty());
    }

    @Test
    public void testParseNull3() throws UnsupportedEncodingException {
        assertTrue(Urls.parseQueryString((String) null, null).isEmpty());
    }

    @Test
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

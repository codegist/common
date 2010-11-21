package org.codegist.common.io;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class IOsTest {

    @Test(expected = NullPointerException.class)
    public void testCopyReaderWriterNull() throws IOException {
        IOs.copy((Reader) null, null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyReaderWriterNull2() throws IOException {
        IOs.copy(new StringReader("abc"), null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyReaderWriterNull3() throws IOException {
        IOs.copy(null, new StringWriter(), true);
    }

    @Test
    public void testCopyReaderWriterNoClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        StringReader in = new StringReader("abc\n£$") {
            @Override
            public void close() {
                super.close();
                bool.change();
            }
        };
        StringWriter out = new StringWriter();
        IOs.copy(in, out, false);
        assertEquals("abc\n£$", out.toString());
        assertFalse(bool.getVal());
    }

    @Test
    public void testCopyReaderWriterClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        StringReader in = new StringReader("abc\n£$") {
            @Override
            public void close() {
                super.close();
                bool.change();
            }
        };
        StringWriter out = new StringWriter();
        IOs.copy(in, out, true);
        assertEquals("abc\n£$", out.toString());
        assertTrue(bool.getVal());
    }

    @Test
    public void testCopyReaderWriterErrorClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        Reader in = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException();
            }

            public void close() {
                bool.change();
            }
        };
        Writer out = new StringWriter();
        try {
            IOs.copy(in, out, true);
            fail("Shoul have failed");
        } catch (IOException e) {
            assertTrue(bool.getVal());
        }

    }

    @Test
    public void testCopyReaderWriterErrorNoClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        Reader in = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException();
            }

            public void close() {
                bool.change();
            }
        };
        Writer out = new StringWriter();
        try {
            IOs.copy(in, out, false);
            fail("Shoul have failed");
        } catch (IOException e) {
            assertFalse(bool.getVal());
        }

    }


    @Test(expected = NullPointerException.class)
    public void testCopyStreamsNull() throws IOException {
        IOs.copy((InputStream) null, null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyStreamsNull2() throws IOException {
        IOs.copy(new ByteArrayInputStream(new byte[]{1, 2, 3, 4}), null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyStreamsNull3() throws IOException {
        IOs.copy(null, new ByteArrayOutputStream(), true);
    }


    @Test
    public void testCopyStreamsNoClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3}) {
            @Override
            public void close() {
                bool.change();
            }
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOs.copy(in, out, false);
        assertArrayEquals(new byte[]{1, 2, 3}, out.toByteArray());
        assertFalse(bool.getVal());
    }

    @Test
    public void testCopyStreamsClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3}) {
            @Override
            public void close() {
                bool.change();
            }
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOs.copy(in, out, true);
        assertArrayEquals(new byte[]{1, 2, 3}, out.toByteArray());
        assertTrue(bool.getVal());
    }

    @Test
    public void testCopyStreamsErrorClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }

            public void close() {
                bool.change();
            }
        };
        OutputStream out = new ByteArrayOutputStream();
        try {
            IOs.copy(in, out, true);
            fail("Shoul have failed");
        } catch (IOException e) {
            assertTrue(bool.getVal());
        }

    }

    @Test
    public void testCopyStreamsErrorNoClose() throws IOException {
        final MutableBoolean bool = new MutableBoolean();
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }

            public void close() {
                bool.change();
            }
        };
        OutputStream out = new ByteArrayOutputStream();
        try {
            IOs.copy(in, out, false);
            fail("Shoul have failed");
        } catch (IOException e) {
            assertFalse(bool.getVal());
        }

    }

    private static class MutableBoolean {
        private boolean val;

        public void change() {
            val = !val;
        }

        public boolean getVal() {
            return val;
        }
    }

}

/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.common.io;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class IOs {
    private IOs() {
        throw new IllegalStateException();
    }

    /**
     * The default buffer size to use.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;


    /**
     * Reads the given input stream into a string using the default system charset. Does not close the input stream.
     *
     * @param inputStream Source
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream) throws IOException {
        return toString(new InputStreamReader(inputStream), false);
    }

    /**
     * Reads the given input stream into a string using the default system charset.
     *
     * @param inputStream Source
     * @param close       Indicates that the stream should be closed or not after reading
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream, boolean close) throws IOException {
        return toString(new InputStreamReader(inputStream), close);
    }

    /**
     * Reads the given input stream into a string using the given charset. Does not close the input stream.
     *
     * @param inputStream Source
     * @param charset     Charset
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream, String charset) throws IOException {
        return toString(inputStream, Charset.forName(charset), false);
    }

    /**
     * Reads the given input stream into a string using the given charset. Does not close the input stream.
     *
     * @param inputStream Source
     * @param charset     Charset
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream, Charset charset) throws IOException {
        return toString(new InputStreamReader(inputStream, charset));
    }

    /**
     * Reads the given input stream into a string using the given charset.
     *
     * @param inputStream Source
     * @param charset     Charset
     * @param close       Indicates that the stream should be closed or not after reading
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream, String charset, boolean close) throws IOException {
        return toString(inputStream, Charset.forName(charset), close);
    }

    /**
     * Reads the given input stream into a string using the given charset.
     *
     * @param inputStream Source
     * @param charset     Charset
     * @param close       Indicates that the stream should be closed or not after reading
     * @return String dump
     * @throws IOException
     */
    public static String toString(InputStream inputStream, Charset charset, boolean close) throws IOException {
        return toString(new InputStreamReader(inputStream, charset), close);
    }

    /**
     * Reads the given input stream into a byte array. Does not close the input stream.
     *
     * @param inputStream Source
     * @return byte[] array dump
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        return toByteArray(inputStream, false);
    }

    /**
     * Reads the given input stream into a byte array.
     *
     * @param inputStream Source
     * @param close       Indicates that the stream should be closed or not after reading
     * @return byte[] array dump
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream inputStream, boolean close) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(inputStream, out, close);
        return out.toByteArray();
    }

    /**
     * Reads the given reader into a string. Does not close the reader.
     *
     * @param reader Source
     * @return String dump
     * @throws IOException
     */
    public static String toString(Reader reader) throws IOException {
        return toString(reader, false);
    }

    /**
     * Reads the given reader into a string.
     *
     * @param reader Source
     * @param close  Indicates that the stream should be closed or not after reading
     * @return String dump
     * @throws IOException
     */
    public static String toString(Reader reader, boolean close) throws IOException {
        StringWriter out = new StringWriter();
        copy(reader, out, close);
        return out.toString();
    }

    /**
     * Copies the given input stream into the given output stream. Does not close the input stream.
     *
     * @param in  Source
     * @param out Destination
     * @throws IOException
     */
    public static void copy(InputStream in, OutputStream out) throws IOException {
        copy(in, out, false);
    }

    /**
     * Copies the given reader into the given writer. Does not close the input stream.
     *
     * @param in  Source
     * @param out Destination
     * @throws IOException
     */
    public static void copy(Reader in, Writer out) throws IOException {
        copy(in, out, false);
    }

    /**
     * Copies the given input stream into the given output stream.
     *
     * @param in    Source
     * @param out   Destination
     * @param close Indicates that the stream should be closed or not after reading
     * @throws IOException
     */
    public static void copy(InputStream in, OutputStream out, boolean close) throws IOException {
        try {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } finally {
            if (close) {
                in.close();
            }
        }
    }

    /**
     * Copies the given reader into the given writer.
     *
     * @param in    Source
     * @param out   Destination
     * @param close Indicates that the reader should be closed or not after reading
     * @throws IOException
     */
    public static void copy(Reader in, Writer out, boolean close) throws IOException {
        try {
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } finally {
            if (close) {
                in.close();
            }
        }
    }

    /**
     * Silently close the given input stream.
     *
     * @param inputStream Input stream to close
     */
    public static void close(InputStream inputStream) {
        close((Closeable) inputStream);
    }

    /**
     * Silently close the given reader.
     *
     * @param reader Reader to close
     */
    public static void close(Reader reader) {
        close((Closeable) reader);
    }

    public static void close(Closeable closeable) {
        if(closeable == null) return;
        try {
            closeable.close();
        } catch(IOException e) {
            //ignore
        }
    }
}

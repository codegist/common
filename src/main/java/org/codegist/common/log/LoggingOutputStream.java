/*
 * Copyright 2010 CodeGist.org
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

package org.codegist.common.log;

import org.codegist.common.log.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

public class LoggingOutputStream extends OutputStream {

        private final StringWriter sw = new StringWriter();
        private final OutputStream delegate;
        private final Logger logger;

        public LoggingOutputStream(OutputStream delegate, Logger logger) {
            this.delegate = delegate;
            this.logger = logger;
        }

        @Override
        public void write(int b) throws IOException {
            delegate.write(b);
            sw.append((char) b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            delegate.write(b);
            sw.append(new String(b));
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            delegate.write(b, off, len);
            sw.append(new String(b, off, len));
        }

        @Override
        public void flush() throws IOException {
            delegate.flush();
        }

        @Override
        public void close() throws IOException {
            delegate.close();
            logger.trace("Written data:");
            logger.trace("\n" + sw);
        }
    }
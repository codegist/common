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

package org.codegist.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public final class Files {
    private Files() {
        throw new IllegalStateException();
    }

    /**
     * Returns the file content as a byte array
     *
     * @param file Source file
     * @return file content
     * @throws IOException
     */
    public static byte[] toByteArray(File file) throws IOException {
        return IOs.toByteArray(new FileInputStream(file), true);
    }

    /**
     * Returns the file content as string
     *
     * @param file
     * @return file content
     * @throws IOException
     */
    public static String toString(File file) throws IOException {
        return IOs.toString(new FileReader(file), true);
    }

}

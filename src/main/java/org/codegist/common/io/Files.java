package org.codegist.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public final class Files {
    private Files() {
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

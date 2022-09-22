package com.knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class FilesImplTest {
    FilesImpl files = new FilesImpl();

    @SneakyThrows
    @Test
    void contentEquals() {
        File file1 = new File("src/main/resources/file1");
        File file2 = new File("src/main/resources/file2");
        File file3 = new File("src/main/resources/esp.txt");

        File badFile1 = new File("file1");
        File badFile2 = new File("file2");

        assertTrue(files.contentEquals(file1, file2));
        assertTrue(files.contentEquals(file1, file1));
        assertFalse(files.contentEquals(file1, file3));
        assertFalse(files.contentEquals(file2, file3));

        assertThrows(NullPointerException.class, () -> files.contentEquals(null, null));
        assertThrows(NullPointerException.class, () -> files.contentEquals(file1, null));
        assertThrows(IllegalArgumentException.class, () -> files.contentEquals(badFile1, badFile2));
    }

    @SneakyThrows
    @Test
    void copyDirectoryToDirectory() {
    }

    @SneakyThrows
    @Test
    void testToString() {
        URL url = new URL("https://www.google.com");
        Charset charset = Charset.defaultCharset();

        assertThrows(NullPointerException.class, () -> files.toString((URL) null, charset));
        assertThrows(NullPointerException.class, () -> files.toString(url, null));
        assertThrows(MalformedURLException.class, () -> files.toString(new URL(""), charset));

        assertNotNull(files.toString(url, charset));
    }

    @SneakyThrows
    @Test
    void testToString1() {
        InputStream test = new FileInputStream("src/main/resources/esp.txt");
        InputStream is = new FileInputStream("src/main/resources/esp.txt");

        Charset charset = Charset.defaultCharset();

        assertThrows(NullPointerException.class, () -> files.toString((URL) null, null));
        assertThrows(FileNotFoundException.class, () -> files.toString(new FileInputStream(""), null));

        assertEquals(IOUtils.toString(test, charset), files.toString(is, charset));
        assertEquals(IOUtils.toString(test, charset), files.toString(is, null));
    }

    @SneakyThrows
    @Test
    void toByteArray() {
        URL url = new URL("https://www.google.com");

        assertThrows(NullPointerException.class, () -> files.toByteArray(null));
        assertNotNull(files.toByteArray(url));
    }

    @SneakyThrows
    @Test
    void normalize() {


    }

    @SneakyThrows
    @Test
    void readLines() {
    }

    @SneakyThrows
    @Test
    void isEmptyDirectory() {
    }

}
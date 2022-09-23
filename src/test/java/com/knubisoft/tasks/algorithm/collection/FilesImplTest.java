package com.knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.NotDirectoryException;
import java.util.List;

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
        File sourceDir = new File("src/main/java/com/knubisoft/tasks/algorithm/collection/empty");
        File destinationDir = new File("src/main/java/com/knubisoft/tasks/algorithm/testDirectory");
        File wrongFile = new File("");

        files.copyDirectoryToDirectory(sourceDir, destinationDir);

        boolean flag = FileUtils.getFile(destinationDir).isDirectory();

        assertThrows(NullPointerException.class, () -> files.copyDirectoryToDirectory(null, null));
        assertThrows(FileNotFoundException.class, () -> files.copyDirectoryToDirectory(sourceDir, wrongFile));

        assertTrue(flag);
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
        String file = "src///main////resources///esp.txt";
        String file2 = "src///main////resources///..//..//esp.txt";

        assertEquals("src\\main\\resources\\esp.txt", files.normalize(file));
        assertEquals("src\\esp.txt", files.normalize(file2));
        assertNull(files.normalize(null));

    }

    @SneakyThrows
    @Test
    void readLines() {
        File file = new File("src/main/resources/esp.txt");
        File wrongFile = new File("src");
        List<String> list = FileUtils.readLines(file, Charset.defaultCharset());

        assertThrows(NullPointerException.class, () -> files.readLines(null, StandardCharsets.UTF_8));
        assertThrows(FileNotFoundException.class, () -> files.readLines(wrongFile, Charset.defaultCharset()));

        assertEquals(list.get(0), files.readLines(file, Charset.defaultCharset()).get(0));
        assertEquals(list.get(3), files.readLines(file, Charset.defaultCharset()).get(3));
    }

    @SneakyThrows
    @Test
    void isEmptyDirectory() {
        File file = new File("src/main/java/com/knubisoft/tasks/algorithm/collection");
        File file2 = new File("Wrong File");
        File emptyDirectory = new File("src/main/java/com/knubisoft/tasks/algorithm/collection/empty");

        assertThrows(NullPointerException.class, () -> files.isEmptyDirectory(null));
        assertThrows(NotDirectoryException.class, () -> files.isEmptyDirectory(file2));

        assertFalse(files.isEmptyDirectory(file));
        assertTrue(files.isEmptyDirectory(emptyDirectory));

    }

}
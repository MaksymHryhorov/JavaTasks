package com.knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.PathUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesImpl implements Files {
    @Override
    public boolean contentEquals(File file1, File file2) throws IOException {
        if (file1 == null || file2 == null) {
            throw new NullPointerException();
        }

        if (!file1.isFile() || !file2.isFile()) {
            throw new IllegalArgumentException();
        }

        return PathUtils.fileContentEquals(file1.toPath(), file2.toPath());
    }

    @Override
    public void copyDirectoryToDirectory(File sourceDir, File destinationDir) throws IOException {
        if (sourceDir == null || destinationDir == null) {
            throw new NullPointerException();
        }

        if (!destinationDir.isDirectory() || !sourceDir.isDirectory()) {
            throw new FileNotFoundException();
        }

        FileUtils.copyDirectoryToDirectory(sourceDir,destinationDir);
    }

    @Override
    public String toString(URL url, Charset encoding) throws IOException {
        if (url == null || encoding == null) {
            throw new NullPointerException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), encoding));
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString(InputStream input, Charset charset) throws IOException {
        if (input == null) {
            throw new NullPointerException();
        }

        if (charset == null) {
            charset = Charset.defaultCharset();
        }

        return IOUtils.toString(input, charset);
    }

    @SneakyThrows
    @Override
    public byte[] toByteArray(URL url) throws IOException {
        if (url == null) {
            throw new NullPointerException();
        }

        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        return bufferedReader.readLine().getBytes();
    }

    @Override
    public String normalize(String fileName) {
        if (fileName == null) {
            return null;
        }

        Path path = Paths.get(fileName);
        Path normalizedPath  = path.normalize();

        return normalizedPath.toString();
    }

    @Override
    public List<String> readLines(File file, Charset charset) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (charset == null) {
            charset = Charset.defaultCharset();
        }

        // First realization
        List<String> stringList = new ArrayList<>();

        /*Scanner scanner = new Scanner(file);

        while(scanner.hasNext()) {
            stringList.add(scanner.next());
        }

        return stringList;*/

        // Second realization
        stringList = FileUtils.readLines(file, charset);

        return stringList;
    }

    @Override
    public boolean isEmptyDirectory(File directory) throws IOException {
        if (directory == null) {
            throw new NullPointerException();
        }

        if (!directory.isDirectory()) {
            throw new NotDirectoryException(directory.toString());
        }

        return PathUtils.isEmptyDirectory(directory.toPath());
    }
}

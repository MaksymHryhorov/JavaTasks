package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LsTree extends Command {
    public LsTree(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please write correct format: lstree \"depth\"";
        }

        File currentPath = context.getCurrentDirectory();
        int depth = Integer.parseInt(args.get(0));
        listDir(currentPath.toPath(), depth);

        return "\r\n";
    }

    public void listDir(Path path, int depth) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

        if (attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            System.out.println(spacesForDepth(depth) + " > " + path.getFileName());

            for (Path tempPath : paths) {
                listDir(tempPath, depth + 1);
            }

        } else {
            System.out.println(spacesForDepth(depth) + " -- " + path.getFileName());
        }
    }

    public String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            builder.append("    ");
        }

        return builder.toString();
    }
}

package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class CatWrite extends Command {

    public CatWrite(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "You need to write: \"catwrite \"file.name\" \"message\"\"";
        }

        if (args.size() < 2) {
            return "Please write: catwrite \"file.name\" \"message\"";
        }

        File fileDirectory = context.getCurrentDirectory();
        StringBuilder textToAdd = appendMessageFromArguments(args);

        return writeToFile(fileDirectory, args, textToAdd);
    }

    private StringBuilder appendMessageFromArguments(List<String> args) {
        StringBuilder textToTheFile = new StringBuilder();

        for (int i = 1; i < args.size(); i++) {
            textToTheFile.append(args.get(i)).append(" ");
        }

        return textToTheFile;
    }

    @SneakyThrows
    private String writeToFile(File fileDirectory, List<String> args, StringBuilder textToAdd) {
        File[] files = fileDirectory.listFiles();
        String result = "";

        for (File file : Objects.requireNonNull(files)) {
            if (file.getName().equals(args.get(0))) {
                FileUtils.writeStringToFile(file, "\r\n" + textToAdd.toString().trim(),
                        StandardCharsets.UTF_8, true);
                result = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                return result;
            }
        }

        return "File: " + args.get(0) + " not found";
    }
}

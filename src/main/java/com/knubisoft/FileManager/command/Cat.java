package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Cat extends Command {
    public Cat(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "You need to use: cat \"sample.txt\"";
        }

        if (args.size() > 1) {
            return "Please write only one file";
        }

        File fileDirectory = context.getCurrentDirectory();

        return showFileInConsole(fileDirectory, args);
    }


    @SneakyThrows
    private String showFileInConsole(File fileDirectory, List<String> args) {
        File[] files = fileDirectory.listFiles();
        String result = "";

        for (File file : Objects.requireNonNull(files)) {
            if (file.getName().equals(args.get(0))) {
                result = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                return result;
            }
        }

        return "File: " + args + " not found";
    }
}

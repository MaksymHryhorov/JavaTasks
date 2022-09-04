package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class CatPosition extends Command {
    public CatPosition(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Use: cat \"sample.txt\" \"split\" \"text\"";
        }

        if (args.size() < 3) {
            return "Please write catposition \"sample.txt\" \"split\" \"text\"";
        }

        StringBuilder textToDefPlace = appendMessageFromArguments(args);
        File file = findFileFromArguments(args);

        if (file != null) {
            String result = readFile(file);
            writeToFile(result, args, textToDefPlace, file);

            return readFile(file);
        }

        return "File " + args.get(0) + " not found";
    }

    private StringBuilder appendMessageFromArguments(List<String> args) {
        StringBuilder textToDefPlace = new StringBuilder();

        for (int i = 2; i < args.size(); i++) {
            textToDefPlace.append(args.get(i)).append(" ");
        }

        return textToDefPlace;
    }

    @SneakyThrows
    private String readFile(File file) {

        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    private File findFileFromArguments(List<String> args) {
        File fileDirectory = context.getCurrentDirectory();
        File[] files = fileDirectory.listFiles();

        for (File file : Objects.requireNonNull(files)) {
            if (file.getName().equals(args.get(0))) {
                return file;
            }
        }

        return null;
    }

    @SneakyThrows
    private void writeToFile(String result, List<String> args, StringBuilder textToDefPlace, File file) {
        StringBuilder resultBuilder = new StringBuilder();
        String[] splitLine = result.split(args.get(1));

        String formatted = splitLine[0] + textToDefPlace;

        resultBuilder.append(formatted);
        for (int i = 1; i < splitLine.length; i++) {
            resultBuilder.append(splitLine[i]);
        }

        FileUtils.writeStringToFile(file, String.valueOf(resultBuilder), StandardCharsets.UTF_8, false);

    }
}

package com.knubisoft.FileManager.command;

import java.io.File;
import java.util.Formatter;
import java.util.List;

public class Ls extends Command {

    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();

        StringBuilder result = new StringBuilder();
        buildHeaderTable(result);

        if (allFiles != null) {
            for (File each : allFiles) {
                result.append(String.format("| %-13s | %-13d |%n", each.getName(), each.getUsableSpace()));
            }
        }
        buildFooterTable(result);

        return result.toString();
    }

    private void buildHeaderTable(StringBuilder result) {
        result.append(String.format("+-------------------------------+%n"));
        result.append(String.format("| File name     | Usable Space  |%n"));
        result.append(String.format("+-------------------------------+%n"));

    }

    private void buildFooterTable(StringBuilder result) {
        result.append(String.format("+-------------------------------+%n"));
    }
}

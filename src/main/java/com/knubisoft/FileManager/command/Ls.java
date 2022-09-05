package com.knubisoft.FileManager.command;

import org.apache.commons.io.FilenameUtils;

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
            buildMainTable(result, allFiles);
        }

        buildFooterTable(result);

        return result.toString();
    }

    private void buildHeaderTable(StringBuilder result) {
        result.append(String.format("+--------------------------------------------------------------+%n"));
        result.append(String.format("| File name     | Usable Space   | Read   | Write  | Extension |%n"));
        result.append(String.format("+--------------------------------------------------------------+%n"));

    }

    private void buildMainTable(StringBuilder result, File[] allFiles) {
        String format = "| %-13s | %-13d  | %-5s  | %-5s | %-10s |%n";


        for (File each : allFiles) {
            String read = canRead(each);
            String write = canWrite(each);

            result.append(String.format(format, each.getName(), each.getUsableSpace(),
                   read, write, FilenameUtils.getExtension(each.getName())));
        }
    }

    private String canWrite(File each) {
        if (each.canWrite()) {
            return "write";
        }
        return "- ";
    }

    private String canRead(File each) {
        if (each.canRead()) {
            return "read ";
        }

        return "- ";
    }


    private void buildFooterTable(StringBuilder result) {
        result.append(String.format("+--------------------------------------------------------------+%n"));
    }
}

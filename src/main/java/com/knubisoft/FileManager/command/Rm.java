package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rm extends Command {
    public Rm(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please write correctly command rm \"-r\" \"recursive\"";
        }

        if (args.size() < 2) {
            return "Please write correctly command rm \"-r\" \"recursive\"";
        }

        if (args.get(0).equals("-r") && args.get(1).equals("recursive")) {
            File file = context.getCurrentDirectory();
            FileUtils.deleteDirectory(file);

            return "Directory successfully deleted";
        }

        return "Please write correctly command rm \"-r\" -- \"recursive\"";
    }
}

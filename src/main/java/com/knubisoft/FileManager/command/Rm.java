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
        if (args.get(1).equals("-r")) {
            File file = context.getCurrentDirectory();
            FileUtils.deleteDirectory(file);

            return "Directory successfully deleted";
        }

        return "Please write correctly command rm \"-r\" -- \"recursive\"";
    }
}

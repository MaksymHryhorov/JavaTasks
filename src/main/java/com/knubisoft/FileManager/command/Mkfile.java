package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Mkfile extends Command{
    public Mkfile(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "You need to write: mkfile \"FileName\"";
        }

        if (args.size() > 2) {
            return "You need to write correct form: mkfile \"FileName\"";
        }

        File currentDirectory = context.getCurrentDirectory();
        File folder = new File(currentDirectory, args.get(0));

        if (!folder.exists()) {
            if (folder.createNewFile()) {
                return "Folder successfully added";
            }
        }

        return "Folder exists";
    }
}

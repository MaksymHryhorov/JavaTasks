package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Rmfile extends Command {
    public Rmfile(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please write correct command: rmdir \"FileName\"";
        }

        if (args.size() > 2) {
            return "Please write correct command: rmdir \"FileName\"";
        }

        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));

        if (file.exists()) {
            if (file.delete()) {
                return "File Successfully deleted";
            }
        }

        return "File not found";
    }
}

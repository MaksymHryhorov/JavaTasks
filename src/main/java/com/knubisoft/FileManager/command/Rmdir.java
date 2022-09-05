package com.knubisoft.FileManager.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Rmdir extends Command {
    public Rmdir(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please write correct command: rmdir \"DirectoryName\"";
        }

        if (args.size() > 2) {
            return "Please write correct command: rmdir \"DirectoryName\"";
        }

        //FileUtils.deleteDirectory(new File(args.get(0)));

        File currentDirectory = context.getCurrentDirectory();

        File directory = new File(currentDirectory, args.get(0));

        if (directory.exists()) {
            if (directory.delete()) {
                return "Directory Successfully deleted";
            }
        }

        return "Directory not found";
    }
}

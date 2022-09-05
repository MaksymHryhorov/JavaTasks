package com.knubisoft.FileManager.command;

import java.io.File;
import java.util.List;

public class Mkdir extends Command {
    public Mkdir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "You need to write: mkdir \"FolderName\"";
        }

        if (args.size() > 2) {
            return "You need to write correct form: mkdir \"FolderName\"";
        }

        File currentDirectory = context.getCurrentDirectory();
        File folder = new File(currentDirectory, args.get(0));

        if (!folder.exists()) {
            if (folder.mkdir()) {
                return "Folder successfully added";
            }
        }

        return "Folder exists";
    }
}

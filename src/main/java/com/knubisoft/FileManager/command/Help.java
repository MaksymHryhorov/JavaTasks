package com.knubisoft.FileManager.command;

import java.util.List;
import java.util.Map;

public class Help extends Command {

    public Help(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        Map<String, Command> commands = context.getCommandMap();

        StringBuilder result = new StringBuilder("Available commands:\r\n");
        if (commands != null) {
            for (String each: commands.keySet()){
                result.append(each).append("\r\n");
            }
        }
        return result.toString();
    }
}

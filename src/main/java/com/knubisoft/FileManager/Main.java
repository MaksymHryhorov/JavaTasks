package com.knubisoft.FileManager;


import com.knubisoft.FileManager.command.Command;
import com.knubisoft.FileManager.command.Context;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String location = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        Context context = new Context(null, new File(args[0]));
        Map<String, Command> commands = getCommands(context);
        context.setCommandMap(commands);

        System.out.println("Hi there! Press q or exit to quit");
        performCommands(context, commands);
    }

    private static void performCommands(Context context, Map<String, Command> commands) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (StringUtils.isBlank(line)) {
                continue;
            }

            List<String> allArguments = Arrays.asList(line.split(" "));

            String commandName = allArguments.get(0);
            if (commandName.equals("q") || commandName.equals("exit")) {
                System.out.println("Bye bye");
                break;
            }

            Command command = commands.getOrDefault(commandName, new Command(context) {

                @Override
                public String execute(List<String> args) {
                    return "Command " + line + " is unknown";
                }
            });
            System.out.println(command.execute(allArguments.subList(1, allArguments.size())));
        }
    }

    @SneakyThrows
    private static Map<String, Command> getCommands(Context context) {
        Reflections reflection = new Reflections("com.knubisoft.FileManager.command", Scanners.SubTypes);
        Set<Class<? extends Command>> allClasses = reflection.getSubTypesOf(Command.class);

        Map<String, Command> commandNameToFunction = new LinkedHashMap<>();
        for (Class<? extends Command> each : allClasses) {
            Command instance = each.getDeclaredConstructor(Context.class).newInstance(context);
            commandNameToFunction.put(each.getSimpleName().toLowerCase(), instance);
        }
        return commandNameToFunction;
    }

}
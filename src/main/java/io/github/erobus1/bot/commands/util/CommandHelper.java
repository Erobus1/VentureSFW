package io.github.erobus1.bot.commands.util;

import io.github.erobus1.bot.commands.CommandArgument;
import io.github.erobus1.bot.commands.CommandArguments;
import io.github.erobus1.bot.commands.SlashCommand;
import io.github.erobus1.bot.commands.Command;

public class CommandHelper {

    public static String parseUsage(SlashCommand command) {
        CommandArguments args = command.getArguments();
        String parsed = "";
        if (args.size() == 0) return parsed;
        for (CommandArgument arg: args.getArgs()) {
            if (arg.isOptional()) {
                parsed += "[" + arg.getName() + "]";
            } else {
                parsed += "<" + arg.getName() + ">";
            }
        }
        return parsed;
    }

    public static CommandInfo getInfo(SlashCommand command) {
        Command cmdInfo = getInfoAnnotation(command);
        String name = cmdInfo.name();
        String description = cmdInfo.description();
        String usage = parseUsage(command);
        String category = cmdInfo.category();
        Permission permission = cmdInfo.permission();
        boolean testOnly = cmdInfo.testOnly();

        return new CommandInfo(name, description, usage, category, permission, testOnly);
    }

    public static Command getInfoAnnotation(SlashCommand command) {
        return command.getClass().getAnnotation(Command.class);
    }
}
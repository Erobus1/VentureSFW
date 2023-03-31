package io.github.erobus1.bot.commands;

import io.github.erobus1.Venture;
import io.github.erobus1.bot.commands.util.CommandHelper;
import io.github.erobus1.bot.commands.util.CommandInfo;
import io.github.erobus1.bot.commands.util.Permission;
import io.github.erobus1.internal.Logger;
import io.github.erobus1.internal.Note;
import io.github.erobus1.internal.embeds.DefaultEmbeds;
import io.github.erobus1.internal.exceptions.FrameworkException;
import io.github.erobus1.internal.exceptions.MissingAnnotationException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {
    private final HashMap<String, SlashCommand> COMMANDS = new HashMap<>();
    private final List<String> CATEGORIES = new ArrayList<>();

    public CommandHandler() throws FrameworkException {
        for (SlashCommand cmd : Venture.getCommands()) {
            Class<? extends SlashCommand> cmdClass = cmd.getClass();
            if (!cmdClass.isAnnotationPresent(Command.class)) throw new MissingAnnotationException("Missing command annotation! At " + cmd.getClass().getName());
            Command cmdInfo = cmdClass.getAnnotation(Command.class);
            String name = cmdInfo.name().toLowerCase();
            COMMANDS.put(name, cmd);
            upsertCommand(cmd);
            if (!CATEGORIES.contains(cmdInfo.category())) CATEGORIES.add(cmdInfo.category().toLowerCase().substring(0, 1).toUpperCase() + cmdInfo.category().toLowerCase().substring(1));
            System.out.println(Logger.GREEN(
                    "Registering \"" + name + "\" command. Class: " + cmdClass.getName()
            ));
            if (cmdClass.isAnnotationPresent(Note.class)) {
                System.out.println(Logger.CYAN(
                        " --> " + cmdClass.getAnnotation(Note.class).content()
                ));
            }
        }
    }

    private void upsertCommand(SlashCommand cmd) throws FrameworkException {
        Guild testGuild = Venture.getJda().getGuildById(Venture.getTestGuildId());
        CommandInfo info = CommandHelper.getInfo(cmd);
        if (testGuild == null) throw new FrameworkException("Invalid Test Guild Id. Please provide a correct one.");
        CommandDataImpl data = new CommandDataImpl(info.getName(), info.getDescription());
        for (CommandArgument arg : cmd.getArguments().getArgs()) {
            data.addOption(arg.getType(), arg.getName(), arg.getDescription(), arg.isOptional());
        }
        if (info.isTestOnly()) {
            testGuild.upsertCommand(data).queue();
        } else {
            Venture.getJda().upsertCommand(data).queue();
        }
    }

    public void runCommand(CommandEvent event) {
        SlashCommand command = COMMANDS.get(event.getName().toLowerCase());
        if (command == null) return;
        Command commandInfo = CommandHelper.getInfoAnnotation(command);
        event.setCommand(command);
        event.setArguments(command.getArguments());

        EmbedBuilder errorEmbed = DefaultEmbeds.ERROR();


        if (commandInfo.permission() != Permission.NONE) {
            for (int i = 0; i < Permission.values().length; i++) {
                if (!Permission.values()[i].getCallback().apply(event.getMember()) && commandInfo.permission().ordinal() >= i) {
                    errorEmbed.setTitle("Insufficient permission!")
                            .setDescription("This command requires at least the `" + commandInfo.permission().getName() + "` permission to be executed!");
                    event.reply(errorEmbed);
                    return;
                }
            }
        }

        command.execute(event);
    }

    public List<String> getCategories() {
        return CATEGORIES;
    }

    public HashMap<String, SlashCommand> getCommands() {
        return COMMANDS;
    }

    public SlashCommand getCommand(String commandName) {
        return COMMANDS.get(commandName);
    }
}
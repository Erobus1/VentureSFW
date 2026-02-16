package io.github.erobus1.bot.commands.impl;

import io.github.erobus1.Venture;
import io.github.erobus1.bot.commands.Command;
import io.github.erobus1.bot.commands.CommandArguments;
import io.github.erobus1.bot.commands.CommandEvent;
import io.github.erobus1.bot.commands.SlashCommand;
import io.github.erobus1.bot.commands.util.CommandHelper;
import io.github.erobus1.bot.commands.util.CommandInfo;
import io.github.erobus1.internal.Note;
import io.github.erobus1.internal.embeds.DefaultEmbeds;
import net.dv8tion.jda.api.EmbedBuilder;


@Note(content = "Implemented help command created in the framework. If you wish to disable the command, call Venturebuilder#noHelp before building the client!")
@Command(
        name = "help",
        description = "Displays useful information about a command or category"
)
public class HelpCommand extends SlashCommand {
    @Override
    public CommandArguments getArguments() {
        return new CommandArguments();
    }

    @Override
    public void execute(CommandEvent event) {
        EmbedBuilder embed = DefaultEmbeds.DEFAULT();
        embed.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());
        embed.setTitle("Help");
        if (event.hasNoArgs()) {
            embed.setDescription(Venture.getBotInfo());
            embed.addField("Please provide either a command or a category. Available categories:", "`" + String.join("`, `", Venture.getCmdHandler().getCategories().toArray(new String[0])) + "`", false);
            event.reply(embed);
            return;
        }

        String argument = event.getArgument(0).getName().toLowerCase();
        argument = argument.substring(0, 1).toUpperCase() + argument.substring(1);

        if (Venture.getCmdHandler().getCategories().contains(argument)) {
            SlashCommand[] commands = Venture.getCommands();
            embed.setDescription("__Here are all available commands in the `" + argument + "` category:__");
            embed.addBlankField(false);
            for (SlashCommand command : commands) {
                Command cmdInfo = CommandHelper.getInfoAnnotation(command);
                embed.addField("- " + cmdInfo.name(), cmdInfo.description(), false);
            }
            event.reply(embed);
            return;
        }

        SlashCommand command = Venture.getCmdHandler().getCommand(argument.toLowerCase());
        if (command != null) {
            CommandInfo cmdInfo = CommandHelper.getInfo(command);
            embed.setDescription("**" + argument + " command help**\n\n" + cmdInfo.getDescription());

            if (!cmdInfo.getUsage().isEmpty()) {
                embed.addField("Usage", cmdInfo.getUsage(), false);
            }
            embed.addField("Category", cmdInfo.getCategory(), false);
            embed.addField("Permission required", cmdInfo.getPermission().getName(), false);
            event.reply(embed);
            return;
        }


        event.reply(DefaultEmbeds.ERROR().setTitle("Not found").setDescription("No such command or category exists!"));
    }
}
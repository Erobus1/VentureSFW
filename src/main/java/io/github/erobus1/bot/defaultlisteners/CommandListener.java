package io.github.erobus1.bot.defaultlisteners;

import io.github.erobus1.Venture;
import io.github.erobus1.bot.commands.CommandEvent;
import io.github.erobus1.bot.commands.CommandHandler;
import io.github.erobus1.bot.commands.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        CommandHandler handler = Venture.getCmdHandler();
        SlashCommand command = handler.getCommand(event.getName());
        handler.runCommand(new CommandEvent(event));
    }
}
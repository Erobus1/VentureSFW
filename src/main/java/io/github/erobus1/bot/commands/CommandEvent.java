package io.github.erobus1.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.List;

public class CommandEvent extends SlashCommandInteractionEvent {

    private SlashCommand command;
    private CommandArguments arguments;

    public CommandEvent(SlashCommandInteractionEvent originalEvent) {
        super(originalEvent.getJDA(), 0, originalEvent.getInteraction());
    }

    public CommandArguments getArguments() {
        return arguments;
    }

    public CommandArgument getArgument(int index) {
        return arguments.getArgs().get(index);
    }

    public SlashCommand getCommand() {
        return command;
    }

    public void setArguments(CommandArguments arguments) {
        this.arguments = arguments;
    }

    public void setCommand(SlashCommand command) {
        this.command = command;
    }

    public void replyString(String message) {
        reply(message).queue();
    }

    public void reply(EmbedBuilder embed) {
        replyEmbeds(embed.build()).queue();
    }

    public void reply(MessageEmbed embed) {
        replyEmbeds(embed).queue();
    }

    public boolean hasNoArgs() {
        return getArguments().size() == 0;
    }
}
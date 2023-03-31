package io.github.erobus1.bot.commands;

public abstract class SlashCommand {

    public abstract CommandArguments getArguments();
    public abstract void execute(CommandEvent event);
}

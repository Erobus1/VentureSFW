package io.github.erobus1;

import com.sun.tools.javac.Main;
import io.github.erobus1.bot.commands.CommandHandler;
import io.github.erobus1.bot.commands.SlashCommand;
import io.github.erobus1.internal.exceptions.FrameworkException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.List;

public class Venture {
    private static String TOKEN;
    private static List<String> DEVELOPERS;
    private static String BOT_INFO;
    private static ListenerAdapter[] LISTENERS;
    private static SlashCommand[] COMMANDS;
    private static JDA jda;
    private static CommandHandler HANDLER;
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static String TESTGUILDID;

    public static void create(String token, String testGuildId, List<String> developers, ListenerAdapter[] listeners, SlashCommand[] commands, String botInfo) {
        TOKEN = token;
        TESTGUILDID = testGuildId;
        DEVELOPERS = developers;
        LISTENERS = listeners;
        COMMANDS = commands;
        BOT_INFO = botInfo;
    }

    public static void initialize() throws FrameworkException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        Object[] listeners = LISTENERS;
        builder.addEventListeners(listeners);
        jda = builder.build();
        jda.awaitReady();
        HANDLER = new CommandHandler();
    }

    public static String getTestGuildId() {
        return TESTGUILDID;
    }

    public static List<String> getDevelopers() {
        return DEVELOPERS;
    }

    public static JDA getJda() {
        return jda;
    }

    public static SlashCommand[] getCommands() {
        return COMMANDS;
    }

    public static CommandHandler getCmdHandler() {
        return HANDLER;
    }

    public static Logger getLogger() {
        return log;
    }

    /**
     * Use this method to get basic information about the bot.
     *
     * @return The bot information, given when building the client.
     * @see VentureBuilder#setBotInfo(String)
     */
    public static String getBotInfo() {
        return BOT_INFO;
    }
}
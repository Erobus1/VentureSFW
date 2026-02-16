package io.github.erobus1;

import io.github.erobus1.bot.commands.SlashCommand;
import io.github.erobus1.bot.commands.impl.HelpCommand;
import io.github.erobus1.bot.defaultlisteners.CommandListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.erobus1.Venture.getLogger;

/**
 * Helper class to build the Venture class.
 */
public class VentureBuilder {
    private final List<String> DEVELOPERS;
    private final String TESTGUILDID;
    private final String TOKEN;
    private String BOT_INFO = "This is a bot created with the Venture framework";
    private final List<ListenerAdapter> EVENT_LISTENERS = new ArrayList<>();
    private final List<SlashCommand> COMMANDS = new ArrayList<>();
    private static boolean helpCommand = true;

    private VentureBuilder(String token, String testGuildId, String... developers) {
        TOKEN = token;
        TESTGUILDID = testGuildId;
        DEVELOPERS = developers.length == 1 && developers[0].isEmpty() ? new ArrayList<>() : Arrays.asList(developers);
        EVENT_LISTENERS.add(new CommandListener());
    }
    public static VentureBuilder createDefault(String token, String testGuildId, String... developers) {
        return new VentureBuilder(token, testGuildId, developers);
    }
    public static VentureBuilder createDefault(String token, String testGuildId ) {
        return new VentureBuilder(token, testGuildId, "");
    }

    public VentureBuilder addDevelopers(String... developers) {
        DEVELOPERS.addAll(Arrays.asList(developers));
        return this;
    }

    public VentureBuilder addListeners(ListenerAdapter... listeners) {
        EVENT_LISTENERS.addAll(Arrays.asList(listeners));
        return this;
    }

    public VentureBuilder addCommands(SlashCommand... commands) {
        COMMANDS.addAll(Arrays.asList(commands));
        return this;
    }

    /**
     * Use this to disable the default {@link HelpCommand} command
     * @see HelpCommand
     */
    public VentureBuilder noHelp() {
        helpCommand = false;
        return this;
    }

    /**
     *
     * @param info The message that will be displayed by using the default help command or can be retrieved by using {@link Venture#getBotInfo}
     */
    public VentureBuilder setBotInfo(String info) {
        BOT_INFO = info;
        return this;
    }

    public void build() {

        if (COMMANDS.isEmpty()) {
            getLogger().info(
                    "No commands initialized."
            );
        }

        if (helpCommand) addCommands(new HelpCommand());

        if (DEVELOPERS.isEmpty()) {
            getLogger().warn(
                    "No developer ids have been added to the bot."
            );
        }

        if (EVENT_LISTENERS.isEmpty()) {
            getLogger().info(
                    "No additional listeners were added!"
            );
        }

        Venture.create(TOKEN, TESTGUILDID, DEVELOPERS, EVENT_LISTENERS.toArray(new ListenerAdapter[0]), COMMANDS.toArray(new SlashCommand[0]), BOT_INFO);
    }
}
package io.github.erobus1.bot.commands.util;

public class CommandInfo {
    private final String name;
    private final String description;
    private final String usage;
    private final String category;
    private final Permission permission;
    private final boolean testOnly;


    public CommandInfo(String name, String description, String usage, String category, Permission permission, boolean testOnly) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.category = category;
        this.permission = permission;
        this.testOnly = testOnly;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public String getCategory() {
        return category;
    }

    public Permission getPermission() {
        return permission;
    }

    public boolean isTestOnly() {
        return testOnly;
    }
}
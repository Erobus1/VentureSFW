# VentureFW

Not much documentations and stuff but to install put in your build.gradle:

```java
repositories {

  maven { url 'https://jitpack.io' }
      
}
```

```java
dependencies {
  implementation 'com.github.Erobus1:VentureSFW:9c07cf8a9a'
}
```

**Don't forget to implement the JDA framework!!**

## To build your client do:

```java
VentureBuilder builder = VentureBuilder.createDefault(token, testGuildId);

/*
* You can manage client options for example
* builder.addListeners(jda listeners...); - Add more listeners to the bot
* builder.addCommands(SlashCommands...); - Add commands to the bot, example command below
* builder.setDevelopers(developer ids...); - n amount of developer ids to use for the Permission system
* builder.setBotInfo(text); - A short information text what you bot is about. Text is used in the default help command and can be retrieved at any time with Venture#getBotInfo
* builder.noHelp(); - Disable the default help command so you can use your own
*/

builder.build();

Venture.initialize();

```


## Example command class

```java
@Command(
  name = "ping",
  description = "Default ping command"
  
  /* Optional attributes
  ,
  category = "general",
  aliases = { "pong", "pingpong" },
  permission = Permission.GUILD_OWNER,
  testOnly = false
  */
)
public class PingCommand extends SlashCommand {

  @Override
  public CommandArguments getArguments() {
    return new CommandArguments();
  }

  @Override
  public void execute(CommandEvent event) {
    event.reply("Pong! " + event.getJDA().getRestPing().complete() + "ms");
    /*
    You can get all arguments by using event#getArguments() or a single argument at a specific index by using event#getArgument(int index) -> String
    */
  }
}
```

## Getting command attributes

To get the attributes of a command simply use the class CommandHelper
```java
CommandInfo info = CommandHelper.getInfo(SlashCommand command);
System.out.println(info.getName());
System.out.println(info.getCategory());
//...
```

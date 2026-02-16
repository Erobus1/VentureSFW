# VentureFW

VentureFW ist ein schlankes **Framework für Discord-Bots mit JDA 6**.
Es bietet ein einfaches Command-System (Slash Commands), Default-Listener und einen Builder für den schnellen Start.

## Installation über JitPack

1. Stelle sicher, dass dein Projekt JitPack als Repository eingebunden hat.
2. Nutze danach dieses Repository als Dependency.

### Gradle (Groovy)

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Erobus1:VentureSFW:<tag>'
}
```

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Erobus1:VentureSFW:<tag>")
}
```

> Ersetze `<tag>` durch ein Release-Tag aus diesem Repository (z. B. `v1.2.0`).

## Quickstart

```java
VentureBuilder builder = VentureBuilder.createDefault(token, testGuildId);

builder
    .addCommands(new PingCommand())
    .setBotInfo("Mein Bot auf VentureFW")
    .build();

Venture.initialize();
```

## Beispiel-Command

```java
@Command(
    name = "ping",
    description = "Default ping command"
)
public class PingCommand extends SlashCommand {

    @Override
    public CommandArguments getArguments() {
        return new CommandArguments();
    }

    @Override
    public void execute(CommandEvent event) {
        event.reply("Pong! " + event.getJDA().getRestPing().complete() + "ms");
    }
}
```

## Hinweise

- VentureFW basiert auf **JDA 6.3.1**.
- Für die Auslieferung über JitPack ist keine zusätzliche lokale Veröffentlichung notwendig.
- Der erste JitPack-Build für ein neues Tag kann kurz dauern.

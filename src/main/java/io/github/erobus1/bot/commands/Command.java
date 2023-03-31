package io.github.erobus1.bot.commands;

import io.github.erobus1.bot.commands.util.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Command {
    String name();
    String description();
    String category() default "general";
    Permission permission() default Permission.NONE;

    boolean testOnly() default true;
}
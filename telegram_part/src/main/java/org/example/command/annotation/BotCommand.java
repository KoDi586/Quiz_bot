package org.example.command.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для маркировки команд бота.
 * Автоматически регистрирует команду в CommandContainer.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface BotCommand {
    /**
     * Имя команды с префиксом "/"
     */
    String command();
    
    /**
     * Описание команды для помощи
     */
    String description() default "";
}
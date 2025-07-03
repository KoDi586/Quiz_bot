package org.example.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.command.annotation.BotCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Класс для автоматической регистрации команд бота.
 * Находит все бины с аннотацией @BotCommand и регистрирует их в CommandContainer.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CommandRegistrar {

    private final CommandContainer commandContainer;
    private final ApplicationContext applicationContext;

    /**
     * Метод, вызываемый после инициализации контекста Spring.
     * Находит все бины с аннотацией @BotCommand и регистрирует их в CommandContainer.
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Получаем все бины с аннотацией @BotCommand
        Map<String, Object> commands = applicationContext.getBeansWithAnnotation(BotCommand.class);
        
        // Регистрируем каждую команду в CommandContainer
        commands.forEach((beanName, bean) -> {
            if (bean instanceof Command) {
                BotCommand annotation = bean.getClass().getAnnotation(BotCommand.class);
                String commandName = annotation.command();
                
                // Регистрируем команду
                commandContainer.registerCommand(commandName, (Command) bean);
                
                log.info("Registered command: {} ({})", commandName, beanName);
            } else {
                log.warn("Bean {} has @BotCommand annotation but does not implement Command interface", beanName);
            }
        });
        
        log.info("Registered {} commands", commands.size());
    }
}
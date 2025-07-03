package org.example.command;

import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Контейнер для хранения и выполнения команд бота.
 * Команды регистрируются автоматически через CommandRegistrar.
 */
@Component
@Slf4j
public class CommandContainer {

    private final Map<String, Command> commandMap = new ConcurrentHashMap<>();

    /**
     * Регистрирует команду в контейнере.
     * Вызывается из CommandRegistrar.
     *
     * @param commandName имя команды (с префиксом "/")
     * @param command     экземпляр команды
     */
    public void registerCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    /**
     * Возвращает команду по имени.
     *
     * @param commandName имя команды
     * @return команда или null, если команда не найдена
     */
    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    /**
     * Выполняет команду по имени.
     *
     * @param commandName имя команды
     * @param update      объект обновления от Telegram
     */
    public void process(String commandName, Update update) {
        if (commandMap.isEmpty()) {
            log.warn("Command map is empty");
            return;
        }

        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute(update);
        } else {
            log.warn("Command not found: {}", commandName);
        }
    }

    /**
     * Возвращает все зарегистрированные команды.
     *
     * @return карта команд
     */
    public Map<String, Command> getCommands() {
        return Map.copyOf(commandMap);
    }
}

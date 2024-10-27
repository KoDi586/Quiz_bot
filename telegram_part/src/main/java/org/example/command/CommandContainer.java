package org.example.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    public CommandContainer(StartCommand startCommand,
                            DetailedCommand detailedCommand) {
        commandMap.put(StartCommand.commandName, startCommand);
        commandMap.put(DetailedCommand.commandName, detailedCommand);
    }


    public void process(String commandName, Update update) {
        if (!commandMap.isEmpty()) {
            commandMap.get(commandName).execute(update);
        }
    }
}

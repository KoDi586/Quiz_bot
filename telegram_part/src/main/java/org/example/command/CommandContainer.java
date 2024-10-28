package org.example.command;

import com.pengrad.telegrambot.model.Update;
import org.example.command.commandHeap.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    public CommandContainer(StartCommand startCommand,
                            DetailedCommand detailedCommand,
                            SecondButton secondButton,
                            ChooseQuiz chooseQuiz,
                            Help help,
                            Donat donat,
                            BackInMainMenu backInMainMenu) {


        commandMap.put(StartCommand.commandName, startCommand);
        commandMap.put(DetailedCommand.commandName, detailedCommand);
        commandMap.put(SecondButton.commandName, secondButton);
        commandMap.put(ChooseQuiz.commandName, chooseQuiz);
        commandMap.put(Help.commandName, help);
        commandMap.put(Donat.commandName, donat);
        commandMap.put(BackInMainMenu.commandName, backInMainMenu);
    }


    public void process(String commandName, Update update) {
        if (!commandMap.isEmpty()) {
            if (commandMap.containsKey(commandName)) {
                commandMap.get(commandName).execute(update);
            }
        }
    }
}

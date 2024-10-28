package org.example.command.commandHeap;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.QuizContainerMenu;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChooseQuiz implements Command {

    private final QuizContainerMenu menu;
    public static String commandName = "/chooseQuiz";

    @Override
    public void execute(Update update) {
        menu.sendMessage(update.callbackQuery().message().chat().id());
    }
}

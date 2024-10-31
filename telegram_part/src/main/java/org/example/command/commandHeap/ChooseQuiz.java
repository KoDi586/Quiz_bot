package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.QuizContainerMenu;
import org.example.sevrice.quizService.QuizCreatorService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChooseQuiz implements Command {

    private final QuizContainerMenu menu;
    private final TelegramBot telegramBot;
    private final QuizCreatorService quizService;
    public static String commandName = "/chooseQuiz";

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(
                update.callbackQuery().message().chat().id(),
                "there is themes:\n"  +  giveAllThemes()
        ));
        menu.sendMessage(update.callbackQuery().message().chat().id());
    }

    private String giveAllThemes() {
        StringBuilder stringBuilder = new StringBuilder();
        int item = 0;
        for (String theme : quizService.getAllThemes()) {
            stringBuilder.append("theme \"").append(theme).append("\" is number ").append(item).append("\n");
            item++;
        }
        return stringBuilder.toString();
    }
}

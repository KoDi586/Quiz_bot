package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.MainMenu;
import org.springframework.stereotype.Component;

@Component("/start")
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final TelegramBot telegramBot;
    private final MainMenu menu;
    public static String commandName = "/start";
    private static String HELLO = ". My creater is glad for you. U can use me for training of others theme." +
            " U can do it from playing in our quiz!";

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(
                update.message().chat().id(),
                "Hello, " + update.message().chat().username() + HELLO
        ));

    menu.sendMessage(update.message().chat().id());
    }
}

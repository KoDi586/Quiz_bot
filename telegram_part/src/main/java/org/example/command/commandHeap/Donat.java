package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Donat implements Command {
    public static String commandName = "/donat";
    private final TelegramBot telegramBot;

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(
                update.callbackQuery().message().chat().id(),
                "for donat click on this link:\n" +
                        "https://easydmarc.com/tools/phishing-url"
        ));
    }
}

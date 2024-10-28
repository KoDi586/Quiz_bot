package org.example.command.commandHeap;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.example.command.Command;
import org.example.listener.menus.MainMenu;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BackInMainMenu implements Command {
    public static String commandName = "/backInMainMenu";
    private final MainMenu menu;

    @Override
    public void execute(Update update) {
        menu.sendMessage(update.callbackQuery().message().chat().id());
    }
}

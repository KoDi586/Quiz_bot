package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.example.command.MessageCommand;
import org.example.command.annotation.BotCommand;
import org.example.listener.menus.MainMenu;

/**
 * Пример команды, обрабатывающей текстовые сообщения.
 * Использует аннотацию @BotCommand для автоматической регистрации.
 * Наследуется от MessageCommand для упрощения обработки текстовых сообщений.
 */
@BotCommand(command = "/start")
//@RequiredArgsConstructor
public class StartCommandExample extends MessageCommand {

    private final MainMenu menu;
    private static final String HELLO = ". My creater is glad for you. U can use me for training of others theme." +
            " U can do it from playing in our quiz!";

    /**
     * Конструктор с указанием TelegramBot.
     * 
     * @param telegramBot экземпляр TelegramBot
     * @param menu экземпляр MainMenu
     */
    public StartCommandExample(TelegramBot telegramBot, MainMenu menu) {
        super(telegramBot);
        this.menu = menu;
    }

    /**
     * Обработка текстового сообщения.
     * Этот метод вызывается только если update содержит текстовое сообщение.
     * 
     * @param update объект обновления от Telegram
     */
    @Override
    protected void handleMessage(Update update) {
        // Получаем ID чата и имя пользователя
        long chatId = getChatId(update);
        String username = update.message().chat().username();
        
        // Отправляем приветственное сообщение
        sendTextMessage(chatId, "Hello, " + username + HELLO);
        
        // Отображаем главное меню
        menu.sendMessage(chatId);
    }
}
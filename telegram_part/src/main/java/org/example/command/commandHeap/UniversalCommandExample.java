package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.example.command.AbstractCommand;
import org.example.command.annotation.BotCommand;
import org.example.listener.menus.MainMenu;

/**
 * Пример универсальной команды, которая может обрабатывать как текстовые сообщения, так и callback-запросы.
 * Использует аннотацию @BotCommand для автоматической регистрации.
 * Наследуется от AbstractCommand для возможности обработки обоих типов обновлений.
 */
@BotCommand(command = "/universal")
public class UniversalCommandExample extends AbstractCommand {

    private final MainMenu menu;

    /**
     * Конструктор с указанием TelegramBot и MainMenu.
     * 
     * @param telegramBot экземпляр TelegramBot
     * @param menu экземпляр MainMenu
     */
    public UniversalCommandExample(TelegramBot telegramBot, MainMenu menu) {
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
        long chatId = update.message().chat().id();
        String username = update.message().chat().username();
        
        sendTextMessage(chatId, "Привет, " + username + "! Вы активировали универсальную команду через текстовое сообщение.");
        menu.sendMessage(chatId);
    }

    /**
     * Обработка callback-запроса.
     * Этот метод вызывается только если update содержит callback-запрос.
     * 
     * @param update объект обновления от Telegram
     */
    @Override
    protected void handleCallbackQuery(Update update) {
        long chatId = update.callbackQuery().message().chat().id();
        
        sendTextMessage(chatId, "Вы активировали универсальную команду через callback-запрос.");
        menu.sendMessage(chatId);
    }
    
//    /**
//     * Вспомогательный метод для отправки текстового сообщения.
//     *
//     * @param chatId ID чата
//     * @param text текст сообщения
//     */
//    private void sendTextMessage(long chatId, String text) {
//
//        sendTextMessage(chatId);
//
//        getTelegramBot().execute(new com.pengrad.telegrambot.request.SendMessage(chatId, text));
//    }
}
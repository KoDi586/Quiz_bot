package org.example.command.commandHeap;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.example.command.CallbackCommand;
import org.example.command.annotation.BotCommand;

/**
 * Пример команды, обрабатывающей callback-запросы.
 * Использует аннотацию @BotCommand для автоматической регистрации.
 * Наследуется от CallbackCommand для упрощения обработки callback-запросов.
 */
@BotCommand(command = "/help")
public class HelpCommandExample extends CallbackCommand {

    /**
     * Конструктор с указанием TelegramBot.
     * 
     * @param telegramBot экземпляр TelegramBot
     */
    public HelpCommandExample(TelegramBot telegramBot) {
        super(telegramBot);
    }

    /**
     * Обработка callback-запроса.
     * Этот метод вызывается только если update содержит callback-запрос.
     * 
     * @param update объект обновления от Telegram
     */
    @Override
    protected void handleCallbackQuery(Update update) {
        // Получаем ID чата
        long chatId = getChatId(update);
        
        // Отправляем сообщение с информацией о боте
        sendTextMessage(chatId, "Здесь основная информация по боту:\n" +
                "1. Используйте /start для начала работы\n" +
                "2. Выберите тему викторины в главном меню\n" +
                "3. Отвечайте на вопросы и получайте баллы");
    }
}
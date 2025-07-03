package org.example.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактный класс для всех команд бота.
 * Предоставляет базовую функциональность и определяет шаблонный метод для выполнения команд.
 */
@RequiredArgsConstructor
public abstract class AbstractCommand implements Command {
    
    protected final TelegramBot telegramBot;
    
    /**
     * Шаблонный метод, определяющий общий алгоритм выполнения команды
     */
    @Override
    public final void execute(Update update) {
        if (update.message() != null) {
            handleMessage(update);
        } else if (update.callbackQuery() != null) {
            handleCallbackQuery(update);
        }
    }

    /**
     * Отправляет текстовое сообщение пользователю
     */
    protected void sendTextMessage(long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
    
    /**
     * Обработка текстовых сообщений
     * По умолчанию не делает ничего, переопределяется в подклассах
     */
    protected void handleMessage(Update update) {
        // По умолчанию ничего не делает
    }
    
    /**
     * Обработка callback-запросов
     * По умолчанию не делает ничего, переопределяется в подклассах
     */
    protected void handleCallbackQuery(Update update) {
        // По умолчанию ничего не делает
    }
}
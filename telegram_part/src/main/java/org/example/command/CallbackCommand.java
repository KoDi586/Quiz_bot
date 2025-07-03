package org.example.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Базовый класс для команд, обрабатывающих только callback-запросы (нажатия на кнопки).
 * Предоставляет удобные методы для работы с callback-запросами.
 */
public abstract class CallbackCommand extends AbstractCommand {

    public CallbackCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    /**
     * Переопределяет метод handleMessage, чтобы он ничего не делал,
     * так как этот класс предназначен только для обработки callback-запросов.
     */
    @Override
    protected final void handleMessage(Update update) {
        // Этот класс не обрабатывает текстовые сообщения
    }

    /**
     * Получает идентификатор чата из обновления
     */
    protected long getChatId(Update update) {
        return update.callbackQuery().message().chat().id();
    }

    /**
     * Получает имя пользователя из обновления
     */
    protected String getUsername(Update update) {
        return update.callbackQuery().message().chat().username();
    }

    /**
     * Получает данные callback-запроса
     */
    protected String getCallbackData(Update update) {
        return update.callbackQuery().data();
    }
}
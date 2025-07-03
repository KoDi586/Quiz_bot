package org.example.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Базовый класс для команд, обрабатывающих только текстовые сообщения.
 * Предоставляет удобные методы для работы с сообщениями.
 */
public abstract class MessageCommand extends AbstractCommand {

    public MessageCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    /**
     * Переопределяет метод handleCallbackQuery, чтобы он ничего не делал,
     * так как этот класс предназначен только для обработки текстовых сообщений.
     */
    @Override
    protected final void handleCallbackQuery(Update update) {
        // Этот класс не обрабатывает callback-запросы
    }

    /**
     * Получает идентификатор чата из обновления
     */
    protected long getChatId(Update update) {
        return update.message().chat().id();
    }

    /**
     * Получает имя пользователя из обновления
     */
    protected String getUsername(Update update) {
        return update.message().chat().username();
    }

    /**
     * Получает текст сообщения из обновления
     */
    protected String getMessageText(Update update) {
        return update.message().text();
    }
}
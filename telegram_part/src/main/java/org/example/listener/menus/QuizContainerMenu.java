package org.example.listener.menus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizContainerMenu {

    private final TelegramBot telegramBot;

    public void sendMessage(long chatId) {
        InlineKeyboardButton button0 = new InlineKeyboardButton("тема 1").callbackData("/firstQuizClick");
        InlineKeyboardButton button2 = new InlineKeyboardButton("тема 2").callbackData("/null");
        InlineKeyboardButton button1 = new InlineKeyboardButton("еще темы").callbackData("/null");
        InlineKeyboardButton button3 = new InlineKeyboardButton("назад").callbackData("/backInMainMenu");



        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{
                {button0, button1},
                {button2, button3}}
        );

        telegramBot.execute(
                new SendMessage(chatId,
                        "Quiz topics:")
                        .replyMarkup(keyboard1));


    }

}

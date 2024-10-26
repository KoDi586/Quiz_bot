package org.example.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateListener implements UpdatesListener {

    private final TelegramBot telegramBot;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            String messageText = update.message().text();
            Long userChatId = update.message().chat().id();
            log.info(messageText, userChatId);
            SendMessage sendMessage = new SendMessage(userChatId, "who a you, warrior?");
            telegramBot.execute(sendMessage);

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

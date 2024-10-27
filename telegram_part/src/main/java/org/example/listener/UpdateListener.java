package org.example.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sevrice.listenerService.MainListenerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final MainListenerService listenerService;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info(update.message().text(), update.message().chat().id());

            if (update.message() != null) {
                if (update.message().text() != null) {
                    listenerService.workWithText(
                            update.message().text(),
                            update
                    );
                } else {
                    listenerService.dontUnderstand(
                            update.message().chat().id()
                    );
                }
            } else if (update.callbackQuery() != null) {
                listenerService.workWithCommand(
                        update.callbackQuery().message().chat().id(),
                        update.callbackQuery().data()
                );
            }


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

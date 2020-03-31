package com.sokolovskaya.travelbot;

import com.sokolovskaya.travelbot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String USERNAME;

    @Value("${bot.token}")
    private String TOKEN;

    private final BotService botService;

    @Override
    public void onUpdateReceived(Update update) {

        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        sendMsg(chatId, botService.getMessage(message));
    }

    private void sendMsg(String chatId, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(chatId);
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
package com.example.sispro.Bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    List<BotCommand> commands=List.of(
            new BotCommand("/start","say hello"),
            new BotCommand("/author","get author name"),
            new BotCommand("/analyze",""),
            new BotCommand("byte or",""),
            new BotCommand("div",""),
            new BotCommand("save file","")
    );
    public TelegramBot(BotConfig config) {
        super(config.getToken());
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(),null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            switch (update.getMessage().getText()){
                case"/start":{
                    message.setText("im started");
                    break;
                }
                default:{
                    message.setText("xz");
                    break;
                }
            }

            try {

                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return config.getName();
    }
}

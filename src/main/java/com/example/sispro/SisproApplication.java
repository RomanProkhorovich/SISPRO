package com.example.sispro;

import com.example.sispro.Bot.BotConfig;
import com.example.sispro.Bot.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class SisproApplication {

    public static void main(String[] args) {
        var conf=new BotConfig();
        TelegramBot bot=new TelegramBot(conf);
        try {
            initBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initBot(TelegramBot bot) throws TelegramApiException {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}

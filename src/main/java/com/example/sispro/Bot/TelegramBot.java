package com.example.sispro.Bot;

import com.example.sispro.Exceptions.TooMuchIterations;
import com.example.sispro.lib;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private State state= State.NONE;
    private final List<BotCommand> commands=List.of(
            new BotCommand("/start","say hello"),
            new BotCommand("/author","get author name"),
            new BotCommand("/analyze","get iter count"),
            new BotCommand("/byte_or","get byte or of 2 numbers"),
            new BotCommand("/div","get div"),
            new BotCommand("/save_csv_file","save csv"),
            new BotCommand("/save_txt_file","save txt")
    );
    public TelegramBot(BotConfig config) {
        super(config.getToken());
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(),null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        this.config=config;
    }

    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message;
            SendMessage mes = new SendMessage();
            mes.setChatId(update.getMessage().getChatId().toString());
            if (update.getMessage().getText().charAt(0) == '/') {

                message = executeCommand(update.getMessage().getText());
            } else
                message = executeNotCommand(update.getMessage().getText());
            mes.setText(message);
            try {
                execute(mes);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String executeNotCommand(String message) {
        switch (state){
            case ANALYZE:{
                try {
                    return String.valueOf(lib.analyze(message));
                }
                catch (TooMuchIterations e){
                    return e.getMessage();
                }
            }
        }
        return "";
    }

    private String executeCommand(String message) {
        switch (message){
            case"/start":{
                return "Hello";
            }
            case"/author":{
                return "Пома Сахорович, группа 6304";
            }
            case"/analyze":{
                state=State.ANALYZE;
                return "anal";
            }
            case"/byte_or":{
                return "byte or";
            }

            case"/div":{
                state=State.DIV;
                return "div";
            }
            case"/save_csv_file":{
                state=State.SAVE_CSV;
                return "save csv";
            }

            case"/save_txt_file":{
                state=State.SAVE_TXT;
                return "save txt";
            }
            default:{
                return "xz";
            }
        }
    }

    public void on1UpdateReceived(Update update) {
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

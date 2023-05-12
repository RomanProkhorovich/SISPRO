package com.example.sispro.Bot;
import com.example.Store.CSVController;
import com.example.Store.Model.CSVRecord;
import com.example.Store.Model.PlainTextRecord;
import com.example.Store.Repos.Repository;
import com.example.Store.TXTController;
import com.example.sispro.Exceptions.CantBeTwoOperandException;
import com.example.sispro.Exceptions.TooMuchIterations;
import com.example.sispro.lib;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
import java.util.List;
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final lib lib=new lib();
    private final List<BotCommand> commands = List.of(
            new BotCommand("/start", "say hello"),
            new BotCommand("/author", "get author name"),
            new BotCommand("/analyze", "get iter count"),
            new BotCommand("/byte_or", "get byte or of 2 numbers"),
            new BotCommand("/div", "get div"),
            new BotCommand("/save_csv_file", "save csv"),
            new BotCommand("/save_txt_file", "save txt")
    );
    private State state = State.NONE;

    public TelegramBot(BotConfig config) {
        super(config.getToken());
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
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
        switch (state) {
            case ANALYZE -> {
                try {
                    return "Количество итераций:" + String.valueOf(lib.analyze(message));
                } catch (TooMuchIterations e) {
                    return e.getMessage();
                }
            }
            case DIV -> {
                var a = message.split(" ");
                if (a.length != 2) {
                    throw new CantBeTwoOperandException();
                }
                return "Результат деления:" + String.valueOf(lib.div(Integer.parseInt(a[0]), Integer.parseInt(a[1])));

            }
            case BYTE_OR -> {
                var a = message.split(" ");
                if (a.length != 2) {
                    throw new CantBeTwoOperandException();
                }
                return "Результат логического или:"
                        + lib.byteOr(Integer.parseInt(a[0])
                        , Integer.parseInt(a[1]));

            }
            case SAVE_CSV -> {
                var a = message.split(";");
                CSVController controller=new CSVController(new Repository<>(CSVRecord.class));
                controller.save(new CSVRecord(a[0],Integer.parseInt(a[1]),a[2]));
                return "Сохранено!";
            }
            case SAVE_TXT -> {
                var a = message.split(";");
                var d=a[0].split(":");
                Date date=new Date(Integer.parseInt(d[0]),
                        Integer.parseInt(d[1]),
                        Integer.parseInt(d[2]));
                TXTController controller=new TXTController(new Repository<>(PlainTextRecord.class));
                boolean bool=true;
                if (a[2].equals("0")){
                    bool=false;
                }
                controller.save(new PlainTextRecord(date,a[1],bool));
                return "сохранено";
            }
        }
        return "";
    }

    private String executeCommand(String message) {
        switch (message) {
            case "/start" -> {
                return "Hello";
            }
            case "/author" -> {
                return "Пома Сахорович(Прохорович Роман) при поддержки Бибики Виковны(Андреева Виктория), группа 6304";
            }
            case "/analyze" -> {
                state = State.ANALYZE;
                return "Введите цикл для анализа:";
            }
            case "/byte_or" -> {
                state = State.BYTE_OR;
                return "Введите 2 числа через пробел!";
            }
            case "/div" -> {
                state = State.DIV;
                return "Введите 2 числа через пробел!";
            }
            case "/save_csv_file" -> {
                state = State.SAVE_CSV;
                return "Введите через ; запись, версию и имя";
            }
            case "/save_txt_file" -> {
                state = State.SAVE_TXT;
                return "Введите через ;  дату(год: месяц: день), путь, режим доступа(0,1)";
            }
            default -> {
                return "xz";
            }
        }
    }


    @Override
    public String getBotUsername() {
        return config.getName();
    }
}

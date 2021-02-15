package by.zadziarnouski.telegrambotdemo;


import by.zadziarnouski.telegrambotdemo.model.Description;
import by.zadziarnouski.telegrambotdemo.service.CityService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public class Bot extends TelegramLongPollingBot {
    private final Map<String, String> SYSTEM_COMMAND = new HashMap<>();
    String username;
    String token;

    {
        SYSTEM_COMMAND.put("/start", "Hi, my dear friend!\nI'll show you everything here.\n" +
                "Please, click or enter '/list' to get a list of available cities" +
                " or '/help' to get general information about me.");
        SYSTEM_COMMAND.put("/help", "I am a tourist assistant.\nI will help you here, you will not get lost with me." +
                "\nCommon commands: \n" +
                " '/list' - to get a list of available cities\n" +
                " '/help' - to get general information about me");
    }

    private final CityService cityService;

    public Bot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    /**
     * Method for receiving messages from Telegram Bot API.
     *
     * @param update Contains a message from the user.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = null;
        String charId = null;

        if (update.hasMessage()) {
            message = update.getMessage().getText();
            charId = update.getMessage().getChatId().toString();
        } else if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getData();
            charId = update.getCallbackQuery().getFrom()
                    .getId().toString();
        }

        if (message.equals("/start")) {
            sendMsg(charId, SYSTEM_COMMAND.get(message));
        } else if (message.equals("/help")) {
            sendMsg(charId, SYSTEM_COMMAND.get(message));
        } else if (message.equals("/list")) {
            sendMsg(charId, cityService.getAllCityNames());
        } else if (Objects.nonNull(cityService.findByName(message))) {
            for (Description d : cityService.findByName(message).getDescriptions()
            ) {
                sendMsg(charId, d.getText());
            }
        } else sendMsg(charId, "City not found. Enter '/list' to get a list of available cities");
    }

    /**
     * Method for creating a message and sending it to user.
     *
     * @param chatId chat id.
     * @param s      the String that you want to send as a message.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(s)
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for registration of the bot that to be executed after dependency injection is done.
     */
    @PostConstruct
    public void runTelegramApiClient() {
        try {
            String pathToBotProperties = Thread.currentThread().getContextClassLoader().getResource("bot.properties").getPath();
            Properties properties = new Properties();
            properties.load(new FileInputStream(pathToBotProperties));
            this.username = (String) properties.get("username");
            this.token = (String) properties.get("token");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (NullPointerException | IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

package org.example.lesson3;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class CryptoBot {
    public static void main(String[] args) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new MyBot());
    }


}
//SmallCryptoExampleBot
//6744147068:AAEeQiZmhOaA6Y74T-3mr1I7IspSij7Y_PI

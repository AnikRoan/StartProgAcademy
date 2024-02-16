package org.example.lesson3;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotOne extends TelegramLongPollingBot {
    public BotOne() {
        super("6744147068:AAEeQiZmhOaA6Y74T-3mr1I7IspSij7Y_PI");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();
        try {
            var message = new SendMessage();
            message.setChatId(chatId);

            switch (text) {
                case "/start" -> message.setText("Hello");
                case "btc" -> message.setText("BTC price " + CryptoPrice.spotPrice("BTC").getAmount().doubleValue());
                case "etc" -> message.setText("ETC price " + CryptoPrice.spotPrice("ETC").getAmount().doubleValue());
                case "doge" -> message.setText("DOGE price " + CryptoPrice.spotPrice("DOGE").getAmount().doubleValue());
                case "sol" -> message.setText("SOL price " + CryptoPrice.spotPrice("SOL").getAmount().doubleValue());


                default -> message.setText("Unknown command");
            }

            execute(message);

        } catch (Exception e) {
            System.out.println("Error");
        }


    }

    @Override
    public String getBotUsername() {
        return "SmallCryptoExampleBot";
    }

}

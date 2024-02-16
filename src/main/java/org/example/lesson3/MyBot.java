package org.example.lesson3;

import net.thauvin.erik.crypto.CryptoException;
import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class MyBot extends TelegramLongPollingBot {


    public MyBot() {
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
                case "etc,btc" ->
                        message.setText("BTC price " + CryptoPrice.spotPrice("BTC").getAmount().doubleValue() + "\n"
                                + "ETC price " + CryptoPrice.spotPrice("ETC").getAmount().doubleValue());


                case "btc,etc,dodge" ->
                        message.setText("BTC price " + CryptoPrice.spotPrice("BTC").getAmount().doubleValue() + "\n"
                                + "ETC price " + CryptoPrice.spotPrice("ETC").getAmount().doubleValue() + "\n"
                                + "DOGE price " + CryptoPrice.spotPrice("DOGE").getAmount().doubleValue());


                case "/all" ->
                        message.setText("BTC price " + CryptoPrice.spotPrice("BTC").getAmount().doubleValue() + "\n"
                                + "ETC price " + CryptoPrice.spotPrice("ETC").getAmount().doubleValue() + "\n"
                                + "DOGE price " + CryptoPrice.spotPrice("DOGE").getAmount().doubleValue() + "\n"
                                + "SOL price " + CryptoPrice.spotPrice("SOL").getAmount().doubleValue());


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



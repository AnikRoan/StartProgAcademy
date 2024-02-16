package org.example.lesson3;

import net.thauvin.erik.crypto.CryptoException;
import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class MyBot extends TelegramLongPollingBot {


    public MyBot() {
        super("6744147068:AAEeQiZmhOaA6Y74T-3mr1I7IspSij7Y_PI");
    }

    void info(Long id, String text) throws TelegramApiException {
        var message = new SendMessage();
        message.setChatId(id);

        message.setText(text);
        execute(message);
    }

    void price(Long id, String text) throws TelegramApiException, IOException, CryptoException {
        var price = CryptoPrice.spotPrice(text);
        info(id, text + " price: " + price.getAmount().doubleValue());

    }

    void sendPicture(Long id, String picture) throws TelegramApiException {
        var photo = getClass().getClassLoader().getResourceAsStream(picture);
        var message = new SendPhoto();
        message.setChatId(id);
        message.setPhoto(new InputFile(photo, picture));
        execute(message);

    }

    String getQuantity(Long id, String text) throws IOException, CryptoException, TelegramApiException {
        String regex = "\\+?\\d+(\\.\\d+)?";
        var message = new SendMessage();
        message.setChatId(id);
        String[] elm = text.split(" ");
        if (elm.length == 2 && elm[1].matches(regex)) {
            var price = CryptoPrice.spotPrice(elm[0].toUpperCase());
            double result = Double.parseDouble(elm[1]) / price.getAmount().doubleValue();

            message.setText("you can buy " + result);
            execute(message);
            return elm[0];


        }
        return text;


    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();


        try {
            text = getQuantity(chatId, text);

            switch (text) {
                case "/start" -> {

                    info(chatId, "Hello");


                }
                case "btc" -> {
                    sendPicture(chatId, "bitcoin.png");
                    price(chatId, "BTC");
                }
                case "etc" -> {
                    sendPicture(chatId, "ethereum-classic.png");
                    price(chatId, "ETC");
                }
                case "doge" -> {
                    sendPicture(chatId, "ethereum-classic.png");
                    price(chatId, "DOGE");
                }
                case "sol" -> {
                    sendPicture(chatId, "solana.png");
                    price(chatId, "SOL");
                }
                case "etc,btc" -> {
                    sendPicture(chatId, "ethereum-classic.png");
                    price(chatId, "ETC");
                    sendPicture(chatId, "bitcoin.png");
                    price(chatId, "BTC");


                }


                case "btc,etc,dodge" -> {
                    sendPicture(chatId, "ethereum-classic.png");
                    price(chatId, "ETC");
                    sendPicture(chatId, "bitcoin.png");
                    price(chatId, "BTC");
                    sendPicture(chatId, "dogecoin.png");
                    price(chatId, "DOGE");

                }


                case "/all" -> {
                    sendPicture(chatId, "ethereum-classic.png");
                    price(chatId, "ETC");

                    sendPicture(chatId, "bitcoin.png");
                    price(chatId, "BTC");

                    sendPicture(chatId, "dogecoin.png");
                    price(chatId, "DOGE");

                    sendPicture(chatId, "solana.png");
                    price(chatId, "SOL");

                }


                default -> info(chatId, "Unknown command");
            }


        } catch (Exception e) {
            System.out.println("Error");
        }


    }

    @Override
    public String getBotUsername() {
        return "SmallCryptoExampleBot";
    }


}



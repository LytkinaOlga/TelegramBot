package by.bntu.fitr.poisit.lytkina.telegram_bot;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Component
public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "1172193353:AAH6AsSYZDA5HhbK6nDOwpqNKLF-19JsSFA";
    }

    @Override
    public String getBotUsername() {
        return "MFALBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String response = "I don't know what i should do:(";
            if (message.getText() != null) {
                String text = message.getText();

                if (text.equals("/hello")) {
                    response = "Hello you too, " + message.getFrom().getFirstName() + "! If you want to flip a coin"
                            + "\nClick /flip to start:)";

                } else if (text.equals("/bye")) {
                    response = "OK. See you soon)";

                } else if (text.equals("/flip")){
                    int number = getRandomNumber();
                    if(number == 0) {
                        response = "HEADS (орел)";
                    }else {
                        response = "TAILS (решка)";
                    }
                }
            }
            sendMessage(message, response);
        }
    }
    public void sendMessage(Message message, String response){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public int getRandomNumber(){
        Random random = new Random();
        int number = random.nextInt(2);
        return  number;
    }
}

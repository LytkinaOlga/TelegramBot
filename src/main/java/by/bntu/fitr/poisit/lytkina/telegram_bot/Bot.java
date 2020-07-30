package by.bntu.fitr.poisit.lytkina.telegram_bot;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            if (message.getText() != null) {
                String text = message.getText();
                if (text.equals("hello")) {
                    String response = "Hello you too, " + message.getFrom().getFirstName();

                    sendMessage(message, response);
                } else if (text.equals("bye")) {
                    String response = "OK. See you soon)";
                    sendMessage(message, response);
                }
            }
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
}

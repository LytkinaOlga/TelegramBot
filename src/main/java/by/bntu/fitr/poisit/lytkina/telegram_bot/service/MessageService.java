package by.bntu.fitr.poisit.lytkina.telegram_bot.service;

import by.bntu.fitr.poisit.lytkina.telegram_bot.bean.ExampleChatBot;
import by.bntu.fitr.poisit.lytkina.telegram_bot.bean.Person;
import by.bntu.fitr.poisit.lytkina.telegram_bot.buttonHandler.ButtonHandler;
import by.bntu.fitr.poisit.lytkina.telegram_bot.repo.PersonRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    ExampleChatBot exampleChatBot;
    @Autowired
    ButtonHandler buttonHandler;
    @Autowired
    PersonRepositoryI personRepository;
    @Autowired
    Optional<Person> person;


    public void sendMessage(SendMessage sendMessage) {
        try {
            exampleChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        SendPhoto sendPhoto = new SendPhoto();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        if (message.hasPhoto()) {
            sendPhotoInChat(message);
        }
        try {
            exampleChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message, String response) {
        SendPhoto sendPhoto = new SendPhoto();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        if (message.hasPhoto()) {
            sendPhotoInChat(message);
        }
        try {
            exampleChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendPhotoInChat(Message message) {
        long chat_id = message.getChatId();

        List<PhotoSize> photos = message.getPhoto();
        String fileId = photos.stream()
                .findFirst()
                .orElse(null).getFileId();

        SendPhoto msg = new SendPhoto()
                .setChatId(chat_id)
                .setPhoto(fileId);
        try {
            exampleChatBot.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}

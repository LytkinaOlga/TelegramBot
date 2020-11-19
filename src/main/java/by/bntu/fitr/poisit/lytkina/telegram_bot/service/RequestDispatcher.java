package by.bntu.fitr.poisit.lytkina.telegram_bot.service;

import by.bntu.fitr.poisit.lytkina.telegram_bot.buttonHandler.ButtonHandler;
import by.bntu.fitr.poisit.lytkina.telegram_bot.enums.BotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.bntu.fitr.poisit.lytkina.telegram_bot.enums.BotCommand.START;
@Service
public class RequestDispatcher {

    @Autowired
    MessageService messageService;
    @Autowired
    ButtonHandler buttonHandler;

    private static String name = "";
    private static String state = "0";

    public void dispatch(Update update) {
        switch (getCommand(update)) {
            case START:
                messageService.sendMessage(buttonHandler.getButtonMainMenu(update.getMessage().getChatId(), "Привет!"));
                break;
            case INPUT_DATA:
                messageService.sendMessage(buttonHandler.getButtonInputData(update.getMessage().getChatId(), "Выберите действие"));
                state = "askName";
            default:
                break;
        }
    }
    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getText() != null) {
                String text = message.getText();

                if (text.equals("/start")) {
                    return BotCommand.START;

                } else if (text.startsWith("Ввести личную информацию")) {
                    return BotCommand.INPUT_DATA;

                } else if (text.equals("Моя информация")) {
                    return BotCommand.PRINT_INF;

                } else if (text.equals("Отправить фото")) {
                    return BotCommand.SENT_PHOTO;
                } else if (state.equals("askName")) {
                    name = text;
                    return BotCommand.ASK_AGE;
                }
            }

        }
//        else if (update.hasCallbackQuery()) {
//            CallbackQuery buttonQuery = update.getCallbackQuery();
//            messageId = buttonQuery.getMessage().getMessageId();
        return null;
    }
}

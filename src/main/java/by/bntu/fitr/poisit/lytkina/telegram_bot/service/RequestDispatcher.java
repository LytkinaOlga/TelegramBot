package by.bntu.fitr.poisit.lytkina.telegram_bot.service;

import by.bntu.fitr.poisit.lytkina.telegram_bot.bean.Person;
import by.bntu.fitr.poisit.lytkina.telegram_bot.buttonHandler.ButtonHandler;
import by.bntu.fitr.poisit.lytkina.telegram_bot.enums.BotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RequestDispatcher {

    @Autowired
    MessageService messageService;
    @Autowired
    ButtonHandler buttonHandler;
    @Autowired
    Person person;
    @Autowired
    PersonService personService;

    private static String name = "";
    private static String age = "";
    private static String state = "";
    private static String address = "";

    public void dispatch(Update update) {
        switch (getCommand(update)) {
            case START:
                messageService.sendMessage(buttonHandler.getButtonMainMenu(update.getMessage().getChatId(), "Привет!"));
                break;
            case INPUT_DATA:
                messageService.sendMessage(buttonHandler.getButtonInputData(update.getMessage().getChatId(), "Выберите действие"));
                state = "getId";
                break;
            case ASK_NAME:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите имя");
                state = "askName";
                break;
            case ASK_AGE:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите возраст");
                state = "askAge";
                break;
            case ASK_ADDRESS:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите адрес");
                state = "askAddress";
                break;
            case PRINT_INF:
                if (personService.checkIfPersonDataExist(update.getMessage())) {
                    person = personService.findPersonById(update);
                    messageService.sendMessage(update.getMessage(), personService.printInf(person));
                } else messageService.sendMessage(update.getMessage(), personService.printInf(person));
                break;
            case SENT_PHOTO:
                messageService.sendMessage(update.getMessage(), "Загрузите фото");
                state = "sendPhoto";
                break;
            case DOWNLOAD_PHOTO:
                messageService.sendMessage(update.getMessage());
                break;
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
                    person.setName(name);
                    personService.savePerson(person);
                    return BotCommand.INPUT_DATA;
                } else if (state.equals("askAge")) {
                    age = text;
                    person.setAge(age);
                    personService.savePerson(person);
                    return BotCommand.INPUT_DATA;
                } else if (state.equals("askAddress")) {
                    address = text;
                    person.setAddress(address);
                    personService.savePerson(person);
                    return BotCommand.INPUT_DATA;
                }

            } else if (update.getMessage().hasPhoto()) {
                return BotCommand.DOWNLOAD_PHOTO;
            }

        } else if (update.hasCallbackQuery()) {
            CallbackQuery buttonQuery = update.getCallbackQuery();
            if (state.equals("getId")) {
                person.setId(update.getCallbackQuery().getFrom().getId());
                personService.savePerson(person);
            }
            if (buttonQuery.getData().equals("Input name")) {
                return BotCommand.ASK_NAME;
            } else if (buttonQuery.getData().equals("Input age")) {
                return BotCommand.ASK_AGE;
            } else if (buttonQuery.getData().equals("Input address")) {
                return BotCommand.ASK_ADDRESS;
            }
        }
        return null;
    }
}

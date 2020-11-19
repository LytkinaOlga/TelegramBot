package by.bntu.fitr.poisit.lytkina.telegram_bot.buttonHandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonHandler {
    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();

        row1.add(new InlineKeyboardButton().setText("Ввести ФИО").setCallbackData("Input name"));
        row2.add(new InlineKeyboardButton().setText("Ввести возраст").setCallbackData("Input age"));
        row3.add(new InlineKeyboardButton().setText("Ввести Адрес").setCallbackData("Input address"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(new KeyboardButton("Ввести личную информацию"));
        row2.add(new KeyboardButton("Моя информация"));
        row3.add(new KeyboardButton("Отправить фото"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public SendMessage getButtonInputData(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getInlineKeyboardMarkup());
    }

    public SendMessage getButtonMainMenu(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getMainMenuKeyboard());
    }
}

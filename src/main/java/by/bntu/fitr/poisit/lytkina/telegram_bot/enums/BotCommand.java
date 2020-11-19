package by.bntu.fitr.poisit.lytkina.telegram_bot.enums;

public enum BotCommand {
    START("/start"),
    INPUT_DATA,
    PRINT_INF,
    SENT_PHOTO,
    ASK_AGE;

    String command;

    BotCommand() {

    }
    public String getCommand() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }

}

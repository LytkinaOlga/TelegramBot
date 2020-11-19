package by.bntu.fitr.poisit.lytkina.telegram_bot.enums;

public enum BotCommand {
    START("/start"),
    INPUT_DATA,
    PRINT_INF,
    SENT_PHOTO,
    ASK_AGE,
    ASK_NAME,
    ASK_ADDRESS,
    RETURN_TO_MAIN_MENU,
    DOWNLOAD_PHOTO;

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

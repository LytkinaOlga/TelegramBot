package by.bntu.fitr.poisit.lytkina.telegram_bot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Person {
    private String name;
    private String age;
    private String address;
}

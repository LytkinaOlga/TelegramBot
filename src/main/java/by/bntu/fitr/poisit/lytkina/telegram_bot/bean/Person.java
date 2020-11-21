package by.bntu.fitr.poisit.lytkina.telegram_bot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Component
@Entity
public class Person {
    @Id
    private Integer id;
    private String name;
    private String age;
    private String address;
}

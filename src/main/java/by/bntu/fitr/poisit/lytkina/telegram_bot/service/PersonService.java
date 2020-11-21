package by.bntu.fitr.poisit.lytkina.telegram_bot.service;

import by.bntu.fitr.poisit.lytkina.telegram_bot.bean.Person;
import by.bntu.fitr.poisit.lytkina.telegram_bot.repo.PersonRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepositoryI personRepository;
    private Optional<Person> person;

    public boolean checkIfPersonDataExist(Message message) {
        if (personRepository.existsById(message.getFrom().getId())) {
            return true;
        } else return false;
    }

    public Person findPersonById(Update update) {
        Integer id = update.getMessage().getFrom().getId();
        person = personRepository.findById(id);
        return person.get();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public String printInf(Person person) {
        String result = "";
        if (person.getName() != null) {
            result = "Ваше имя: " + person.getName();
        } else result = "Ваше имя: данные отсутствуют";

        if (person.getAge() != null) {
            result += "\nВаш возраст: " + person.getAge();
        } else result += "\nВаш возраст: данные отсутствуют";

        if (person.getAddress() != null) {
            result += "\nВаш адрес: " + person.getAddress();
        } else result += "\nВаш адрес: данные отсутствуют";

        return result;
    }
}

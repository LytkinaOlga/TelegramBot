package by.bntu.fitr.poisit.lytkina.telegram_bot.repo;

import by.bntu.fitr.poisit.lytkina.telegram_bot.bean.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}

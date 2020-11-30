package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  // асимптотика: преобразование списка в мапу - O(n)
  // обход списка -  O(n), Map.get(...) - O(1), ArrayList.add(...) - O(1)
  // Итого: O(n) + O(n) = O(n)
  // По памяти O(n) дополнительно для хранения мапы
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);
    Map<Integer, Person> personMap = persons.stream().collect(Collectors.toMap(Person::getId, person -> person));

    return personIds.stream().map(personMap::get)
            .collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}

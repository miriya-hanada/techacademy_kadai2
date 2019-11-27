package dbSample2;

import java.util.Iterator;
import java.util.List;

public class Maintwo {

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();

        List<Person> persons = personDAO.getPersonFromKadaidb("花田実里弥");

        Iterator<Person> it = persons.iterator();
        while (it.hasNext()) {
            Person person = it.next();
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getAge());

        }
    }
}

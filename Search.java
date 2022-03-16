import java.util.List;

public class Search {

    public static int linearSearch(String name, List<Person> phoneBook) {

        for (int i = 0; i < phoneBook.size(); i++) {
            if (phoneBook.get(i).getName().equals(name)) {
               return i;
            }
        }

        return -1;
    }
}

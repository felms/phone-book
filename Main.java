import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Lê os arquivos
        List<Person> phoneBook = new ArrayList<>();
        List<String> searchList = new ArrayList<>();
        try{
            File directory = new File("C:\\Users\\Almoxarife\\IdeaProjects\\Phone Book" +
                    "\\Phone Book\\task\\src\\phonebook\\directory.txt");
            File find = new File("C:\\Users\\Almoxarife\\IdeaProjects\\Phone Book" +
                    "\\Phone Book\\task\\src\\phonebook\\find.txt");

            Scanner scanner = new Scanner(directory);
            while (scanner.hasNext()) {
                String number = scanner.next();
                String name = scanner.nextLine().trim();
                phoneBook.add(new Person(name, number));
            }

            scanner = new Scanner(find);
            while (scanner.hasNext()) {
                String name = scanner.nextLine();
                searchList.add(name);
            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }



        // Faz a busca
        int found = 0;

        System.out.println("Start searching...");
        long time0 = System.currentTimeMillis();
        for (String s : searchList) {
            if (Search.linearSearch(s, phoneBook) >= 0) {
                found++;
            }
        }
        long time = System.currentTimeMillis() - time0;

        long minutes = (time / 1000) / 60;
        long seconds = (time / 1000) % 60;
        long t1 = (minutes * 60) + seconds;
        long miliseconds = time - (t1 * 1000);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, searchList.size(), minutes, seconds, miliseconds);

    }
}

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
            File directory = new File("C:\\Users\\Almoxarife\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\phonebook\\directory.txt");
            File find = new File("C:\\Users\\Almoxarife\\IdeaProjects\\Phone Book\\Phone Book\\task\\src\\phonebook\\find.txt");

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



        // --- Faz a busca Linear
        int found = 0;

        System.out.println("\nStart searching (linear search)...");
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
        long milliseconds = time - (t1 * 1000);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, searchList.size(), minutes, seconds, milliseconds);


        // --- Faz a Jump Search
        found = 0;

        System.out.println("\nStart searching (bubble sort + jump search)...");
        long sTime = System.currentTimeMillis();
        boolean sorted = Sort.bubbleSort(phoneBook, time * 10);

        long sortTime = System.currentTimeMillis() - sTime;
        long sMinutes = (sortTime / 1000) / 60;
        long sSeconds = (sortTime / 1000) % 60;
        long sT1 = (sMinutes * 60) + sSeconds;
        long sMilliseconds = sortTime - (sT1 * 1000);

        if (sorted) {

            long bTime = System.currentTimeMillis();
            for (String s : searchList) {
                if (Search.jumpSearch(s, phoneBook) >= 0) {
                    found++;
                }
            }
            long jsTime = System.currentTimeMillis() - bTime;
            long jsMinutes = (jsTime / 1000) / 60;
            long jsSeconds = (jsTime / 1000) % 60;
            long jsT1 = (jsMinutes * 60) + jsSeconds;
            long jsMilliseconds = jsTime - (jsT1 * 1000);

            long totalTime = sortTime + jsTime;
            long totalMinutes = (totalTime / 1000) / 60;
            long totalSeconds = (totalTime / 1000) % 60;
            long totalT1 = (totalMinutes * 60) + totalSeconds;
            long totalMilliseconds = totalTime - (totalT1 * 1000);
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n" +
                            "Sorting time: %d min. %d sec. %d ms.\n" +
                            "Searching time: %d min. %d sec. %d ms.\n",
                    found, searchList.size(),
                    totalMinutes, totalSeconds, totalMilliseconds,
                    sMinutes, sSeconds, sMilliseconds,
                    jsMinutes, jsSeconds, jsMilliseconds);
        } else {
            long bTime = System.currentTimeMillis();
            for (String s : searchList) {
                if (Search.linearSearch(s, phoneBook) >= 0) {
                    found++;
                }
            }
            long jsTime = System.currentTimeMillis() - bTime;
            long jsMinutes = (jsTime / 1000) / 60;
            long jsSeconds = (jsTime / 1000) % 60;
            long jsT1 = (jsMinutes * 60) + jsSeconds;
            long jsMilliseconds = jsTime - (jsT1 * 1000);

            long totalTime = sortTime + jsTime;
            long totalMinutes = (totalTime / 1000) / 60;
            long totalSeconds = (totalTime / 1000) % 60;
            long totalT1 = (totalMinutes * 60) + totalSeconds;
            long totalMilliseconds = totalTime - (totalT1 * 1000);
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n" +
                            "Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n" +
                            "Searching time: %d min. %d sec. %d ms.\n",
                    found, searchList.size(),
                    totalMinutes, totalSeconds, totalMilliseconds,
                    sMinutes, sSeconds, sMilliseconds,
                    jsMinutes, jsSeconds, jsMilliseconds);
        }


    }
}

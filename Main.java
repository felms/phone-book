import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import java.util.Scanner;

public class Main {

    private static long time;
    public static void main(String[] args) {

        // Lê os arquivos
        List<Person> phoneBook = new ArrayList<>();
        List<String> searchList = new ArrayList<>();
        try{
            File directory = new File("/home/felipe/Downloads/directory.txt");
            File find = new File("/home/felipe/Downloads/find.txt");

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

        List<Person> pb0 = new ArrayList<>(phoneBook);
        lSearch(pb0, searchList);

        List<Person> pb1 = new ArrayList<>(phoneBook);
        jSearch(pb1, searchList);

        List<Person> pb2 = new ArrayList<>(phoneBook);
        bSearch(pb2, searchList);

        List<Person> pb3 = new ArrayList<>(phoneBook);
        hashTSearch(pb3, searchList);

    }

    // Faz a busca Linear
    public static void lSearch(List<Person> phoneBook, List<String> searchList) {

        int found = 0;

        System.out.println("\nStart searching (linear search)...");
        long time0 = System.currentTimeMillis();
        for (String s : searchList) {
            if (Search.linearSearch(s, phoneBook) >= 0) {
                found++;
            }
        }
        time = System.currentTimeMillis() - time0;

        long minutes = (time / 1000) / 60;
        long seconds = (time / 1000) % 60;
        long t1 = (minutes * 60) + seconds;
        long milliseconds = time - (t1 * 1000);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, searchList.size(), minutes, seconds, milliseconds);
    }

    // Faz a Jump Search
    public static void jSearch(List<Person> phoneBook, List<String> searchList) {

        int found = 0;

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

    // Faz a Busca Binária
    public static void bSearch(List<Person> phoneBook, List<String> searchList) {

        int found = 0;

        System.out.println("\nStart searching (quick sort + binary search)...");
        long qsTime = System.currentTimeMillis();
        Sort.quicksort(phoneBook, 0, phoneBook.size() - 1);

        long qsortTime = System.currentTimeMillis() - qsTime;
        long qsMinutes = (qsortTime / 1000) / 60;
        long qsSeconds = (qsortTime / 1000) % 60;
        long qsT1 = (qsMinutes * 60) + qsSeconds;
        long qsMilliseconds = qsortTime - (qsT1 * 1000);

        long bsTime = System.currentTimeMillis();
        for (String s : searchList) {
            if (Search.binarySearch(phoneBook, s) >= 0) {
                found++;
            }
        }
        long bsearchTime = System.currentTimeMillis() - bsTime;
        long bsMinutes = (bsearchTime / 1000) / 60;
        long bsSeconds = (bsearchTime / 1000) % 60;
        long bsT1 = (bsMinutes * 60) + bsSeconds;
        long bsMilliseconds = bsearchTime - (bsT1 * 1000);

        long bsTotalTime = qsortTime + bsearchTime;
        long bsTotalMinutes = (bsTotalTime / 1000) / 60;
        long bsTotalSeconds = (bsTotalTime / 1000) % 60;
        long bsTotalT1 = (bsTotalMinutes * 60) + bsTotalSeconds;
        long bsTotalMilliseconds = bsTotalTime - (bsTotalT1 * 1000);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n" +
                        "Sorting time: %d min. %d sec. %d ms.\n" +
                        "Searching time: %d min. %d sec. %d ms.\n",
                found, searchList.size(),
                bsTotalMinutes, bsTotalSeconds, bsTotalMilliseconds,
                qsMinutes, qsSeconds, qsMilliseconds,
                bsMinutes, bsSeconds, bsMilliseconds);
    }

    // Faz a busca com a HashTable
    public static void hashTSearch(List<Person> phoneBook, List<String> searchList) {

        int found = 0;

        System.out.println("\nStart searching (hash table)...");
        long time0 = System.currentTimeMillis();
        HashMap<String, Person> hashMap = new HashMap<>();
        for (Person p : phoneBook) {
            hashMap.put(p.getName(), p);
        }
        long htTime = System.currentTimeMillis() - time0;
        long htMinutes = (htTime / 1000) / 60;
        long htSeconds = (htTime / 1000) % 60;
        long htT1 = (htMinutes * 60) + htSeconds;
        long htMilliseconds = htTime - (htT1 * 1000);


        long time1 = System.currentTimeMillis();
        for (String s : searchList) {
            Person p = hashMap.get(s);
            if (p != null) {
                found++;
            }
        }
        long htSearchTime = System.currentTimeMillis() - time1;
        long htsMinutes = (htSearchTime / 1000) / 60;
        long htsSeconds = (htSearchTime / 1000) % 60;
        long htsT1 = (htsMinutes * 60) + htsSeconds;
        long htsMilliseconds = htSearchTime - (htsT1 * 1000);

        long htTotalTime = htTime + htSearchTime;
        long htTotalMinutes = (htTotalTime / 1000) / 60;
        long htTotalSeconds = (htTotalTime / 1000) % 60;
        long htTotalT1 = (htTotalMinutes * 60) + htTotalSeconds;
        long htTotalMilliseconds = htTotalTime - (htTotalT1 * 1000);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms\n" +
                        "Creating time: %d min. %d sec. %d ms.\n" +
                        "Searching time: %d min. %d sec. %d ms.\n",
                found, searchList.size(),
                htTotalMinutes, htTotalSeconds, htTotalMilliseconds,
                htMinutes, htSeconds, htMilliseconds,
                htsMinutes, htsSeconds, htsMilliseconds);

    }
}

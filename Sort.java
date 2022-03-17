import java.util.List;

public class Sort {

    public static boolean bubbleSort(List<Person> list, long maxTime) {

        long t0 = System.currentTimeMillis();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Person aux = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, aux);
                }
                long t1 = System.currentTimeMillis() - t0;
                if (t1 > maxTime) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void quicksort(List<Person> list, int low, int hi) {
            if (low < hi) {

                int p = partition(list, low, hi);

                quicksort(list, low, p - 1);
                quicksort(list, p + 1, hi);
            }
    }

    private static int partition(List<Person> list, int low, int hi) {

        Person pivot = list.get(hi);
        int i = low - 1 ;
        for (int j = low; j < hi; j++) {

            if (list.get(j).compareTo(pivot) <= 0) {
                i++;

                Person aux = list.get(i);
                list.set(i, list.get(j));
                list.set(j, aux);
            }
        }

        Person aux = list.get(i + 1);
        list.set(i + 1, list.get(hi));
        list.set(hi, aux);

        return i + 1;
    }
}

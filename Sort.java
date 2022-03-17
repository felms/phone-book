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
}

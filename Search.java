import java.util.List;

public class Search {

    public static int linearSearch(String name, List<Person> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
               return i;
            }
        }

        return -1;
    }

    public static int jumpSearch(String name, List<Person> list) {

        int step = (int) Math.floor(Math.sqrt(list.size()));
        int curr = 0;
        int ind = 0;

        while (curr < list.size()) {
            if (list.get(curr).getName().equals(name)) {
                return curr;
            } else if (list.get(curr).getName().compareTo(name) > 0) {
                ind = curr - 1;

                while ((ind > curr - step) && (ind > 1)) {
                    if (list.get(ind).getName().equals(name)) {
                        return ind;
                    }
                    ind--;
                }
                return -1;
            }
            curr += step;
        }

        ind = list.size();

        while (ind > curr - step) {
            if (list.get(ind).getName().equals(name)) {
                return ind;
            }
            ind--;
        }


        return -1;
    }

    public static int binarySearch(List<Person> list, String name) {

        int left = -1;
        int right = list.size();

        while (left <= right) {
            int middle = (left + right) / 2;

            if(list.get(middle).getName().equals(name)) {
                return middle;
            } else if(list.get(middle).getName().compareTo(name) > 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }
}

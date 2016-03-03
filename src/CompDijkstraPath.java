import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompDijkstraPath implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof LinkedList && o2 instanceof LinkedList) {
            double weightOne = 0;
            double weightTwo = 0;

            for (Object edge : (LinkedList) o1) {
                weightOne += ((Edge) edge).getWeight();
            }

            for (Object edge : (LinkedList) o2) {
                weightTwo += ((Edge) edge).getWeight();
            }
            double sum = weightOne-weightTwo;

            if (sum != 0) {
                return sum > 0 ? 1 : -1;
            }
        } else if (o1 instanceof Comparable && o2 instanceof Comparable) {
            return ((Comparable) o1).compareTo(o2);
        }

        return 0;
    }
}

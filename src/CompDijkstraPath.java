import java.util.Comparator;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompDijkstraPath implements Comparator<QueueElement> {
    @Override
    public int compare(QueueElement o1, QueueElement o2) {
        double temp = o1.getWeight() - o2.getWeight();

        if (temp > 0) {
            return 1;
        } else if ( temp < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

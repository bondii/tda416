import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompDijkstraPath implements Comparator<LinkedList<Edge>> {

    @Override
    public int compare(LinkedList<Edge> o1, LinkedList<Edge> o2) {
        double weightOne = 0;
        double weightTwo = 0;

        for (Edge edge : o1) {
            weightOne += edge.getWeight();
        }

        for (Edge edge : o2) {
            weightTwo += edge.getWeight();
        }
        double sum = weightOne-weightTwo;

        if (sum != 0) {
            return sum > 0 ? 1 : -1;
        }

        return 0;
    }

}

import java.util.Comparator;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompKruskalEdge implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        if (o1.getWeight() - o2.getWeight() > 0) {
            return 1;
        } else if (o1.getWeight() - o2.getWeight() < 0) {
            return -1;
        }

        return 0;
    }
}

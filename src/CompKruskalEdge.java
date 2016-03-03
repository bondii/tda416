import java.util.Comparator;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompKruskalEdge implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Edge && o2 instanceof Edge) {
            if (((Edge) o1).getWeight() - ((Edge) o2).getWeight() > 0) {
                return 1;
            } else if (((Edge) o1).getWeight() - ((Edge) o2).getWeight() < 0) {
                return -1;
            }
        } else if (o1 instanceof Comparable && o2 instanceof Comparable) {
            return ((Comparable) o1).compareTo(o2);
        }

        return 0;
    }
}

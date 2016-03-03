import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-29.
 */
public class QueueElement<E extends Edge> implements Comparable {
    int node;
    Double cost;
    LinkedList<E> path;

    public QueueElement (int node, double cost, LinkedList<E> path) {
        this.node = node;
        this.cost = cost;
        this.path = path;
    }

    public int getNode() {
        return node;
    }

    public LinkedList<E> getPath() {
        return path;
    }

    public double getWeight() {
        return cost;

        /*
        double weight = 0;
        for (Edge edge : path) {
            weight += edge.getWeight();
        }
        return weight;
         */
    }

    @Override
    public int compareTo(Object o) {
        double temp = this.cost - ((QueueElement) o).getWeight();

        if (temp > 0) {
            return 1;
        } else if ( temp < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

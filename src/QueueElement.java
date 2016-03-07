import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-29.
 */
public class QueueElement<E extends Edge> {
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
    }
}

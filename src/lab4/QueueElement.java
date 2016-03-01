package lab4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-29.
 */
public class QueueElement<E extends Edge> {
    NodeObject node;
    Double cost;
    LinkedList<E> path;

    public QueueElement (NodeObject node, double cost, LinkedList<E> path) {
        this.node = node;
        this.cost = cost;
        this.path = path;
    }

    public NodeObject getNode() {
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
}

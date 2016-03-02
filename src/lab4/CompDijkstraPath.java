package lab4;

import java.util.LinkedList;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompDijkstraPath {
    public static LinkedList<Edge> compPath(LinkedList<Edge> pathOne, LinkedList<Edge> pathTwo) {
        int weightOne = 0;
        int weightTwo = 0;

        for (Edge edge : pathOne) {
            weightOne += edge.getWeight();
        }

        for (Edge edge : pathTwo) {
            weightTwo += edge.getWeight();
        }

        return weightOne >= weightTwo ? pathTwo : pathOne;
    }
}

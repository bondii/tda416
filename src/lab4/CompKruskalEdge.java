package lab4;

/**
 * Created by Bondi on 2016-02-25.
 */
public class CompKruskalEdge {
    public static Edge getSmallest(Edge edgeOne, Edge edgeTwo) {
        return isBiggerThan(edgeOne, edgeTwo) ? edgeTwo : edgeOne;
    }

    public static boolean isBiggerThan(Edge edgeOne, Edge edgeTwo) {
        return edgeOne.getWeight() > edgeTwo.getWeight();
    }
}

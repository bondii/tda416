import java.awt.geom.Point2D;
import DLList.Node;

public class Lab2b {

    public static double[] simplifyShape(double[] poly, int k) {
        DLList list = new DLList();
        int size = 2;
        for (int i = 0; i < poly.length; i+=2) {
            list.addLast(new Point(poly[i], poly[i+1]));
        }

        Node node = list.getFirst();
        node = node.getNext();

        Node smallest = node;
        double dist = calcVal((Point) node.elt, (Point)  node.getPrev().elt, (Point) node.getNext().elt);
        ((Point) node.elt).setVal(dist);
        node = node.getNext();

        while (node.getNext().getNext() != null) {
            double newDist = calcVal((Point) node.elt, (Point) node.getPrev().elt, (Point) node.getNext().elt);
            ((Point) node.elt).setVal(newDist);
            if (dist > newDist) {
                dist = newDist;
                smallest = node;
            }
            size++;
        }

        while(size > k) {
            ((Point) smallest.getPrev().elt).setVal(calcVal((Point) smallest.getPrev().elt, (Point) smallest.getPrev().getPrev().elt, (Point) smallest.getNext().elt));
            ((Point) smallest.getNext().elt).setVal(calcVal((Point) smallest.getNext().elt, (Point) smallest.getPrev().elt, (Point) smallest.getNext().getNext().elt));
            list.remove(smallest);
            size--;
        }

        return null;
    }

    public static double calcVal(Point p, Point l, Point r) {
        return Point2D.distance(l.getX(), l.getY(),p.getX(), p.getY()) +
                Point2D.distance(p.getX(), p.getY(), r.getX(), r.getY()) -
                Point2D.distance(l.getX(), l.getY(), r.getX(), r.getY());
    }
}



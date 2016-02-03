import java.awt.geom.Point2D;
import java.util.PriorityQueue;

//import DLList.Node; // Why does this not work?

public class Lab2b {

    public static double[] simplifyShape(double[] poly, int k) {
        DLList list = new DLList(); // creates new double-linked list for nodes
        int size = 0;

        for (int i = 0; i < poly.length; i+=2) {
            list.addLast(new Point(poly[i], poly[i+1])); // adding new element to list
            size++;
        }

        PriorityQueue queue = new PriorityQueue();//size);

        DLList.Node node = list.getFirst();
        node = node.getNext(); // skipping first node since it should never be removed

        while (node.getNext() != null) { // up until second last node because it should never be removed
            double newDist = calcVal((Point) node.elt, (Point) node.getPrev().elt, (Point) node.getNext().elt);
            ((Point) node.elt).setVal(newDist);
            queue.add(node); // adding Point to priorityqueue
            node = node.getNext();
        }

        while(size > k) {
            DLList.Node temp = (DLList.Node) queue.poll(); // retrieves and removes the head of the queue, which is the "smallest" Node/Point

            if (temp.getPrev().getPrev() != null) {
                ((Point) temp.getPrev().elt).setVal(calcVal((Point) temp.getPrev().elt, (Point) temp.getPrev().getPrev().elt, (Point) temp.getNext().elt));
            }
            if (temp.getNext().getNext() != null) {
                ((Point) temp.getNext().elt).setVal(calcVal((Point) temp.getNext().elt, (Point) temp.getPrev().elt, (Point) temp.getNext().getNext().elt));
            }

            list.remove(temp);
            size--;
        }

        double[] newArray = new double[size*2];
        DLList.Node temp = list.getFirst();
        for (int i = 0; i < size; i++) {
            newArray[i*2] = ((Point) temp.elt).getX();
            newArray[i*2+1] = ((Point) temp.elt).getY();
            temp = temp.getNext();
        }

        return newArray;
    }

    public static double calcVal(Point p, Point l, Point r) {
        return Point2D.distance(l.getX(), l.getY(),p.getX(), p.getY()) +
                Point2D.distance(p.getX(), p.getY(), r.getX(), r.getY()) -
                Point2D.distance(l.getX(), l.getY(), r.getX(), r.getY());
    }
}



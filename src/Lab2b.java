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

        PriorityQueue queue = new PriorityQueue();//size); // sorted for value

        DLList.Node node = list.getFirst();
        node = node.getNext(); // skipping first node since it should never be removed

        while (node.getNext() != null) { // up until second last node because it should never be removed
            double newDist = calcVal(node.elt, node.getPrev().elt, node.getNext().elt);
            Point.setVal(node.elt, newDist);
            queue.add(node); // adding Point to priorityqueue
            node = node.getNext();
        }

        while(size > k) { // removing elements with lowest value until k elements left
            DLList.Node temp = (DLList.Node) queue.poll(); // retrieves and removes the head of the queue, which is the "smallest" Node/Point

            if (temp.getPrev().getPrev() != null) {
                queue.remove(temp.getPrev()); // removing from queue and then adding again, in order to update new val
                Point.setVal(temp.getPrev().elt, calcVal(temp.getPrev().elt, temp.getPrev().getPrev().elt, temp.getNext().elt));
                queue.add(temp.getPrev());
            }
            if (temp.getNext().getNext() != null) {
                queue.remove(temp.getNext()); // removing from queue and then adding again, in order to update new val
                Point.setVal(temp.getNext().elt, calcVal(temp.getNext().elt, temp.getPrev().elt, temp.getNext().getNext().elt));
                queue.add(temp.getNext());
            }

            list.remove(temp); // removing node from list
            size--; // updating size
        }

        double[] newArray = new double[size*2]; // creating return array
        DLList.Node temp = list.getFirst();
        for (int i = 0; i < size; i++) {
            newArray[i*2] = ((Point) temp.elt).getX();
            newArray[i*2+1] = ((Point) temp.elt).getY();
            temp = temp.getNext();
        }

        return newArray;
    }

    public static double calcVal(Object p, Object l, Object r) {
        return calcVal((Point) p, (Point) l, (Point) r);
    }

    public static double calcVal(Point p, Point l, Point r) {
        return Math.sqrt(Math.pow(l.getX()-p.getX(), 2) + Math.pow(l.getY()-p.getY(), 2)) +
                Math.sqrt(Math.pow(p.getX()-r.getX(), 2) + Math.pow(p.getY()-r.getY(), 2)) -
                Math.sqrt(Math.pow(l.getX()-r.getX(), 2) + Math.pow(l.getY()-r.getY(), 2));
    }
}



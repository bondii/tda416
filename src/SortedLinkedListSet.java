
/**
 * Created by Bondi on 2016-02-09.
 */
public class SortedLinkedListSet<Integer> implements SimpleSet {
    public class Node implements Comparable {
        /** The contents of the node is public */
        public Comparable elt;

        protected Node prev, next;

        Node() {
            this(null);
        }
        Node(Comparable elt) {
            this.elt = elt;
            prev = next = null;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        @Override
        public int compareTo(Object o) {
            return (this.elt).compareTo(((Node) o).elt);
        }
    }

    /** first and last nodes in list, null when list is empty */
    Node first, last;

    int size;

    public SortedLinkedListSet() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Comparable x) {
        //System.out.println("running add");
        Node node = new Node(x);
        if (first == null) {
            //System.out.println("FIRST NULL" + x);
            first = node;
            last = node;
            size++;
            return true;
        } else {
            //System.out.println("First NOT null " + x);
            Node iterNode = first;

            while (iterNode != null) {
                //System.out.println("Node: " + node.elt + " iterNode: " + iterNode.elt);
                if (node.elt.equals(iterNode.elt)) {
                    //System.out.println("Node existed, returning false");
                    return false;
                } else if (node.compareTo(iterNode) < 0) {
                    //System.out.println("breaking");
                    break;
                }

                iterNode = iterNode.getNext();
            }


            node.next = iterNode;
            if (iterNode != null) {
                node.prev = iterNode.getPrev();
                if (iterNode.getPrev() != null) {
                    iterNode.getPrev().next = node;
                } else {
                    first = node;
                }
                iterNode.prev = node;
            } else {
                last.next = node;
                node.prev = last;
                last = node;
            }

        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Comparable x) {
        Node node = new Node(x);
        Node iterNode = first;

        while (iterNode != null) {
            if (iterNode.elt.equals(node.elt)) {
                if (iterNode.getPrev() != null) {
                    iterNode.getPrev().next = iterNode.getNext();
                } else {
                    first = iterNode.getNext();
                    iterNode.prev = null;
                }
                if (iterNode.getNext() != null) {
                    iterNode.getNext().prev = iterNode.getPrev();
                } else {
                    last = iterNode.getPrev();
                    iterNode.next = null;
                }

                size--;
                return true;
            }
            iterNode = iterNode.getNext();
        }

        return false;
    }

    @Override
    public boolean contains(Comparable x) {
        Node node = new Node(x);
        Node iterNode = first;

        //System.out.println("Testing contains for " + x);
        while (iterNode != null) {
            if (node.elt.equals(iterNode.elt)) {
                return true;
            }
            //System.out.println(iterNode.elt);
            iterNode = iterNode.getNext();
        }

        return false;
    }
}


/**
 * Created by Bondi on 2016-02-09.
 */
public class SortedLinkedListSet implements SimpleSet {
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
        first = last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Comparable x) {
        Node node = new Node(x);
        if (first == null) {
            first = node;
        } else {
            Node iterNode = first;

            while (true) {
                if (node.equals(iterNode)) {
                    return false;
                } else if (node.compareTo(iterNode) < 0) {
                    break;
                }

                if (iterNode.getNext()!=null) { //ugly solution, but hey it works
                    iterNode = iterNode.getNext();
                } else {
                    break;
                }
            }


            node.next = iterNode;
            iterNode.prev = node;

            if (iterNode.getPrev() == null) {
                first = node;
            } else {
                node.prev = iterNode.getPrev();
                iterNode.getPrev().next = node;
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
            if (iterNode.equals(node)) {
                iterNode.getPrev().next = iterNode.getNext();
                iterNode.getNext().prev = iterNode.getPrev();

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

        while (iterNode != null) {
            if (node.equals(iterNode)) {
                return true;
            }
        }

        return false;
    }
}

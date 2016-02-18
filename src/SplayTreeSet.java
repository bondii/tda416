/**
 * Created by Bondi on 2016-02-09.
 */
public class SplayTreeSet implements SimpleSet {

    private Node root;
    private int size;

    public class Node implements Comparable {
        /** The contents of the node is public */
        public Comparable elt;

        protected Node left, right;

        Node() {
            this(null);
        }

        Node(Comparable elt) {
            this.elt = elt;
            left = right = null;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public int compareTo(Object o) {
            return (this.elt).compareTo(((Node) o).elt);
        }
    }

    public SplayTreeSet() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Comparable x) {
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
        } else {
            Node focus = root;

            while (true) {
                int diff = focus.compareTo(newNode);
                if (focus.equals(newNode)) {
                    return false;
                } else if (diff > 0) {
                    if (focus.getLeft() == null) {
                        focus.left = newNode;
                        break;
                    } else {
                        focus = focus.getLeft();
                    }
                } else {
                    if (focus.getRight() == null) {
                        focus.right = newNode;
                        break;
                    } else {
                        focus = focus.getRight();
                    }
                }
            }
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Comparable x) {
        Node compNode = new Node(x);
        Node focus = root;

        // HANTERA FALLET compNode == root *****************************

        while (focus != null) {
            if (focus.getLeft().equals(compNode) || focus.getRight().equals(compNode)) {
                Node child = focus.getLeft().equals(compNode) ? focus.getLeft() : focus.getRight();
                Node newNode;

                if (child.getLeft() == null) {
                    if (child.getRight() == null) {
                        newNode = null;
                    } else {
                        newNode = child.getRight();
                    }
                } else if (child.getRight() == null) {
                    newNode = child.getLeft();
                } else {
                    // TVÅ BARN
                    int leftGen = 1;
                    int rightGen = 1;

                    Node current = focus.getLeft();

                    while (current.getRight().getRight() != null) {
                        leftGen++;
                        current = current.getRight();
                    }

                    Node parent = current;
                    newNode = current.getRight();
                    current = focus.getRight();

                    while (current.getLeft().getLeft() != null) {
                        rightGen++;
                        current = current.getLeft();
                    }

                    if (rightGen < leftGen) {
                        current = newNode.getRight();
                        parent.left = current;
                    } else {
                        current = newNode.getLeft();
                        parent.right = current;
                    }
                    newNode.left = focus.getLeft();
                    newNode.right = focus.getRight();

                }

                size--;
                return true;
            } else {
                focus = moveTowards(focus, compNode);
            }
        }

        return false;
    }

    private Node splay(Node node) {


        return null;
    }

    private Node zig(Node parent, Boolean rightChild) {
        Node newRoot;
        if (rightChild) {
            newRoot = parent.getRight();
            parent.right = newRoot.getLeft();
            newRoot.left = parent;
        } else {
            newRoot = parent.getLeft();
            parent.left = newRoot.getRight();
            newRoot.right = parent;
        }

        return newRoot;
    }

    // Runs two zigs after each other and returns the new "root".
    private Node zigZig(Node grandParent, Boolean rightChild) {
        return zig(zig(grandParent, rightChild), rightChild);
    }

    private Node zigZag(Node grandParent, Boolean rightChild) {
        Node child;
        if (rightChild) {
            child = grandParent.getRight().getLeft();
            zig(grandParent.getRight(), false);
            grandParent.right = child;
            zig(grandParent, true);
        } else {
            child = grandParent.getLeft().getRight();
            zig(grandParent.getLeft(), true);
            grandParent.left = child;
            zig(grandParent, false);
        }

        return child;
    }

    private Node moveTowards(Node from, Node to) {
        int diff = from.compareTo(to);
        if (diff > 0) {
            return from.getLeft();
        } else {
            return from.getRight();
        }
    }

    @Override
    public boolean contains(Comparable x) {
        Node compNode = new Node(x);
        Node focus = root;

        while (focus != null) {
            if (focus.equals(compNode)) {
                return true;
            } else {
                focus = moveTowards(focus, compNode);
            }
        }

        return false;
    }
}

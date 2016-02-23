/**
 * Created by Bondi on 2016-02-09.
 */
public class SplayTreeSet implements SimpleSet {

    private Node root;
    private int size;

    public class Node implements Comparable {
        /** The contents of the node is public */
        public Comparable elt;

        protected Node left, right, parent;

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

        public Node getParent() {
            return parent;
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

        Node focus = null;
        if (root == null) {
            root = newNode;
        } else {
            focus = root;
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

        newNode.parent = focus;
        size++;
        splay(newNode);
        return true;
    }

    @Override
    public boolean remove(Comparable x) {
        Node compNode = new Node(x);
        Node focus = root;

        if (!compNode.equals(root)) {
            if (!splay(compNode)) {
                return false;
            }
            //Node to be removed is now root
        }

        //REMOVING ROOT
        if (focus.getLeft() == null) {
            root = focus.getRight();
        } else if (focus.getRight() == null) {
            root = focus.getLeft();
        } else {
            focus = focus.getLeft();
            while (focus.getRight() != null) {
                focus = focus.getRight();
            }
            // Now focus is the last node

            focus.right = compNode.getRight();
            splay(focus);
            // focus should now be root
        }

        root.parent = null;
        size--;
        return true;
    }

    //@Override
    public boolean FIRSTremove(Comparable x) {
        Node compNode = new Node(x);
        Node focus = root;

        if (compNode.equals(root)) {
            if (focus.getLeft() == null) {
                root = focus.getRight();
            } else if (focus.getRight() == null) {
                root = focus.getLeft();
            } else {
                focus = focus.getLeft();
                while (focus.getRight() != null) {
                    focus = focus.getRight();
                }
                // Now focus is the last node

                focus.right = compNode.getRight();
                splay(focus);
                // focus should now be root
            }

            root.parent = null;
            size--;
            return true;
        }

        while (focus != null) {
            if (focus.getLeft().equals(compNode) || focus.getRight().equals(compNode)) {
                Node child = focus.getLeft().equals(compNode) ? focus.getLeft() : focus.getRight();
                Node newNode;

                if (child.getLeft() == null) {
                    if (child.getRight() == null) { // no children
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

    private boolean splay(Node node) {
        while (node.getParent() != null && node.getParent().getParent() != null) {
            if (node.compareTo(node.getParent()) > 0) {
                if (node.compareTo(node.getParent().getParent()) > 0) {
                    //zigZig(node.getParent().getParent(), true);
                    zigZig(node, true);
                } else {
                    //zigZag(node.getParent().getParent(), false);
                    zigZag(node, false);
                }
            } else {
                if (node.compareTo(node.getParent().getParent()) < 0) {
                    //zigZig(node.getParent().getParent(), false);
                    zigZig(node, false);
                } else {
                    //zigZag(node.getParent().getParent(), true);
                    zigZag(node, true);
                }
            }
        }

        if (node.getParent() != null) {
            //zig(node.getParent(), node.compareTo(node.getParent()) > 0);
            zig(node);
        }

        root = node;
        return true;
    }

    private void zig(Node node) {
        Node temp;
        if (node.compareTo(node.getParent()) > 0) {
            temp = node.getLeft();
            node.left = node.getParent();
            node.getParent().right = temp;
        } else {
            temp = node.getRight();
            node.right = node.getParent();
            node.getParent().left = temp;
        }

        temp = node.getParent();
        node.parent = temp.getParent();
        temp.parent = node;
    }

    private Node GAMMALzig(Node parent, Boolean rightChild) {
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

    // Runs two zigs after each other.
    private void zigZig(Node node) {
        zig(node.getParent());
        zig(node);
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
        return from.compareTo(to) > 0 ? from.getLeft() : from.getRight();
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

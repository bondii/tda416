/**
 * Created by Bondi on 2016-02-09.
 */
public class SplayTreeSet<Integer> implements SimpleSet {

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
            if (o == null) {
                throw new ArrayIndexOutOfBoundsException("HEST NULL HITTAT: " + this.elt);
            } else {
                return (this.elt).compareTo(((Node) o).elt);
            }
        }

        @Override
        public boolean equals(Object o) {
            return (this.elt).equals(((Node) o).elt);
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
        if (x == null) {
            return false;
        }

        Node newNode = new Node(x);

        Node focus = null;
        if (root == null){
            root = newNode ;
        } else {
            focus = root;
            while (true) {
                if (focus.equals(newNode)) {
                    return false;
                } else if (focus.compareTo(newNode) > 0) {
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
        if (root == null) {
            return false;
        }

        Node removeNode = new Node(x);

        if (removeNode.equals(root) && root.getLeft() == null && root.getRight() == null) {
            root = null;
        } else {
            if (!removeNode.equals(root)) {
                if (!contains(removeNode)) {
                    return false;
                }
            }
            //Node to be removed is now root
            removeNode = root;
            Node focus = root;

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

                focus.right = removeNode.getRight();
                if (removeNode.getRight() != null) {
                    removeNode.getRight().parent = focus;
                    root = removeNode.getLeft();
                }

                removeNode.getLeft().parent = null;
                root = removeNode.getLeft();


                contains(focus);
                // focus should now be root
            }

            root.parent = null;
        }

        size--;
        return true;
    }

    private void splay(Node node) {
        while (node.getParent() != null && node.getParent().getParent() != null) {
            if (node.compareTo(node.getParent()) > 0) {
                if (node.compareTo(node.getParent().getParent()) > 0) {
                    zigZig(node);
                } else {
                    zigZag(node);
                }
            } else {
                if (node.compareTo(node.getParent().getParent()) < 0) {
                    zigZig(node);
                } else {
                    zigZag(node);
                }
            }
        }

        if (node.getParent() != null) {
            zig(node);
        }

        root = node;
    }

    private void zig(Node node) {
        if (root.equals(node)) {
            return;
        }

        Node temp;
        if (node.compareTo(node.getParent()) > 0) {
            temp = node.getLeft();
            node.left = node.getParent();
            node.getParent().right = temp;
            if (temp != null) {
                temp.parent = node.getLeft();
            }
        } else {
            temp = node.getRight();
            node.right = node.getParent();
            node.getParent().left = temp;
            if (temp != null) {
                temp.parent = node.getRight();
            }
        }

        temp = node.getParent();
        node.parent = temp.getParent();
        temp.parent = node;

        if (node.getParent() != null) {
            if (node.compareTo(node.getParent()) > 0) {
                node.getParent().right = node;
            } else {
                node.getParent().left = node;
            }
        }
    }

    private void zigZig(Node node) {
        zig(node.getParent());
        zig(node);
    }

    private void zigZag(Node node) {
        zig(node);
        zig(node);
    }

    private Node moveTowards(Node from, Node to) {
        return from.compareTo(to) > 0 ? from.getLeft() : from.getRight();
    }

    public boolean contains(Node compNode) {
        Node focus = root;

        while (focus != null) {
            if (focus.equals(compNode)) {
                splay(focus);
                return true;
            } else {
                focus = moveTowards(focus, compNode);
            }
        }

        return false;
    }

    @Override
    public boolean contains(Comparable x) {
        return contains(new Node(x));
    }

}

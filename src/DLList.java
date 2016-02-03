/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  public class Node implements Comparable {
    /** The contents of the node is public */
    public E elt;

    protected Node prev, next;

    Node() {
      this(null);
    }
    Node(E elt) {
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
          return ((Comparable) this.elt).compareTo(((Node) o).elt);
      }
  }
  
  /** first and last nodes in list, null when list is empty */
  Node first, last;
  
  DLList() {
    first = last = null;
  }
  
  /** inserts an element at the beginning of the list
   * @param e   the new element value
   * @return    the node holding the added element
   */
  public Node addFirst(E e) {
      Node node = new Node(e);

      if (last == null) {
          first = node;
          last = node;
      } else {
          first.prev = node;
          node.next = first;
          first = node;
      }

      return node;
  }

  /** inserts an element at then end of the list
   * @param e   the new element
   * @return    the node holding the added element
   */
  public Node addLast(E e) {
      Node node = new Node(e);

      if (last == null) {
          first = node;
          last = node;
      } else {
          last.next = node;
          node.prev = last;
          last = node;
      }

      return node;
  }
  
  /**
   * @return    the node of the list's first element, null if list is empty
   */
  public Node getFirst() {
      return first;
  }
  
  /**
   * @return    the node of the list's last element, null if list is empty
   */
  public Node getLast() {
      return last;
  }
  
  /** inserts a new element after a specified node
    * @param e   the new element
    * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertAfter(E e, Node l) {
      Node node = new Node(e);

      node.next = l.next;
      node.prev = l;
      l.next.prev = node;
      l.next = node;

      return node;
  }

  /** inserts a new element before a specified node
    * @param e   the new element
    * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertBefore(E e, Node l) {
      Node node = new Node(e);

      node.next = l;
      node.prev = l.prev;
      l.prev.next = node;
      l.prev = node;

      return node;
  }

  /** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  public void remove(Node l) {
      Node temp = l.prev;
      l.prev.next = l.next;
      l.next.prev = temp;
  }
}
